package de.unikoblenz.west.okbtoollabs.humavar.restapi.query;

import de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata.SummaryMapper;
import spark.Request;

/**
 * obk-toollabs
 * Created by Alex on 03.05.17.
 */
public class GetStatementGroupSummaryJsonResponse extends GetWikidataStatementGroupJsonResponse {

    GetStatementGroupSummaryJsonResponse(Request req, String apiHostUrl) {
        super(req, SummaryMapper::new, apiHostUrl);
    }
}
