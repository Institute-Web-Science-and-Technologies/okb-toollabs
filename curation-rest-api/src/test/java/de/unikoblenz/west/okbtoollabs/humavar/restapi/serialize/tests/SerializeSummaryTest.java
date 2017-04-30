package de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.tests;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.junit.Before;
import org.junit.Test;

/**
 * okb-toollabs
 * Created by Alex on 16.04.2017 at 17:01.
 */
public class SerializeSummaryTest {

    @Before
    public void init() {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
    }

    @Test
    public void testSourceHistogramEntrySerialization() {
        // TODO: implement test.
    }

    @Test
    public void testSummarizedStatementGroupSerialization() {
        // TODO: implement test.
    }

    @Test
    public void testSummarizedItemSerialization() {
        // TODO: Implement test.
    }
}
