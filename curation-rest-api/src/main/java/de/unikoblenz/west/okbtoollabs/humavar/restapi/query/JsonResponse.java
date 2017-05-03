package de.unikoblenz.west.okbtoollabs.humavar.restapi.query;

import com.google.gson.JsonObject;

/**
 * Created by alex on 03.05.17.
 */
public abstract class JsonResponse {

    int statusCode;
    JsonObject body;

    public JsonObject getBody() { return body; }
    public int getStatusCode() {return statusCode; }
}
