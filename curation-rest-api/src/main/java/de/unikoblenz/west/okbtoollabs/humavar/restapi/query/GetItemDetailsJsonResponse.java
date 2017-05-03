package de.unikoblenz.west.okbtoollabs.humavar.restapi.query;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
 * okb-toollabs
 * Created by Alex on 03.05.17.
 */
public class GetItemDetailsJsonResponse implements JsonResponse {

    private int statusCode;
    private JsonObject body;

    public GetItemDetailsJsonResponse(Request req, String apiHostUrl) {
        body = null;

        Gson gson = new Gson();

        String itemId = req.params(":item_id");

        List<String> entityIds = new ArrayList<>();
        entityIds.add(String.format("Q%s", itemId));
        try {
            DetailsMapper mapper = new DetailsMapper(new WikidataGetAccessor(apiHostUrl).getEntities(entityIds));
            body = gson.toJsonTree(mapper.mapToItem(entityIds.get(0))).getAsJsonObject();
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

    @Override
    public JsonObject getBody() { return body; }

    @Override
    public int getStatusCode() {
        return statusCode;
    }
}
