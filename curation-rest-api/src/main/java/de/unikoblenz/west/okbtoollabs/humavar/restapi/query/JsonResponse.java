package de.unikoblenz.west.okbtoollabs.humavar.restapi.query;

import com.google.gson.JsonObject;

/**
 * okb-toollabs
 * Created by Alex on 03.05.17.
 */
public abstract class JsonResponse {

    int statusCode;
    JsonObject body;

    JsonObject getBody() { return body; }
    int getStatusCode() {return statusCode; }
}
