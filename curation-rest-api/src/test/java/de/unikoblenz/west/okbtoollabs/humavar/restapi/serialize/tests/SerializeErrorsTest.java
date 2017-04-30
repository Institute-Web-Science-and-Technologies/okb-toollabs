package de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.tests;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.junit.Before;
import org.junit.Test;

/**
 * okb-toollabs
 * Created by Alex on 16.04.2017 at 17:04.
 */
public class SerializeErrorsTest {

    @Before
    public void init() {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
    }

    @Test
    public void testBadRequestErrorSerialization() {
        // TODO: Implement test.
    }

    @Test
    public void testNotFoundErrorSerialization() {
        // TODO: Implement test.
    }
}
