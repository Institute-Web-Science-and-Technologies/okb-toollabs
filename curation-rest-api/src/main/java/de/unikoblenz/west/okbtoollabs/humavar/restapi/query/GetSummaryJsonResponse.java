package de.unikoblenz.west.okbtoollabs.humavar.restapi.query;

import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;

/**
 * Created by alex on 03.05.17.
 */
public class GetSummaryJsonResponse implements JsonResponse {
    public GetSummaryJsonResponse(Request req) {

    }

    @Override
    public JsonObject getBody() {
        return null;
    }

    @Override
    public int getStatusCode() {
        return 0;
    }
}
