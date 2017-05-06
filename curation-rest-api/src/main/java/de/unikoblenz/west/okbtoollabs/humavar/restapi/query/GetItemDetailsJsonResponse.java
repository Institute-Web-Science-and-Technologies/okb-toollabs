package de.unikoblenz.west.okbtoollabs.humavar.restapi.query;

import de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata.DetailsMapper;
import spark.Request;

/**
 * okb-toollabs
 * Created by Alex on 03.05.17.
 */
public class GetItemDetailsJsonResponse extends GetWikidataItemJsonResponse {

    public GetItemDetailsJsonResponse(Request req, String apiHostUrl) {
        super(req, DetailsMapper::new, apiHostUrl);
    }

}
