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

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.core.thing.ThingTypeUID;

/**
 * The {@link BindingConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Dirk Gronert - Initial contribution
 */
@NonNullByDefault
public class BindingConstants {

    // List of error messages
    public static final String COMMUNICATION_ERROR_AUTHENTICATION = "Error during the initialisation of the authentication";
    public static final String COMMUNICATION_ERROR_VERSION_NOT_SUPPORTED = "Error during the initialisation of the authentication";
    public static final String COMMUNICATION_ERROR_UNSUPPORTED_ENCODING = "The text encoding is not supported by this openHAB installation";
    public static final String COMMUNICATION_ERROR_AES_ERROR = "The java installation does not support AES encryption in GCM mode";
    public static final String COMMUNICATION_ERROR_HTTP = "HTTP communication error: No response from device";
    public static final String COMMUNICATION_ERROR_JSON = "HTTP communication error: answer did not match the expected format";
    public static final String COMMUNICATION_ERROR_API_CHANGED = "The API seems to have changed :-( Maybe this implementation has become incompatible with the device";
    public static final String COMMUNICATION_ERROR_INCOMPATIBLE_DEVICE = "The device could not provide the required information. Please check, if you selected the right thing for your device!";
    public static final String COMMUNICATION_ERROR_USER_ACCOUNT_LOCKED = "Your user account on the device is locked. Please reset the password by following the instructions on the device´s web frontend";
    public static final String CONFIGURATION_ERROR_PASSWORD = "Wrong password";

    // ???? from SONOS
    // BINDING ID
    private static final String BINDING_ID = "lginverter";

    public enum MODELS {D010KE1N211}

    // MODELS
    public static final ThingTypeUID MODEL_D010KE1N211_THING_TYPE_UID = new ThingTypeUID(BINDING_ID, MODELS.D010KE1N211.name());
    
    public static final Set<ThingTypeUID> SUPPORTED_KNOWN_THING_TYPES_UIDS = Stream.of(MODEL_D010KE1N211_THING_TYPE_UID).collect(Collectors.toSet());
    public static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = new HashSet<ThingTypeUID>(SUPPORTED_KNOWN_THING_TYPES_UIDS);

    // channel dictionary
    private static final String ACTIVE = "active";
    private static final String AMPERAGE = "amperage";
    private static final String APHASE = "aPhase";
    private static final String BACKUP = "backup";
    private static final String BATTERY = "battery";
    private static final String BRAND = "brand";
    private static final String CAPACITY = "capacity";
    private static final String CHARGE = "charge";
    private static final String CO2 = "co2";
    private static final String CONSUMPTION = "consumption";
    private static final String DC = "dc";
    private static final String DISCHARGE = "discharge";
    private static final String FEEDIN = "feedIn";
    private static final String FREQUENCY = "frequency";
    private static final String GENERATION = "generation";
    private static final String GRID = "grid";
    private static final String LIMITATION = "limitation";
    private static final String MODE= "mode";
    private static final String MONTH = "month";
    private static final String OPERATION = "operation";
    private static final String PCS = "pcs";
    private static final String POWER = "power";
    private static final String PV = "pv";
    private static final String REDUCTION = "reduction";
    private static final String SAFETY = "safety";
    private static final String SELF = "self";
    private static final String SETTING = "setting";
    private static final String SOC = "soc";
    private static final String STATUS = "status";
    private static final String STRING = "string";
    private static final String TODAY = "today";
    private static final String VOLTAGE = "voltage";
    private static final String WINTER = "winter";
    private static final String TYPE = "type";
    private static final String IP = "ip";
    private static final String MASK = "mask";
    private static final String GATEWAY = "gateway";
    private static final String DNS = "dns";
    private static final String CONNECTED = "connected";
    private static final String ALG = "alg";
    private static final String STOP = "start"; 
    private static final String START = "stop"; 
    private static final String DATE = "date";
    private static final String PMS = "pms";
    private static final String UNIT = "unit";
    private static final String VERSION = "version";
    private static final String BUILD = "build";
    private static final String INSTALL = "install";
    private static final String BMS = "bms";
    private static final String MODEL = "model";
    private static final String INPUT = "input";
    private static final String OUTPUT = "output";
    private static final String AC = "ac";
    private static final String SERIAL = "serial";
    private static final String STATISTIC = "stat";
    private static final String CONVERSION = "conversion";
    private static final String TOTAL = "total";
    private static final String USE = "use";
    private static final String USER = "user";
    private static final String UI = "ui";
    private static final String LOAD = "load";
    private static final String IS = "is";
    private static final String DIRECT = "direct";
    private static final String CHARGING = "charging";
    private static final String DISCHARGING = "discharging";
    private static final String FROM = "from";
    private static final String TO = "to";
    private static final String STANDBY = "standby";
    private static final String HEATPUMP = "heatpump";
    private static final String ENABLE = "emable";
    private static final String ACTIVATE = "activate";
    private static final String EV = "ev";
    private static final String CHARGER = "charger";
    private static final String WAITING = "waiting";
    private static final String TIME = "time";
    private static final String TEMPERATURE = "temperature";


    // CHANNEL DEFINITIONS
    public static final String COMMON_BATTERY_BACKUP_SETTING = BATTERY + StringUtils.capitalize(BACKUP) + StringUtils.capitalize(SETTING);
    public static final String COMMON_BATTERY_BACKUP_SOC = BATTERY + StringUtils.capitalize(BACKUP) + SOC.toUpperCase();
    public static final String COMMON_BATTERY_BACKUP_STATUS = BATTERY + StringUtils.capitalize(BACKUP) + StringUtils.capitalize(STATUS);
    public static final String COMMON_BATTERY_CHARGE_MONTH = BATTERY + StringUtils.capitalize(CHARGE) + StringUtils.capitalize(MONTH);
    public static final String COMMON_BATTERY_CHARGE_TODAY = BATTERY + StringUtils.capitalize(CHARGE) + StringUtils.capitalize(TODAY);
    public static final String COMMON_BATTERY_DC_POWER = BATTERY + DC.toUpperCase() + StringUtils.capitalize(POWER);
    public static final String COMMON_BATTERY_DISCHARGE_MONTH = BATTERY + StringUtils.capitalize(DISCHARGE) + StringUtils.capitalize(MONTH);
    public static final String COMMON_BATTERY_DISCHARGE_TODAY = BATTERY + StringUtils.capitalize(DISCHARGE) + StringUtils.capitalize(TODAY);
    public static final String COMMON_BATTERY_SAFETY_SOC = BATTERY + StringUtils.capitalize(SAFETY) + SOC.toUpperCase();
    public static final String COMMON_BATTERY_SOC = BATTERY + SOC.toUpperCase();
    public static final String COMMON_BATTERY_STATUS = BATTERY + StringUtils.capitalize(STATUS);
    public static final String COMMON_BATTERY_WINTER_SETTING = BATTERY + StringUtils.capitalize(WINTER) + StringUtils.capitalize(SETTING);
    public static final String COMMON_BATTERY_WINTER_STATUS = BATTERY + StringUtils.capitalize(WINTER) + StringUtils.capitalize(STATUS);
    public static final String COMMON_GRID_ACTIVE_POWER = GRID + StringUtils.capitalize(ACTIVE) + StringUtils.capitalize(POWER);
    public static final String COMMON_GRID_APHASE = GRID + StringUtils.capitalize(APHASE);
    public static final String COMMON_GRID_FEEDIN_MONTH = GRID + StringUtils.capitalize(FEEDIN) + StringUtils.capitalize(MONTH);
    public static final String COMMON_GRID_FEEDIN_TODAY = GRID + StringUtils.capitalize(FEEDIN) + StringUtils.capitalize(TODAY);
    public static final String COMMON_GRID_FREQUENCY = GRID + StringUtils.capitalize(FREQUENCY);
    public static final String COMMON_GRID_CONSUMPTION_MONTH = GRID + StringUtils.capitalize(CONSUMPTION) + StringUtils.capitalize(MONTH);
    public static final String COMMON_GRID_CONSUMPTION_TODAY = GRID + StringUtils.capitalize(CONSUMPTION) + StringUtils.capitalize(TODAY);
    public static final String COMMON_LOAD_BATTERY_CONSUMPTION_MONTH = BATTERY + StringUtils.capitalize(CONSUMPTION) + StringUtils.capitalize(MONTH);
    public static final String COMMON_LOAD_BATTERY_CONSUMPTION_TODAY = BATTERY + StringUtils.capitalize(CONSUMPTION) + StringUtils.capitalize(TODAY);
    public static final String COMMON_LOAD_CONSUMPTION_MONTH = CONSUMPTION + StringUtils.capitalize(MONTH);
    public static final String COMMON_LOAD_CONSUMPTION_TODAY = CONSUMPTION + StringUtils.capitalize(TODAY);
    public static final String COMMON_LOAD_GRID_CONSUMPTION_MONTH = GRID + StringUtils.capitalize(CONSUMPTION) + StringUtils.capitalize(MONTH);
    public static final String COMMON_LOAD_GRID_CONSUMPTION_TODAY = GRID + StringUtils.capitalize(CONSUMPTION) + StringUtils.capitalize(TODAY);
    public static final String COMMON_LOAD_POWER = CONSUMPTION + StringUtils.capitalize(POWER);
    public static final String COMMON_LOAD_PV_CONSUMPTION_MONTH = PV + StringUtils.capitalize(CONSUMPTION) + StringUtils.capitalize(MONTH);
    public static final String COMMON_LOAD_PV_CONSUMPTION_TODAY = PV + StringUtils.capitalize(CONSUMPTION) + StringUtils.capitalize(TODAY);
    public static final String COMMON_PCS_CO2_REDUCTION_MONTH = CO2 + StringUtils.capitalize(REDUCTION) + StringUtils.capitalize(MONTH);
    public static final String COMMON_PCS_FEEDIN_LIMITATION = GRID + StringUtils.capitalize(FEEDIN) + StringUtils.capitalize(LIMITATION);
    public static final String COMMON_PCS_GRID_FEEDIN_MONTH = GRID + StringUtils.capitalize(FEEDIN) + StringUtils.capitalize(MONTH);
    public static final String COMMON_PCS_GRID_FEEDIN_TODAY = GRID + StringUtils.capitalize(FEEDIN) + StringUtils.capitalize(TODAY);
    public static final String COMMON_PCS_OPERATION_MODE = PCS + StringUtils.capitalize(OPERATION) + StringUtils.capitalize(MODE);
    public static final String COMMON_PCS_PV_GENERATION_MONTH = GENERATION + StringUtils.capitalize(MONTH);
    public static final String COMMON_PCS_PV_GENERATION_TODAY = GENERATION + StringUtils.capitalize(TODAY);
    public static final String COMMON_PCS_SELF_CONSUMPTION_TODAY = SELF + StringUtils.capitalize(CONSUMPTION) + StringUtils.capitalize(TODAY);
    public static final String COMMON_PCS_STATUS = PCS + StringUtils.capitalize(STATUS);
    public static final String COMMON_PV_BRAND = PV + StringUtils.capitalize(BRAND);
    public static final String COMMON_PV_CAPACITY = PV + StringUtils.capitalize(CAPACITY);
    public static final String COMMON_PV_GENERATION_MONTH = GENERATION + StringUtils.capitalize(MONTH);
    public static final String COMMON_PV_GENERATION_TODAY = GENERATION + StringUtils.capitalize(TODAY);
    public static final String COMMON_PV_STRING_1_AMPERAGE = PV + StringUtils.capitalize(STRING) + "1" + StringUtils.capitalize(AMPERAGE);
    public static final String COMMON_PV_STRING_1_POWER = PV + StringUtils.capitalize(STRING) + "1" +StringUtils.capitalize(POWER);
    public static final String COMMON_PV_STRING_1_VOLTAGE = PV + StringUtils.capitalize(STRING) + "1" + StringUtils.capitalize(VOLTAGE);
    public static final String COMMON_PV_STRING_2_AMPERAGE = PV + StringUtils.capitalize(STRING) + "2" + StringUtils.capitalize(AMPERAGE);
    public static final String COMMON_PV_STRING_2_POWER = PV + StringUtils.capitalize(STRING) + "2" + StringUtils.capitalize(POWER);
    public static final String COMMON_PV_STRING_2_VOLTAGE = PV + StringUtils.capitalize(STRING) + "2" + StringUtils.capitalize(VOLTAGE);
    public static final String COMMON_PV_STRING_3_AMPERAGE = PV + StringUtils.capitalize(STRING) + "3" + StringUtils.capitalize(AMPERAGE);
    public static final String COMMON_PV_STRING_3_POWER = PV + StringUtils.capitalize(STRING) + "3" + StringUtils.capitalize(POWER);
    public static final String COMMON_PV_STRING_3_VOLTAGE = PV + StringUtils.capitalize(STRING) + "3" + StringUtils.capitalize(VOLTAGE);
    // NETWORK API
    public static final String NETWORK_TYPE = TYPE;
    public static final String NETWORK_SETTING = SETTING;
    public static final String NETWORK_IP = IP;
    public static final String NETWORK_MASK = MASK;
    public static final String NETWORK_GATEWAY = GATEWAY;
    public static final String NETWORK_DNS = DNS;
    public static final String NETWORK_CONNECTED = CONNECTED;
    // BATTERY API
    public static final String BATTERY_WINTER_SETTING = WINTER + StringUtils.capitalize(SETTING);
    public static final String BATTERY_WINTER_STATUS = WINTER + StringUtils.capitalize(STATUS);
    public static final String BATTERY_BACKUP_SETTING = BACKUP + StringUtils.capitalize(SETTING);
    public static final String BATTERY_BACKUP_STATUS = BACKUP + StringUtils.capitalize(STATUS);
    public static final String BATTERY_ALG_SETTING = ALG + StringUtils.capitalize(SETTING);
    public static final String BATTERY_SAFETY_SOC = SAFETY + SOC.toUpperCase();
    public static final String BATTERY_BACKUP_SOC = BACKUP + SOC.toUpperCase();
    public static final String BATTERY_START_DATE = START + StringUtils.capitalize(DATE);
    public static final String BATTERY_END_DATE = STOP + StringUtils.capitalize(DATE);
    // SYSTEMINFO API
    public static final String SYSTEMINFO_PMS_MODEL = PMS + StringUtils.capitalize(MODEL);
    public static final String SYSTEMINFO_PMS_SERIAL = PMS + StringUtils.capitalize(SERIAL);
    public static final String SYSTEMINFO_PMS_AC_POWER_INPUT = PMS + AC.toUpperCase() + StringUtils.capitalize(POWER) + StringUtils.capitalize(INPUT);
    public static final String SYSTEMINFO_PMS_AC_POWER_OUTPUT = PMS + AC.toUpperCase() + StringUtils.capitalize(POWER) + StringUtils.capitalize(OUTPUT);
    public static final String SYSTEMINFO_PMS_INSTALL_DATE = PMS + StringUtils.capitalize(INSTALL) + StringUtils.capitalize(DATE);
    public static final String SYSTEMINFO_BATTERY_CAPACITY = BATTERY + StringUtils.capitalize(CAPACITY);
    public static final String SYSTEMINFO_BATTERY_INSTALL_DATE = BATTERY + StringUtils.capitalize(INSTALL) + StringUtils.capitalize(DATE);
    public static final String SYSTEMINFO_PMS_VERSION = PMS + StringUtils.capitalize(VERSION);
    public static final String SYSTEMINFO_PMS_BUILD_DATE = PMS + StringUtils.capitalize(BUILD) + StringUtils.capitalize(DATE);;
    public static final String SYSTEMINFO_PCS_VERSION = PCS + StringUtils.capitalize(VERSION);
    public static final String SYSTEMINFO_BMS_VERSION = BMS + StringUtils.capitalize(VERSION);
    public static final String SYSTEMINFO_BMS_UNIT_1_VERSION = BMS + StringUtils.capitalize(UNIT) + "1" + StringUtils.capitalize(VERSION);
    public static final String SYSTEMINFO_BMS_UNIT_2_VERSION = BMS + StringUtils.capitalize(UNIT) + "2" + StringUtils.capitalize(VERSION);
    // HOME API
    public static final String HOME_STATISTIC_PCS_PV_TOTAL_POWER = STATISTIC + StringUtils.capitalize(PCS) + PV.toUpperCase() + StringUtils.capitalize(TOTAL) + StringUtils.capitalize(POWER);
    public static final String HOME_STATISTIC_BATTERY_CONVERSION_POWER = STATISTIC + StringUtils.capitalize(BATTERY) + StringUtils.capitalize(CONVERSION) + StringUtils.capitalize(POWER);
    public static final String HOME_STATISTIC_BATTERY_USE = STATISTIC + StringUtils.capitalize(BATTERY) + StringUtils.capitalize(USE);
    public static final String HOME_STATISTIC_BATTERY_STATUS = STATISTIC + StringUtils.capitalize(BATTERY) + StringUtils.capitalize(STATUS);
    public static final String HOME_STATISTIC_BATTERY_USER_SOC = STATISTIC + StringUtils.capitalize(BATTERY) + StringUtils.capitalize(USER) + SOC.toUpperCase();
    public static final String HOME_STATISTIC_LOAD_POWER = STATISTIC + StringUtils.capitalize(LOAD) + StringUtils.capitalize(POWER);
    public static final String HOME_STATISTIC_LOAD_POWER_UI= STATISTIC + StringUtils.capitalize(LOAD) + StringUtils.capitalize(POWER) + UI.toUpperCase();
    public static final String HOME_STATISTIC_AC_POWER_OUTPUT = STATISTIC + AC.toUpperCase() + StringUtils.capitalize(POWER) + StringUtils.capitalize(OUTPUT);
    public static final String HOME_STATISTIC_LOAD_TODAY = STATISTIC + StringUtils.capitalize(LOAD) + StringUtils.capitalize(TODAY);
    public static final String HOME_STATISTIC_GRID_POWER = STATISTIC + StringUtils.capitalize(GRID) + StringUtils.capitalize(POWER);
    public static final String HOME_STATISTIC_SELF_CONSUMPTION_TODAY = STATISTIC + StringUtils.capitalize(SELF) + StringUtils.capitalize(CONSUMPTION) + StringUtils.capitalize(TODAY);
    public static final String HOME_STATISTIC_PV_GENERATION = STATISTIC + PV.toUpperCase() + StringUtils.capitalize(GENERATION);
    public static final String HOME_STATISTIC_GRID_FEEDIN = STATISTIC + StringUtils.capitalize(GRID) + StringUtils.capitalize(FEEDIN);
    public static final String HOME_DIRECTION_IS_DIRECT_CONSUMPTION = IS + StringUtils.capitalize(DIRECT) + StringUtils.capitalize(CONSUMPTION);
    public static final String HOME_DIRECTION_IS_BATTERY_CHARGING = IS + StringUtils.capitalize(BATTERY) + StringUtils.capitalize(CHARGING);
    public static final String HOME_DIRECTION_IS_BATTERY_DISCHARGING = IS + StringUtils.capitalize(BATTERY) + StringUtils.capitalize(DISCHARGING);
    public static final String HOME_DIRECTION_IS_GRID_FEEDIN = IS + StringUtils.capitalize(GRID) + StringUtils.capitalize(FEEDIN);
    public static final String HOME_DIRECTION_IS_GRID_CONSUMPTION = IS + StringUtils.capitalize(GRID) + StringUtils.capitalize(CONSUMPTION);
    public static final String HOME_DIRECTION_IS_BATTERY_CHARGING_FROM_GRID = IS + StringUtils.capitalize(BATTERY) + StringUtils.capitalize(CHARGING) + StringUtils.capitalize(FROM) + StringUtils.capitalize(GRID);
    public static final String HOME_DIRECTION_IS_BATTERY_DISCHARGING_TO_GRID = IS + StringUtils.capitalize(BATTERY) + StringUtils.capitalize(DISCHARGING) + StringUtils.capitalize(TO) + StringUtils.capitalize(GRID);
    public static final String HOME_OPERATION_STATUS = OPERATION + StringUtils.capitalize(STATUS);
    public static final String HOME_OPERATION_MODE = OPERATION + StringUtils.capitalize(MODE);
    public static final String HOME_OPERATION_PCS_STANDBY = PCS + StringUtils.capitalize(STANDBY);
    public static final String HOME_OPERATION_MODE_WINTER = MODE + StringUtils.capitalize(WINTER);
    public static final String HOME_OPERATION_MODE_BACKUP = MODE + StringUtils.capitalize(BACKUP);
    public static final String HOME_OPERATION_PCS_STATUS = PCS + StringUtils.capitalize(STATUS);
    public static final String HOME_HEATPUMP_ACTIVATE = HEATPUMP + StringUtils.capitalize(ACTIVATE);
    public static final String HOME_HEATPUMP_TEMPERATURE = HEATPUMP + StringUtils.capitalize(TEMPERATURE);
    public static final String HOME_HEATPUMP_ENABLE = HEATPUMP + StringUtils.capitalize(ENABLE);
    public static final String HOME_EV_CHARGER_ACTIVATE = EV + StringUtils.capitalize(CHARGER)  + StringUtils.capitalize(ACTIVATE);
    public static final String HOME_EV_CHARGER_POWER = EV + StringUtils.capitalize(CHARGER)  + StringUtils.capitalize(POWER);
    public static final String HOME_GRID_WAITING_TIME = GRID + StringUtils.capitalize(WAITING)  + StringUtils.capitalize(TIME);
}