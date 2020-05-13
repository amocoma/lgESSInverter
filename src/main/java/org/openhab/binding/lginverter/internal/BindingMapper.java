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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.binding.lginverter.internal.BindingConstants.MODELS;
import org.openhab.binding.lginverter.internal.handler.http.Constants;
import org.openhab.binding.lginverter.internal.model.BindingConfig;

/**
 * The {@link BindingMapper} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Dirk Gronert - Initial contribution
 */
@NonNullByDefault
public class BindingMapper {
    
    private static final Map<String, List<BindingConfig>> CHANNEL_MAPPING = new HashMap<String, List<BindingConfig>>();

    static {
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_BATTERY_BACKUP_SETTING, Stream.of("BATT","backup_setting").collect(Collectors.toList()), ChannelDatatypes.ONOFF);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_BATTERY_BACKUP_SOC, Stream.of("BATT","backup_soc").collect(Collectors.toList()), ChannelDatatypes.PERCENTAGE);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_BATTERY_BACKUP_STATUS, Stream.of("BATT","backup_status").collect(Collectors.toList()), ChannelDatatypes.ONOFF);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_BATTERY_CHARGE_MONTH, Stream.of("BATT","month_batt_charge_energy").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_BATTERY_CHARGE_TODAY, Stream.of("BATT","today_batt_charge_energy").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_BATTERY_DC_POWER, Stream.of("BATT","dc_power").collect(Collectors.toList()), ChannelDatatypes.WATT);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_BATTERY_DISCHARGE_MONTH, Stream.of("BATT","month_batt_discharge_energy").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_BATTERY_DISCHARGE_TODAY, Stream.of("BATT","today_batt_discharge_enery").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_BATTERY_SAFETY_SOC, Stream.of("BATT","safety_soc").collect(Collectors.toList()), ChannelDatatypes.PERCENTAGE);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_BATTERY_SOC, Stream.of("BATT","soc").collect(Collectors.toList()), ChannelDatatypes.PERCENTAGE);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_BATTERY_STATUS, Stream.of("BATT","status").collect(Collectors.toList()), ChannelDatatypes.STRING);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_BATTERY_WINTER_SETTING, Stream.of("BATT","winter_setting").collect(Collectors.toList()), ChannelDatatypes.ONOFF);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_BATTERY_WINTER_STATUS, Stream.of("BATT","winter_status").collect(Collectors.toList()), ChannelDatatypes.ONOFF);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_GRID_ACTIVE_POWER, Stream.of("GRID","active_power").collect(Collectors.toList()), ChannelDatatypes.WATT);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_GRID_APHASE, Stream.of("GRID","a_phase").collect(Collectors.toList()), ChannelDatatypes.VOLT);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_GRID_FEEDIN_MONTH, Stream.of("GRID","month_grid_feed_in_energy").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_GRID_FEEDIN_TODAY, Stream.of("GRID","today_grid_feed_in_energy").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_GRID_FREQUENCY, Stream.of("GRID","freq").collect(Collectors.toList()), ChannelDatatypes.FREQUENCY);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_GRID_CONSUMPTION_MONTH, Stream.of("GRID","month_grid_power_purchase_energy").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_GRID_CONSUMPTION_TODAY, Stream.of("GRID","today_grid_power_purchase_energy").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_LOAD_BATTERY_CONSUMPTION_MONTH, Stream.of("LOAD","month_batt_discharge_energy").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_LOAD_BATTERY_CONSUMPTION_TODAY, Stream.of("LOAD","today_batt_discharge_enery").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_LOAD_CONSUMPTION_MONTH, Stream.of("LOAD","month_load_consumption_sum").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_LOAD_CONSUMPTION_TODAY, Stream.of("LOAD","today_load_consumption_sum").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_LOAD_GRID_CONSUMPTION_MONTH, Stream.of("LOAD","month_grid_power_purchase_energy").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_LOAD_GRID_CONSUMPTION_TODAY, Stream.of("LOAD","today_grid_power_purchase_energy").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_LOAD_POWER, Stream.of("LOAD","load_power").collect(Collectors.toList()), ChannelDatatypes.WATT);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_LOAD_PV_CONSUMPTION_MONTH, Stream.of("LOAD","month_pv_direct_consumption_energy").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_LOAD_PV_CONSUMPTION_TODAY, Stream.of("LOAD","today_pv_direct_consumption_enegy").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_PCS_CO2_REDUCTION_MONTH, Stream.of("PCS","month_co2_reduction_accum").collect(Collectors.toList()), ChannelDatatypes.KILOGRAM);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_PCS_FEEDIN_LIMITATION, Stream.of("PCS","feed_in_limitation").collect(Collectors.toList()), ChannelDatatypes.PERCENTAGE);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_PCS_GRID_FEEDIN_MONTH, Stream.of("PCS","month_grid_feed_in_energy").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_PCS_GRID_FEEDIN_TODAY, Stream.of("PCS","today_grid_feed_in_energy").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR); 
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_PCS_OPERATION_MODE, Stream.of("PCS","operation_mode").collect(Collectors.toList()), ChannelDatatypes.STRING);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_PCS_PV_GENERATION_MONTH, Stream.of("PCS","month_pv_generation_sum").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_PCS_PV_GENERATION_TODAY, Stream.of("PCS","today_pv_generation_sum").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_PCS_SELF_CONSUMPTION_TODAY, Stream.of("PCS","today_self_consumption").collect(Collectors.toList()), ChannelDatatypes.PERCENTAGE);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_PCS_STATUS, Stream.of("PCS","pcs_stauts").collect(Collectors.toList()), ChannelDatatypes.STRING);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_PV_BRAND, Stream.of("PV","brand").collect(Collectors.toList()), ChannelDatatypes.STRING);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_PV_CAPACITY, Stream.of("PV","capacity").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_PV_GENERATION_MONTH, Stream.of("PV","today_month_pv_generation_sum").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_PV_GENERATION_TODAY, Stream.of("PV","today_pv_generation_sum").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_PV_STRING_1_AMPERAGE, Stream.of("PV","pv1_current").collect(Collectors.toList()), ChannelDatatypes.AMPERE);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_PV_STRING_1_POWER, Stream.of("PV","pv1_power").collect(Collectors.toList()), ChannelDatatypes.WATT);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_PV_STRING_1_VOLTAGE, Stream.of("PV", "pv1_voltage").collect(Collectors.toList()), ChannelDatatypes.VOLT);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_PV_STRING_2_AMPERAGE, Stream.of("PV","pv2_current").collect(Collectors.toList()), ChannelDatatypes.AMPERE);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_PV_STRING_2_POWER, Stream.of("PV","pv2_power").collect(Collectors.toList()), ChannelDatatypes.WATT);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_PV_STRING_2_VOLTAGE, Stream.of("PV", "pv2_voltage").collect(Collectors.toList()), ChannelDatatypes.VOLT);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_PV_STRING_3_AMPERAGE, Stream.of("PV", "pv3_current").collect(Collectors.toList()), ChannelDatatypes.AMPERE);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_PV_STRING_3_POWER, Stream.of("PV","pv3_power").collect(Collectors.toList()), ChannelDatatypes.WATT);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.COMMON, BindingConstants.COMMON_PV_STRING_3_VOLTAGE, Stream.of("PV", "pv3_voltage").collect(Collectors.toList()), ChannelDatatypes.VOLT);
        // NETWORK API
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.NETWORK, BindingConstants.NETWORK_TYPE, Stream.of("type").collect(Collectors.toList()), ChannelDatatypes.STRING);        
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.NETWORK, BindingConstants.NETWORK_SETTING, Stream.of("setting").collect(Collectors.toList()), ChannelDatatypes.STRING);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.NETWORK, BindingConstants.NETWORK_IP, Stream.of("ip").collect(Collectors.toList()), ChannelDatatypes.STRING);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.NETWORK, BindingConstants.NETWORK_MASK, Stream.of("mask").collect(Collectors.toList()), ChannelDatatypes.STRING);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.NETWORK, BindingConstants.NETWORK_GATEWAY, Stream.of("gateway").collect(Collectors.toList()), ChannelDatatypes.STRING);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.NETWORK, BindingConstants.NETWORK_DNS, Stream.of("dns").collect(Collectors.toList()), ChannelDatatypes.STRING);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.NETWORK, BindingConstants.NETWORK_CONNECTED, Stream.of("connected").collect(Collectors.toList()), ChannelDatatypes.INTERNET_CONNECTED);
        // BATTERY API
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.BATTERY, BindingConstants.BATTERY_WINTER_SETTING, Stream.of("winter_setting").collect(Collectors.toList()), ChannelDatatypes.ONOFF);        
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.BATTERY, BindingConstants.BATTERY_WINTER_STATUS, Stream.of("winter_status").collect(Collectors.toList()), ChannelDatatypes.ONOFF);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.BATTERY, BindingConstants.BATTERY_BACKUP_SETTING, Stream.of("backup_setting").collect(Collectors.toList()), ChannelDatatypes.ONOFF);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.BATTERY, BindingConstants.BATTERY_BACKUP_STATUS, Stream.of("backup_status").collect(Collectors.toList()), ChannelDatatypes.ONOFF);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.BATTERY, BindingConstants.BATTERY_ALG_SETTING, Stream.of("alg_setting").collect(Collectors.toList()), ChannelDatatypes.ONOFF);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.BATTERY, BindingConstants.BATTERY_SAFETY_SOC, Stream.of("safety_soc").collect(Collectors.toList()), ChannelDatatypes.PERCENTAGE);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.BATTERY, BindingConstants.BATTERY_BACKUP_SOC, Stream.of("backup_soc").collect(Collectors.toList()), ChannelDatatypes.PERCENTAGE);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.BATTERY, BindingConstants.BATTERY_START_DATE, Stream.of("startdate").collect(Collectors.toList()), ChannelDatatypes.STRING);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.BATTERY, BindingConstants.BATTERY_END_DATE, Stream.of("stopdate").collect(Collectors.toList()), ChannelDatatypes.STRING);
        // SYSTEMINFO API
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.SYSTEMINFO, BindingConstants.SYSTEMINFO_PMS_MODEL, Stream.of("pms", "model").collect(Collectors.toList()), ChannelDatatypes.STRING);        
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.SYSTEMINFO, BindingConstants.SYSTEMINFO_PMS_SERIAL, Stream.of("pms", "serialno").collect(Collectors.toList()), ChannelDatatypes.STRING);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.SYSTEMINFO, BindingConstants.SYSTEMINFO_PMS_AC_POWER_INPUT, Stream.of("pms", "ac_input_power").collect(Collectors.toList()), ChannelDatatypes.WATT);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.SYSTEMINFO, BindingConstants.SYSTEMINFO_PMS_AC_POWER_OUTPUT, Stream.of("pms", "ac_output_power").collect(Collectors.toList()), ChannelDatatypes.WATT);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.SYSTEMINFO, BindingConstants.SYSTEMINFO_PMS_INSTALL_DATE, Stream.of("pms", "install_date").collect(Collectors.toList()), ChannelDatatypes.ONOFF);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.SYSTEMINFO, BindingConstants.SYSTEMINFO_BATTERY_CAPACITY, Stream.of("batt", "capacity").collect(Collectors.toList()), ChannelDatatypes.PERCENTAGE);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.SYSTEMINFO, BindingConstants.SYSTEMINFO_BATTERY_INSTALL_DATE, Stream.of("batt", "install_date").collect(Collectors.toList()), ChannelDatatypes.STRING);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.SYSTEMINFO, BindingConstants.SYSTEMINFO_PMS_VERSION, Stream.of("version", "pms_version").collect(Collectors.toList()), ChannelDatatypes.STRING);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.SYSTEMINFO, BindingConstants.SYSTEMINFO_PMS_BUILD_DATE, Stream.of("version", "pms_build_date").collect(Collectors.toList()), ChannelDatatypes.STRING);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.SYSTEMINFO, BindingConstants.SYSTEMINFO_PCS_VERSION, Stream.of("version", "pcs_version").collect(Collectors.toList()), ChannelDatatypes.STRING);        
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.SYSTEMINFO, BindingConstants.SYSTEMINFO_BMS_VERSION, Stream.of("version", "bms_version").collect(Collectors.toList()), ChannelDatatypes.STRING);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.SYSTEMINFO, BindingConstants.SYSTEMINFO_BMS_UNIT_1_VERSION, Stream.of("version", "bms_unit1_version").collect(Collectors.toList()), ChannelDatatypes.STRING);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.SYSTEMINFO, BindingConstants.SYSTEMINFO_BMS_UNIT_2_VERSION, Stream.of("version", "bms_unit2_version").collect(Collectors.toList()), ChannelDatatypes.STRING);
        // HOME API
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_STATISTIC_PCS_PV_TOTAL_POWER, Stream.of("statistics", "pcs_pv_total_power").collect(Collectors.toList()), ChannelDatatypes.WATT);        
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_STATISTIC_BATTERY_CONVERSION_POWER, Stream.of("statistics", "batconv_power").collect(Collectors.toList()), ChannelDatatypes.WATT);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_STATISTIC_BATTERY_USE, Stream.of("statistics", "bat_use").collect(Collectors.toList()), ChannelDatatypes.STRING);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_STATISTIC_BATTERY_STATUS, Stream.of("statistics", "bat_status").collect(Collectors.toList()), ChannelDatatypes.ONOFF);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_STATISTIC_BATTERY_USER_SOC, Stream.of("statistics", "bat_user_soc").collect(Collectors.toList()), ChannelDatatypes.PERCENTAGE);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_STATISTIC_LOAD_POWER, Stream.of("statistics", "load_power").collect(Collectors.toList()), ChannelDatatypes.WATT);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_STATISTIC_LOAD_POWER_UI, Stream.of("statistics", "load_power_for_ui").collect(Collectors.toList()), ChannelDatatypes.WATT);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_STATISTIC_AC_POWER_OUTPUT, Stream.of("statistics", "ac_output_power").collect(Collectors.toList()), ChannelDatatypes.WATT);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_STATISTIC_LOAD_TODAY, Stream.of("statistics", "load_today").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_STATISTIC_GRID_POWER, Stream.of("statistics", "grid_power").collect(Collectors.toList()), ChannelDatatypes.WATT);        
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_STATISTIC_SELF_CONSUMPTION_TODAY, Stream.of("statistics", "current_day_self_consumption").collect(Collectors.toList()), ChannelDatatypes.PERCENTAGE);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_STATISTIC_PV_GENERATION, Stream.of("statistics", "current_pv_generation_sum").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_STATISTIC_GRID_FEEDIN, Stream.of("statistics", "current_grid_feed_in_energy").collect(Collectors.toList()), ChannelDatatypes.KILOWATT_HOUR);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_DIRECTION_IS_DIRECT_CONSUMPTION, Stream.of("direction", "is_direct_consuming_").collect(Collectors.toList()), ChannelDatatypes.SWITCH_ONE_ZERO);        
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_DIRECTION_IS_BATTERY_CHARGING, Stream.of("direction", "is_battery_charging_").collect(Collectors.toList()), ChannelDatatypes.SWITCH_ONE_ZERO);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_DIRECTION_IS_BATTERY_DISCHARGING, Stream.of("direction", "is_battery_discharging_").collect(Collectors.toList()), ChannelDatatypes.SWITCH_ONE_ZERO);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_DIRECTION_IS_GRID_FEEDIN, Stream.of("direction", "is_grid_selling_").collect(Collectors.toList()), ChannelDatatypes.SWITCH_ONE_ZERO);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_DIRECTION_IS_GRID_CONSUMPTION, Stream.of("direction", "is_grid_buying_").collect(Collectors.toList()), ChannelDatatypes.SWITCH_ONE_ZERO);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_DIRECTION_IS_BATTERY_CHARGING_FROM_GRID, Stream.of("direction", "is_charging_from_grid_").collect(Collectors.toList()), ChannelDatatypes.SWITCH_ONE_ZERO);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_DIRECTION_IS_BATTERY_DISCHARGING_TO_GRID, Stream.of("direction", "is_discharging_to_grid_").collect(Collectors.toList()), ChannelDatatypes.SWITCH_ONE_ZERO);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_OPERATION_STATUS, Stream.of("operation", "status").collect(Collectors.toList()), ChannelDatatypes.STRING);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_OPERATION_MODE, Stream.of("operation", "mode").collect(Collectors.toList()), ChannelDatatypes.STRING);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_OPERATION_PCS_STANDBY, Stream.of("operation", "pcs_standbymode").collect(Collectors.toList()), ChannelDatatypes.STRING);        
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_OPERATION_MODE_WINTER, Stream.of("wintermode", "winter_status").collect(Collectors.toList()), ChannelDatatypes.ONOFF);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_OPERATION_MODE_BACKUP, Stream.of("wintermode", "backup_status").collect(Collectors.toList()), ChannelDatatypes.ONOFF);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_OPERATION_PCS_STATUS, Stream.of("pcs_fault", "pcs_status").collect(Collectors.toList()), ChannelDatatypes.STRING);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_HEATPUMP_ACTIVATE, Stream.of("heatpump", "heatpump_activate").collect(Collectors.toList()), ChannelDatatypes.ONOFF);        
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_HEATPUMP_TEMPERATURE, Stream.of("heatpump", "current_temp").collect(Collectors.toList()), ChannelDatatypes.TEMPERATURE);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_HEATPUMP_ENABLE, Stream.of("heatpump", "heatpump_enable").collect(Collectors.toList()), ChannelDatatypes.ONOFF);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_EV_CHARGER_ACTIVATE, Stream.of("evcharger", "ev_activate").collect(Collectors.toList()), ChannelDatatypes.ONOFF);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_EV_CHARGER_POWER, Stream.of("evcharger", "ev_power").collect(Collectors.toList()), ChannelDatatypes.WATT);
        addChannel(MODELS.D010KE1N211, Constants.VERSIONS.V1, Constants.STATES.HOME, BindingConstants.HOME_GRID_WAITING_TIME, Stream.of("gridWaitingTime").collect(Collectors.toList()), ChannelDatatypes.SECONDS);
    }
    
    private static void addChannel(MODELS inverter, BindingConfig config) {
        if (!CHANNEL_MAPPING.containsKey(inverter.name())) CHANNEL_MAPPING.put(inverter.name(), new LinkedList<BindingConfig>());
        List<BindingConfig> inverterChannels = CHANNEL_MAPPING.get(inverter.name());
        final Optional<BindingConfig> existingBindingConfig = inverterChannels.stream()
                        .filter(bc -> bc.version.equals(config.version) && bc.state.equals(config.state) && bc.channelUID.equals(config.channelUID)).findFirst();
        if (!existingBindingConfig.isPresent()) inverterChannels.add(config);
    }

    private static void addChannel(MODELS inverter, Constants.VERSIONS version, Constants.STATES state, String channelUID, List<String> path, ChannelDatatypes dataType) {
        addChannel(inverter, new BindingConfig(version, state, channelUID, path, dataType));
    }

    public static List<BindingConfig> getBindingConfigs(String inverter, Constants.VERSIONS version, Constants.STATES state) {
        if (CHANNEL_MAPPING.containsKey(inverter))
            return CHANNEL_MAPPING.get(inverter).stream().filter(bc -> bc.version.equals(version) && bc.state.equals(state)).collect(Collectors.toList());
        return new LinkedList<BindingConfig>();
    }
}