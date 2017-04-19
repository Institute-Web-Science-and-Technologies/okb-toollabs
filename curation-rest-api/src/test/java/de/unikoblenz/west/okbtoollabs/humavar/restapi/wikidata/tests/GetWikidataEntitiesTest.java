package de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata.tests;

import com.google.gson.JsonObject;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata.InvalidEntityIdException;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata.WikidataGetAccessor;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * okb-toollabs
 * Created by Alex on 19.04.2017 at 22:08.
 */
public class GetWikidataEntitiesTest {

    private WikidataGetAccessor accessor;

    @Before
    public void init() {
        String hostUrl = "www.wikidata.org/w/api.php";
        accessor = new WikidataGetAccessor(hostUrl);
    }

    @Test
    public void testValidSingleEntityRequest() throws InvalidEntityIdException, IOException {
        List<String> validEntityId = new ArrayList<>();
        validEntityId.add("Q42");
        JsonObject result = accessor.getEntities(validEntityId);

        assertEquals("Q42", result.get("entities").getAsJsonObject().get("Q42").getAsJsonObject().get("id").getAsString());
    }
}
