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

import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.binding.lginverter.internal.BindingMapper;
import org.openhab.binding.lginverter.internal.ChannelDatatypes;
import org.openhab.binding.lginverter.internal.handler.http.Constants;


/**
 * The {@link BindingMapper} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Dirk Gronert - Initial contribution
 */
@NonNullByDefault
public class BindingConfig {
    public String channelUID;
    public List<String> path;
    public ChannelDatatypes dataType;
    public Constants.VERSIONS version;
    public Constants.STATES state;

    public BindingConfig(final Constants.VERSIONS version, final Constants.STATES state, final String channelUID, final List<String> path, final ChannelDatatypes dataType) {
        this.version = version;
        this.state = state;
        this.channelUID = state.name().toLowerCase() + "#" + channelUID;
        this.path = path;
        this.dataType = dataType;
    }
}