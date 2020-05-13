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

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.HttpVersion;
import org.openhab.binding.lginverter.internal.model.TimesyncInfo;

/**
 * The {@link HttpUtilities} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Dirk Gronert - Initial contribution
 */
public class HttpUtilities {

    // GSON handler
    private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    public static ContentResponse executeHttpPost(HttpClient httpClient,  @NonNull TimesyncInfo timesyncInfo, String url, JsonElement parameters) throws InterruptedException, TimeoutException, ExecutionException {
        Request response = httpClient.newRequest(url).scheme("https")
                .agent("Jetty HTTP client")
                .version(HttpVersion.HTTP_1_1)
                .method(HttpMethod.POST)
                .header(HttpHeader.ACCEPT, "application/json")
                .header(HttpHeader.CONTENT_TYPE, "application/json")
                .timeout(5, TimeUnit.SECONDS);
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("auth_key", timesyncInfo.authKey);
        if (parameters != null) {
            if (parameters.isJsonObject()) {
                for (Map.Entry<String, JsonElement> entry : parameters.getAsJsonObject().entrySet()) {
                    requestPayload.add(entry.getKey(), entry.getValue());
                }
            }
        }
        response.content(new StringContentProvider(requestPayload.toString()));
        return response.send();
    }
    /**
     * Helper to extract the JSON object from a HTTP response.
     * Use only, if you expect a JSON object and no other types (e.g. JSON array)!
     *
     * @param reponse the HTTP response
     * @return the JSON object
     */
    public static JsonObject getJsonObjectFromResponse(ContentResponse reponse) {
        return GSON.fromJson(reponse.getContentAsString(), JsonObject.class);
    }
}