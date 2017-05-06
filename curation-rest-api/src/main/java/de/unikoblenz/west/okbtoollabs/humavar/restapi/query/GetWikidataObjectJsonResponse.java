package de.unikoblenz.west.okbtoollabs.humavar.restapi.query;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.errors.BadRequestError;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.errors.InternalServerError;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.errors.NotFoundError;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata.EntityMissingException;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata.InvalidEntityIdException;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata.WikidataGetAccessor;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata.WikidataObjectMapper;
import spark.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * okb-toollabs
 * Created by Alex on 06.05.2017 at 01:57.
 */
abstract class GetWikidataObjectJsonResponse extends JsonResponse {

    final static String STATEMENT_GROUP = "statement group";
    final static String ITEM = "item";

    GetWikidataObjectJsonResponse(Request req, Function<JsonObject, WikidataObjectMapper> mapperConstructor, String objectType, String apiHostUrl) {
        if (!objectType.equals(ITEM) && !objectType.equals(STATEMENT_GROUP))
            throw new IllegalArgumentException(String.format("%s is an invalid object type", objectType));

        body = null;

        Gson gson = new Gson();

        String itemId = req.params(":item_id");
        String propertyId = req.params(":property_id");

        List<String> entityIds = new ArrayList<>();
        entityIds.add(String.format("Q%s", itemId));
        try {
            WikidataObjectMapper mapper = mapperConstructor.apply(new WikidataGetAccessor(apiHostUrl).getEntities(entityIds));
            if (objectType.equals(ITEM))
                body = gson.toJsonTree(mapper.mapToItem(entityIds.get(0))).getAsJsonObject();
            else
                body = gson.toJsonTree(mapper.mapToStatementGroup(entityIds.get(0), String.format("P%s", propertyId))).getAsJsonObject();
            statusCode = 200;
        } catch (IOException e) {
            statusCode = 500;
            body = gson.toJsonTree(
                    new InternalServerError("Error in accessing Wikidata."))
                    .getAsJsonObject();
        } catch (InvalidEntityIdException e) {
            statusCode = 400;
            body = gson.toJsonTree(
                    new BadRequestError(e.getMessage(), e.getInvalidEntityId())
            ).getAsJsonObject();
        } catch (EntityMissingException e) {
            statusCode = 404;
            body = gson.toJsonTree(
                    new NotFoundError(e.getMessage(), Integer.parseInt(e.getMissingEntityId().substring(1)), "item")
            ).getAsJsonObject();
        }
    }
}
