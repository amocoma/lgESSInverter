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
package org.openhab.binding.lginverter.internal.config;

/**
 * The {@link InverterConfiguration} class contains fields mapping thing configuration parameters.
 *
 * @author Dirk Gronert - Initial contribution
 */
public class InverterConfiguration {

    public String password;
    public String version;
    public String serial;
    public String ip;

    // common
    public Boolean useCommonInformation;
    public int commonRefreshInternalInSeconds;
    // system
    public Boolean useSystemInformation;
    public int systemRefreshInternalInSeconds;
    // battery
    public Boolean useBatteryInformation;
    public int batteryRefreshInternalInSeconds;
    // home
    public Boolean useHomeInformation;
    public int homeRefreshInternalInSeconds;
    // network
    public Boolean useNetworkInformation;
    public int networkRefreshInternalInSeconds;
}