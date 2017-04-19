package de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.tests;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.details.DetailedQualifier;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.details.DetailedReference;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.details.DetailedSnak;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * okb-toollabs
 * Created by Alex on 16.04.2017 at 16:05.
 */
public class SerializeDetailsTest {

    private Gson gson;
    private JsonParser parser;

    @Before
    public void init() {
        gson = new Gson();
        parser = new JsonParser();
    }

    @Test
    public void testDetailedSnakSerialization() {
        // Construct DetailedSnak.
        JsonObject dataValue = new JsonParser().parse("{ }").getAsJsonObject();
        DetailedSnak snak = new DetailedSnak(31, "instance of", "wikibase-item", dataValue);
        // Serialize DetailedSnak.
        JsonObject serializedSnak = parser.parse(gson.toJson(snak)).getAsJsonObject();
        // Test.
        assertEquals(31, serializedSnak.getAsJsonPrimitive("property_id").getAsInt());
        assertEquals("instance of", serializedSnak.getAsJsonPrimitive("property_label").getAsString());
        assertEquals("wikibase-item", serializedSnak.getAsJsonPrimitive("datatype").getAsString());
        assertNotNull(serializedSnak.getAsJsonObject("datavalue"));
    }

    @Test
    public void testDetailedQualifierSerialization() {
        // Construct DetailedQualifier.
        JsonObject dataValue = new JsonParser().parse("{ }").getAsJsonObject();
        DetailedSnak snak = new DetailedSnak(31, "instance of", "wikibase-item", dataValue);
        DetailedQualifier qualifier = new DetailedQualifier(snak);
        // Serialize DetailedQualifier.
        JsonObject serializedQualifier = parser.parse(gson.toJson(qualifier)).getAsJsonObject();
        // Test.
        assertNotNull(serializedQualifier.getAsJsonObject("snak"));
    }

    @Test
    public void testDetailedReferenceSerialization() {
        // Construct DetailedReference.
        JsonObject dataValue = new JsonParser().parse("{ }").getAsJsonObject();
        DetailedSnak snak = new DetailedSnak(31, "instance of", "wikibase-item", dataValue);
        List<DetailedSnak> snaks = new ArrayList<>();
        snaks.add(snak);
        List<Integer> snaksOrder = new ArrayList<>();
        snaksOrder.add(31);
        DetailedReference reference = new DetailedReference(snaks, snaksOrder);
        // Serialize DetailedReference.
        JsonObject serializedReference = parser.parse(gson.toJson(reference)).getAsJsonObject();
        // Test.
        assertEquals(1, serializedReference.getAsJsonArray("snaks").size());
        assertEquals(1, serializedReference.getAsJsonArray("snaks_order").size());
    }

    @Test
    public void testDetailedStatementSerialization() {
        // TODO: Implement test.
    }

    @Test
    public void testDetailedStatementGroupSerialization() {
        // TODO: Implement test.
    }

    @Test
    public void testDetailedItemSerialization() {
        // TODO: Implement test.
    }
}
