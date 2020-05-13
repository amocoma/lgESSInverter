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
package org.openhab.binding.lginverter.internal.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonObject;

import org.openhab.binding.lginverter.internal.BindingConstants;

/**
 * The {@link BindingConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Dirk Gronert - Initial contribution
 */
public class TimesyncInfo {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public final String authKey;
    public final String by = "phone";
    public final String at;
    
    public TimesyncInfo(String authKey) {
        this.authKey = authKey;
        LocalDateTime localDateTime = LocalDateTime.now();
        this.at = localDateTime.format(formatter);
    }
    
    public JsonObject getESSTimesync(){
        JsonObject ts = new JsonObject();
        ts.addProperty("auth_key", this.authKey);
        ts.addProperty("by", this.by);
        ts.addProperty("date_time", this.at);
        return ts;
    }

}