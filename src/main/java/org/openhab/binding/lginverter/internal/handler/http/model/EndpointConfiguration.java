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
package org.openhab.binding.lginverter.internal.handler.http.model;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.lginverter.internal.config.InverterConfiguration;
import org.openhab.binding.lginverter.internal.handler.http.Constants.VERSIONS;

/**
 * The {@link EndpointConfiguration} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Dirk Gronert - Initial contribution
 */
@NonNullByDefault
public class EndpointConfiguration {
    public @Nullable String state;
    public String endpoint;
    public List<VERSIONS> versionValidity;
    
    public EndpointConfiguration(String endpoint, List<VERSIONS> versionValidity) {
        this.state = null;
        this.endpoint = endpoint;
        this.versionValidity = versionValidity;
    }

    public EndpointConfiguration(String state, String endpoint, List<VERSIONS> versionValidity) {
        this.state = state;
        this.endpoint = endpoint;
        this.versionValidity = versionValidity;
    }

    public Boolean isActive(InverterConfiguration config) {
        if (this.state != null) {
            switch (this.state) {
                case "common":
                    return config.useCommonInformation;
                case "home":
                    return config.useHomeInformation;
                case "battery":
                    return config.useBatteryInformation;
                case "network":
                    return config.useNetworkInformation;
                case "systeminfo":
                    return config.useSystemInformation;
                default:
                    return false;
            }                
        }
        return false;
    }
    public Boolean isValid(String version) {
        final String _version = version.toLowerCase();
        return this.versionValidity.stream().filter(_v -> _v.name().toLowerCase().equals(_version)).findFirst().isPresent();
    }

    public Boolean isActiveAndValid(InverterConfiguration config) {
        return this.isActive(config) && this.isValid(config.version);
    }


    public int getRefreshInternal(InverterConfiguration config){
        if (this.state != null) {
            switch (this.state) {
                case "common":
                    return config.commonRefreshInternalInSeconds;
                case "home":
                    return config.homeRefreshInternalInSeconds;
                case "battery":
                    return config.batteryRefreshInternalInSeconds;
                case "network":
                    return config.networkRefreshInternalInSeconds;
                case "system":
                    return config.systemRefreshInternalInSeconds;
                default:
                    return 30;
            }                
        }
        return 30;
    }

    public String getEndpoint(@NonNull InverterConfiguration config, @Nullable String ipAddress) {
        return String.format(this.endpoint, ipAddress, config.version.toLowerCase());
        //if (this.isValid(version)) {
        // }
    }
}