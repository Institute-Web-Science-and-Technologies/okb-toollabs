package de.unikoblenz.west.okbtoollabs.humavar.restapi;

import spark.Spark;
import spark.servlet.SparkApplication;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.options;

/**
 * okb-toollabs
 * Created by Alex on 02.05.2017 at 17:41.
 */
public class CurationEndpoint implements SparkApplication {

    @Override
    public void init() {
        // TODO: not secure, change parameters somehow.
        enableCORS("*", "*", "*");

        get("/details/:item_id/:property_id", (req, res) -> "not implemented");

        get("/details/:item_id", (req, res) -> "not implemented");

        get("/summary/:item_id/:property_id", (req, res) -> "not implemented");

        get("/summary/:item_id", (req, res) -> "not implemented");

    }

    // Copied from: http://sparkjava.com/tutorials/cors
    private static void enableCORS(final String origin, final String methods, final String headers) {

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });
    }
}
