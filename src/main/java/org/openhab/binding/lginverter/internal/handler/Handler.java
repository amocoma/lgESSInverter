package org.openhab.binding.lginverter.internal.handler;

import java.nio.charset.UnsupportedCharsetException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.ElectricCharge;
import javax.measure.quantity.ElectricCurrent;
import javax.measure.quantity.ElectricPotential;
import javax.measure.quantity.Energy;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Power;
import javax.measure.quantity.Time;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Response;
import org.eclipse.smarthome.core.library.types.DecimalType;
import org.eclipse.smarthome.core.library.types.OnOffType;
import org.eclipse.smarthome.core.library.types.QuantityType;
import org.eclipse.smarthome.core.library.types.StringType;
import org.eclipse.smarthome.core.library.unit.SIUnits;
import org.eclipse.smarthome.core.library.unit.SmartHomeUnits;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusDetail;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandler;
import org.eclipse.smarthome.core.types.Command;
import org.openhab.binding.lginverter.internal.BindingConstants;
import org.openhab.binding.lginverter.internal.BindingMapper;
import org.openhab.binding.lginverter.internal.ChannelDatatypes;
import org.openhab.binding.lginverter.internal.config.InverterConfiguration;
import org.openhab.binding.lginverter.internal.discovery.DiscoverServices;
import org.openhab.binding.lginverter.internal.handler.http.Constants;
import org.openhab.binding.lginverter.internal.handler.http.Constants.COMMON;
import org.openhab.binding.lginverter.internal.handler.http.Constants.STATES;
import org.openhab.binding.lginverter.internal.handler.http.HttpUtilities;
import org.openhab.binding.lginverter.internal.model.BindingConfig;
import org.openhab.binding.lginverter.internal.model.TimesyncInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link Handler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Dirk Gronert - Initial contribution
 */
public class Handler extends BaseThingHandler {

    private @Nullable String _ipAddress;
    private @Nullable HttpClient _httpClient;
    private @Nullable List<ScheduledFuture<?>> _refreshSchedulers = new LinkedList<ScheduledFuture<?>>();
    private @NonNullByDefault({}) InverterConfiguration _config;
    private TimesyncInfo _timesyncInfo;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public Handler(Thing thing, HttpClient httpClient) {
        super(thing);
        this._httpClient = httpClient;
    }


    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        // All channels are readonly and updated by the scheduler
    }

    @Override
    public void dispose() {
        this.cancelAllSchedulers();
        super.dispose();
    }

    private void cancelAllSchedulers(){
        if (this._refreshSchedulers != null) {
            this._refreshSchedulers.parallelStream().forEach(_rs -> _rs.cancel(true));
            this._refreshSchedulers = null;
        }
    }

    @Override
    public void initialize() {
        this._config = getConfigAs(InverterConfiguration.class);
        // temporary value while initializing
        this.updateStatus(ThingStatus.UNKNOWN);
        // Start the authentication
        this.scheduler.schedule(this::authenticate, 1, TimeUnit.SECONDS);
    }

    /**
     * The API supports the resolution of multiple values at a time
     *
     * Therefore this methods builds one request to gather all information for the
     * current inverter. The list contains all channels as defined in
     * {@link MappingInverterToChannel} for the current inverter
     *
     */
    private void updateChannelValues(Integer retry, STATES state) {
        // Send the API request to get values for all channels
        if (retry < 3) {
            if (this._timesyncInfo != null) {
                ContentResponse metricResponse;
                try {
                    String stateURL = state.config.getEndpoint(this._config, this._ipAddress);
                    metricResponse = HttpUtilities.executeHttpPost(this._httpClient, this._timesyncInfo, stateURL, null);
                    final int statusCode = metricResponse.getStatus();
                    if (statusCode >= 400) {
                        if (statusCode == HttpStatus.UNAUTHORIZED_401  || statusCode == HttpStatus.METHOD_NOT_ALLOWED_405 ) {
                            logger.info("Session expired - performing authentication and afterwards a retry");
                            this.authenticate();
                            this.updateChannelValues(retry++, state);
                            return;
                        }
                        if (statusCode == HttpStatus.NOT_FOUND_404) {
                            this.updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.OFFLINE.COMMUNICATION_ERROR, BindingConstants.COMMUNICATION_ERROR_INCOMPATIBLE_DEVICE);
                            return;
                        }
                        if (statusCode == HttpStatus.SERVICE_UNAVAILABLE_503) {
                            this.updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.OFFLINE.COMMUNICATION_ERROR, BindingConstants.COMMUNICATION_ERROR_HTTP);
                            return;
                        }
                        this.updateStatus(ThingStatus.UNKNOWN);
                        return;
                    }
                } catch (TimeoutException | ExecutionException e) {
                    // Communication problem
                    this.updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.OFFLINE.COMMUNICATION_ERROR, BindingConstants.COMMUNICATION_ERROR_HTTP);
                    return;
                } catch (final InterruptedException e) {
                    Thread.currentThread().interrupt();
                    this.updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.OFFLINE.COMMUNICATION_ERROR, BindingConstants.COMMUNICATION_ERROR_HTTP);
                    return;
                }
                this.extractMetricsToChannels(state, HttpUtilities.getJsonObjectFromResponse(metricResponse));
                this.updateStatus(ThingStatus.ONLINE);                    
            } else {
                this.authenticate();
                // #TODO message
                this.updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.OFFLINE.COMMUNICATION_ERROR, BindingConstants.COMMUNICATION_ERROR_HTTP);
            }
        } else {
            // #TODO message
            this.updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.OFFLINE.COMMUNICATION_ERROR, BindingConstants.COMMUNICATION_ERROR_HTTP);
        }
    }

    private void extractMetricsToProperties(JsonObject metrics) {
    }

    private void extractMetricsToChannels(STATES state, JsonObject metrics) {
        for (BindingConfig config : BindingMapper.getBindingConfigs(this.thing.getThingTypeUID().getId(), Constants.VERSIONS.V1, state)) {
            JsonObject target = metrics;
            JsonPrimitive primValue = null;
            int pathSegments = 0;
            for (String path : config.path) {
                if (target.isJsonObject()) {
                    pathSegments++;
                    if (target.has(path)) {
                        JsonElement pathValue = target.get(path);
                        if (pathValue.isJsonPrimitive()) primValue = pathValue.getAsJsonPrimitive();
                        else if (pathValue.isJsonObject())  target = pathValue.getAsJsonObject();
                        else {
                            // #TODO message
                            logger.warn("");
                            break;
                        }
                    } else {
                        // #TODO message
                        logger.warn("");
                        break;
                    }
                } else {
                    // #TODO message
                    logger.warn("");
                    break;
                }
            }
        
            if (config.path.size() == pathSegments && primValue != null)
                updateChannelValue(config.channelUID, config.dataType, primValue);
        }
    }
         
        /**
     * Update the channel to the given value. The value is set to the matching data (SITypes etc)
     *
     * @param channeluid Channel to update
     * @param dataType   target data type
     * @param value      primValue
     */
    private void updateChannelValue(final String channeluid, final ChannelDatatypes dataType, JsonPrimitive primValue) {
        System.out.println(channeluid + " : " + primValue.toString() + " : " + dataType);
        switch (dataType) {
            case INTEGER:
                this.updateState(channeluid, new DecimalType(primValue.getAsInt()));
                break;
            case PERCENTAGE:
                this.updateState(channeluid, new QuantityType<Dimensionless>(primValue.getAsDouble(), SmartHomeUnits.PERCENT));
                break;
            case KILOGRAM:
                this.updateState(channeluid, new QuantityType<Mass>(primValue.getAsDouble() / 1000, SIUnits.KILOGRAM));
                break;
            case SECONDS:
                this.updateState(channeluid, new QuantityType<Time>(primValue.getAsDouble() , SmartHomeUnits.SECOND));
                break;
            case KILOWATT_HOUR:
                this.updateState(channeluid, new QuantityType<Energy>(primValue.getAsDouble() / 1000, SmartHomeUnits.KILOWATT_HOUR));
                break;
            case WATT:
                this.updateState(channeluid, new QuantityType<Power>(primValue.getAsDouble(), SmartHomeUnits.WATT));
                break;
            case AMPERE:
                this.updateState(channeluid, new QuantityType<ElectricCurrent>(primValue.getAsDouble(), SmartHomeUnits.AMPERE));
                break;
            case AMPERE_HOUR:
                this.updateState(channeluid, new QuantityType<ElectricCharge>(primValue.getAsDouble() * 3600, SmartHomeUnits.COULOMB));
                break;
            case VOLT:
                this.updateState(channeluid, new QuantityType<ElectricPotential>(primValue.getAsDouble(), SmartHomeUnits.VOLT));
                break;
            case ONOFF:
                this.updateState(channeluid, OnOffType.from(primValue.getAsString()));
                break;
            case STRING:
                this.updateState(channeluid, new StringType(primValue.getAsString()));                
                break;
            case INTERNET_CONNECTED:
                this.updateState(channeluid, OnOffType.from("internet_connected".equals(primValue.getAsString())));
                break;
            case SWITCH_ONE_ZERO:
                this.updateState(channeluid, OnOffType.from(1 == primValue.getAsInt()));
                break;

            default: {
                // unknown datatype
                logger.debug("{} not known!", dataType);
            }
        }
    }
            
            
    /**
     * Method to authenticate against the ESS. 
     */
    private final void authenticate() {
        this.cancelAllSchedulers();
        try{
            this._ipAddress = DiscoverServices.getESSIP(this._config.serial);
        } catch (Exception e) {
            // #TODO message
            if (this._config.ip != null) {
                this._ipAddress = this._config.ip;
            } else {
                this.updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.OFFLINE.COMMUNICATION_ERROR,
                BindingConstants.COMMUNICATION_ERROR_HTTP);
                this._refreshSchedulers = new LinkedList<ScheduledFuture<?>>();
                this._refreshSchedulers.add(scheduler.schedule(this::authenticate, 60 * 15, TimeUnit.SECONDS));
                return;
            
            }
        }
        this.thing.setProperty("ip", this._ipAddress);
        this._timesyncInfo = null;
        if (COMMON.LOGIN.config.isValid(this._config.version) && COMMON.TIMESYNC.config.isValid(this._config.version)) {
            final String authUrl = COMMON.LOGIN.config.getEndpoint(this._config, this._ipAddress),
                timesyncUrl = COMMON.TIMESYNC.config.getEndpoint(this._config, this._ipAddress);
            final JsonObject authPayload = new JsonObject();
            authPayload.addProperty("password", this._config.password);
            Request authRequest = this._httpClient.newRequest(authUrl)
                                        .method(HttpMethod.PUT)
                                        .header(HttpHeader.CONTENT_TYPE, "application/json")
                                        .content(new StringContentProvider(authPayload.toString()), "application/json");
            ContentResponse authResponse;
            try{
                authResponse = authRequest.send();
                final int statusCode = authResponse.getStatus();
                if (statusCode >= 400) {
                    if(statusCode == HttpStatus.BAD_REQUEST_400){
                        this.updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.OFFLINE.COMMUNICATION_ERROR, BindingConstants.COMMUNICATION_ERROR_API_CHANGED);
                        return;
                    }else if(statusCode == HttpStatus.FORBIDDEN_403){
                        this.updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.OFFLINE.CONFIGURATION_ERROR, BindingConstants.COMMUNICATION_ERROR_USER_ACCOUNT_LOCKED);
                        return;
                    }else if(statusCode == HttpStatus.SERVICE_UNAVAILABLE_503){
                        this.updateStatus(ThingStatus.UNINITIALIZED);
                        return;
                    }
                    this.updateStatus(ThingStatus.UNINITIALIZED);
                    this._refreshSchedulers = new LinkedList<ScheduledFuture<?>>();
                    this._refreshSchedulers.add(scheduler.schedule(this::authenticate, 60 * 15, TimeUnit.SECONDS));
                    return;               
                }
            } catch (InterruptedException | TimeoutException | ExecutionException e) {
                this.updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.OFFLINE.COMMUNICATION_ERROR,
                        BindingConstants.COMMUNICATION_ERROR_HTTP);
                this._refreshSchedulers = new LinkedList<ScheduledFuture<?>>();
                this._refreshSchedulers.add(scheduler.schedule(this::authenticate, 60 * 15, TimeUnit.SECONDS));
                return;
            }
    
            final JsonObject authResponseJSON = HttpUtilities.getJsonObjectFromResponse(authResponse);
            TimesyncInfo tsi = new TimesyncInfo(authResponseJSON.get("auth_key").getAsString());
            Request timesyncRequest = this._httpClient.newRequest(timesyncUrl)
                                            .method(HttpMethod.PUT)
                                            .header(HttpHeader.CONTENT_TYPE, "application/json")
                                            .content(new StringContentProvider((new Gson()).toJson(tsi.getESSTimesync())), "application/json");
            ContentResponse timesyncResponse;
            try{
                timesyncResponse = timesyncRequest.send();
                if(timesyncResponse.getStatus() != Response.SC_OK){
                    this.updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.OFFLINE.COMMUNICATION_ERROR, BindingConstants.COMMUNICATION_ERROR_API_CHANGED);
                    return;
                }
            }catch(InterruptedException | TimeoutException | ExecutionException e){
                this.updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.OFFLINE.COMMUNICATION_ERROR, BindingConstants.COMMUNICATION_ERROR_HTTP);
                return;
            }
            try{
                final JsonObject timesyncResponseJSON = HttpUtilities.getJsonObjectFromResponse(timesyncResponse);
                String status = timesyncResponseJSON.has("status") ? timesyncResponseJSON.get("status").getAsString() : "noSucess";
                if(!status.equals("success")){
                    this.updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.OFFLINE.COMMUNICATION_ERROR, BindingConstants.COMMUNICATION_ERROR_HTTP);
                    return;
                }
            }catch(JsonSyntaxException | UnsupportedCharsetException e){
                this.updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.OFFLINE.COMMUNICATION_ERROR, BindingConstants.COMMUNICATION_ERROR_HTTP);
                return;
            }
            this._timesyncInfo = tsi;
            this.updateStatus(ThingStatus.ONLINE);
            this._refreshSchedulers = new LinkedList<ScheduledFuture<?>>();
            this._refreshSchedulers.add(scheduler.schedule(this::authenticate, 86400, TimeUnit.SECONDS));
            for (STATES state : Constants.STATES.values())
                if(state.config.isActiveAndValid(this._config)) this._refreshSchedulers.add(scheduler.scheduleWithFixedDelay(() -> this.updateChannelValues(0, state), 1, state.config.getRefreshInternal(this._config), TimeUnit.SECONDS));            
        } else {
            this.updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.OFFLINE.COMMUNICATION_ERROR, BindingConstants.COMMUNICATION_ERROR_VERSION_NOT_SUPPORTED);
        }
    }
}