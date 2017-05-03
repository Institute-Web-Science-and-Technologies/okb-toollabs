package de.unikoblenz.west.okbtoollabs.humavar.restapi.query;

import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;

/**
 * Created by alex on 03.05.17.
 */
public class JsonResponseFactory {

    public JsonObject buildResponse(Request req, Response res, RequestType type, String apiHostUrl) {
        JsonResponse response;
        switch (type) {
            case GET_ITEM_DETAILS:
                response = new GetItemDetailsJsonResponse(req, apiHostUrl);
                break;
            default:
                throw new IllegalArgumentException("Unknown/Not implemented type given to buildResponse");
        }
        res.status(response.getStatusCode());
        return response.getBody();
    }

}
