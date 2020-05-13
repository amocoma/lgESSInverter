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
package org.openhab.binding.lginverter.internal.handler.http;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.binding.lginverter.internal.BindingConstants;
import org.openhab.binding.lginverter.internal.handler.http.model.EndpointConfiguration;

/**
 * The {@link BindingConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Dirk Gronert - Initial contribution
 */
@NonNullByDefault
public class Constants {

    public enum VERSIONS {V1}

    private static final String URL_PREFIX = "https://%1$s";

    private static final String URL_LOGIN = URL_PREFIX + "/%2$s/user/setting/login";
    private static final String URL_TIMESYNC = URL_PREFIX + "/%2$s/user/setting/timesync";

    private static final String URL_NETWORK = URL_PREFIX + "/%2$s/user/setting/network";
    private static final String URL_SYSTEMINFO = URL_PREFIX + "/%2$s/user/setting/systeminfo";
    private static final String URL_BATTERY = URL_PREFIX + "/%2$s/user/setting/batt";
    private static final String URL_HOME = URL_PREFIX + "/%2$s/user/essinfo/home";
    private static final String URL_COMMON = URL_PREFIX + "/%2$s/user/essinfo/common";

    private static final String URL_OPERATION = URL_PREFIX + "/%2$s/user/operation/status"; // switch on off
    
    /**
     * def switch_on(self):
        """
        switch on operation.
        :return:
        """
        r = requests.put(SWITCH_URL.format(self.ip), json={"auth_key": self.auth_key, "operation": "start"},
                         verify=False, headers={"Content-Type": "application/json"})

    def switch_off(self):
        """
        switch off operation.
        :return:
        """
        r = requests.put(SWITCH_URL.format(self.ip), json={"auth_key": self.auth_key, "operation": "stop"},
                         verify=False, headers={"Content-Type": "application/json"})
        # if not r.json()["status"] == "success":
        #    raise ESSException("switching unsuccessful")
     */

    /**
    def set_batt_settings(self, command):
        command.update({"auth_key": self.auth_key})
        requests.put(BATT_URL.format(self.ip), json=command, verify=False)
    
    **/
    
    /**
    def winter_off(self):  # pragma: no cover
        """
        switch off winter mode
        :return:
        """
        self.set_batt_settings({"wintermode": "off"})
    
    def winter_on(self):  # pragma:no cover
        """
        switch off winter mode
        :return:
        """
        self.set_batt_settings({"wintermode": "on"})
    **/
    
    /**
    def fastcharge_on(self):  # pragma:no cover
        """
        switch off winter mode
        :return:
        """
        self.set_batt_settings({"alg_setting": "on"})

    def fastcharge_off(self):  # pragma:no cover
        """
        switch off winter mode
        :return:
        """
        self.set_batt_settings({"alg_setting": "off"})
     */
    public enum COMMON {
        LOGIN(new EndpointConfiguration(URL_LOGIN, Stream.of(VERSIONS.V1).collect(Collectors.toList()))),
        TIMESYNC(new EndpointConfiguration(URL_TIMESYNC, Stream.of(VERSIONS.V1).collect(Collectors.toList())));
        
        public final EndpointConfiguration config;
 
        private COMMON(EndpointConfiguration config) {
            this.config = config;
        }

    }

    public enum STATES {
        NETWORK(new EndpointConfiguration("network", URL_NETWORK, Stream.of(VERSIONS.V1).collect(Collectors.toList()))),
        SYSTEMINFO(new EndpointConfiguration("systeminfo", URL_SYSTEMINFO, Stream.of(VERSIONS.V1).collect(Collectors.toList()))),
        BATTERY(new EndpointConfiguration("battery", URL_BATTERY, Stream.of(VERSIONS.V1).collect(Collectors.toList()))),
        HOME(new EndpointConfiguration("home", URL_HOME, Stream.of(VERSIONS.V1).collect(Collectors.toList()))),
        COMMON(new EndpointConfiguration("common", URL_COMMON, Stream.of(VERSIONS.V1).collect(Collectors.toList())));

        public final EndpointConfiguration config;
 
        private STATES(EndpointConfiguration config) {
            this.config = config;
        }

    }
}