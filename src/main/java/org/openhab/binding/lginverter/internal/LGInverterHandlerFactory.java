/**
 * Copyright (c) 2010-2020 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.lginverter.internal;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.smarthome.config.core.Configuration;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.ThingUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerFactory;
import org.eclipse.smarthome.io.net.http.HttpClientFactory;
import org.eclipse.smarthome.io.net.http.HttpClientInitializationException;
import org.openhab.binding.lginverter.internal.handler.Handler;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link LGInverterHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Dirk Gronert - Initial contribution
 */
@Component(configurationPid = "binding.lginverter", service = ThingHandlerFactory.class)
public class LGInverterHandlerFactory extends BaseThingHandlerFactory {

    private HttpClient _httpClient;
    private final Logger logger = LoggerFactory.getLogger(LGInverterHandlerFactory.class);



    public LGInverterHandlerFactory() {
        // [wip] mgb: temporary work around until ssl issues are sorted
        this._httpClient = new HttpClient(new SslContextFactory(true));
        try {
            this._httpClient.start();
        } catch (Exception e) {
            throw new HttpClientInitializationException("Could not start HttpClient", e);
        }
    }

    @Override
    public Thing createThing(ThingTypeUID thingTypeUID, Configuration configuration, ThingUID thingUID, ThingUID bridgeUID) {
        if (BindingConstants.SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID)) {
            ThingUID lgInverterUID = getInverterUID(thingTypeUID, thingUID, configuration);
            logger.debug("Creating a LG inverter thing with ID '{}'", lgInverterUID);
            return super.createThing(thingTypeUID, configuration, lgInverterUID, null);
        }
        throw new IllegalArgumentException(
                "The thing type " + thingTypeUID + " is not supported by the sonos binding.");
    }    

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return BindingConstants.SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected @Nullable ThingHandler createHandler(Thing thing) {
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();
        if (BindingConstants.SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID)) {
            logger.debug("Creating a InverterHandler for thing '{}'", thing.getUID());
            return new Handler(thing, this._httpClient);
        }
        return null;
    }
    
    private ThingUID getInverterUID(ThingTypeUID thingTypeUID, ThingUID thingUID, Configuration configuration) {
        return thingUID;
    }


    // @Reference // [wip] mgb: disabled due to missing common name attributes with certs
    protected void setHttpClientFactory(HttpClientFactory httpClientFactory) {
        this._httpClient = httpClientFactory.getCommonHttpClient();
    }

    protected void unsetHttpClientFactory(HttpClientFactory httpClientFactory) {
        this._httpClient = null;
    }
}
