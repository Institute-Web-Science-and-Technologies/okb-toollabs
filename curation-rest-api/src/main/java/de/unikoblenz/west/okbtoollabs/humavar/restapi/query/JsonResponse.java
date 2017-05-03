package de.unikoblenz.west.okbtoollabs.humavar.restapi.query;

import com.google.gson.JsonObject;

/**
 * Created by alex on 03.05.17.
 */
public interface JsonResponse {
    public JsonObject getBody();
    public int getStatusCode();
}
