package de.unikoblenz.west.okbtoollabs.humavar.restapi.query;

import com.google.gson.JsonObject;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata.WikidataObjectMapper;
import spark.Request;

import java.util.function.Function;

/**
 * okb-toollabs
 * Created by Alex on 06.05.2017 at 01:34.
 */
abstract class GetWikidataItemJsonResponse extends GetWikidataObjectJsonResponse {

    GetWikidataItemJsonResponse(Request req, Function<JsonObject, WikidataObjectMapper> mapperConstructor, String apiHostUrl) {
        super(req, mapperConstructor, GetWikidataObjectJsonResponse.ITEM, apiHostUrl);
    }

}
