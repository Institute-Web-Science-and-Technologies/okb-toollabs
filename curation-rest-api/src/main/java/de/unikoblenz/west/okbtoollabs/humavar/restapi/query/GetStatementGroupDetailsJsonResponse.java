package de.unikoblenz.west.okbtoollabs.humavar.restapi.query;

import de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata.DetailsMapper;
import spark.Request;

/**
 * Created by alex on 03.05.17.
 */
public class GetStatementGroupDetailsJsonResponse extends GetWikidataStatementGroupJsonResponse {

    public GetStatementGroupDetailsJsonResponse(Request req, String apiHostUrl) {
        super(req, DetailsMapper::new, apiHostUrl);
    }
}
