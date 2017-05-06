package de.unikoblenz.west.okbtoollabs.humavar.restapi.query;

import de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata.SummaryMapper;
import spark.Request;

/**
 * okb-toollabs
 * Created by Alex on 03.05.17.
 */
public class GetItemSummaryJsonResponse extends GetWikidataItemJsonResponse {

    public GetItemSummaryJsonResponse(Request req, String apiHostUrl) {
        super(req, SummaryMapper::new, apiHostUrl);
    }

}
