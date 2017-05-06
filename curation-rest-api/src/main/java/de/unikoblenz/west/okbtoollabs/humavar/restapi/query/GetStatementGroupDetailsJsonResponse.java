package de.unikoblenz.west.okbtoollabs.humavar.restapi.query;

import com.google.gson.Gson;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.errors.BadRequestError;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.errors.InternalServerError;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.errors.NotFoundError;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata.DetailsMapper;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata.EntityMissingException;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata.InvalidEntityIdException;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata.WikidataGetAccessor;
import spark.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 03.05.17.
 */
public class GetStatementGroupDetailsJsonResponse extends GetWikidataStatementGroupJsonResponse {

    public GetStatementGroupDetailsJsonResponse(Request req, String apiHostUrl) {
        super(req, DetailsMapper::new, apiHostUrl);
    }
}
