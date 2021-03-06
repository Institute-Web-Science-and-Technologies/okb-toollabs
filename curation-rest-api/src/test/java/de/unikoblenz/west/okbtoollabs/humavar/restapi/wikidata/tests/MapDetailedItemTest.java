package de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata.tests;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.details.DetailedItem;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.details.DetailedStatement;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.details.DetailedStatementGroup;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata.DetailsMapper;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata.EntityMissingException;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata.InvalidEntityIdException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

/**
 * okb-toollabs
 * Created by Alex on 19.04.17.
 */
public class MapDetailedItemTest {

    private JsonObject validCompleteResult;
    private JsonObject validMissingResult;
    private JsonObject emptyResult;

    @Before
    public void init() throws FileNotFoundException {
        JsonParser parser = new JsonParser();
        // Maybe this JSON object should be loaded from a file instead.
        validCompleteResult = parser.parse(new InputStreamReader(new FileInputStream(new File("test_data/Q42.json"))))
                .getAsJsonObject();
        validMissingResult = parser.parse(new InputStreamReader(new FileInputStream(new File("test_data/Q7.json"))))
                .getAsJsonObject();
        emptyResult = parser.parse("{}").getAsJsonObject();
    }

    @Test
    public void testValidExistingItemMapping() throws InvalidEntityIdException, EntityMissingException {
        DetailsMapper mapper = new DetailsMapper(validCompleteResult);
        DetailedItem result = mapper.mapToItem("Q42");

        assertEquals(42, result.itemId);
        assertEquals("Douglas Adams", result.label);
        assertEquals(4, result.aliases.size());
        assertEquals("English writer", result.description);
        assertEquals(96, result.statementGroups.size());
        assertEquals("details/42", result.url);

        DetailedStatementGroup instanceGroup = result.statementGroups.stream()
                .filter((DetailedStatementGroup sg) -> sg.propertyId == 31).findFirst().orElse(null);
        assertEquals(1, instanceGroup.statements.size());
        assertEquals("details/42/31", instanceGroup.url);

        DetailedStatement instanceStatement = instanceGroup.statements.get(0);
        assertEquals(2, instanceStatement.references.size());
        assertEquals(0, instanceStatement.qualifiers.size());
    }

    @Test(expected=EntityMissingException.class)
    public void testValidMissingItemMapping() throws InvalidEntityIdException, EntityMissingException {
        DetailsMapper mapper = new DetailsMapper(validMissingResult);
        mapper.mapToItem("Q7");
    }

    @Test(expected=EntityMissingException.class)
    public void testInvalidMissingItemMapping() throws InvalidEntityIdException, EntityMissingException {
        DetailsMapper mapper = new DetailsMapper(emptyResult);
        mapper.mapToItem("Q1");
    }

    @Test(expected=InvalidEntityIdException.class)
    public void testInvalidEntityIdItemMapping() throws InvalidEntityIdException, EntityMissingException {
        DetailsMapper mapper = new DetailsMapper(emptyResult);
        mapper.mapToItem("Q32.53");
    }
}
