package de.unikoblenz.west.okbtoollabs.humavar.restapi;

import spark.Spark;
import spark.servlet.SparkApplication;

/**
 * okb-toollabs
 * Created by Alex on 02.05.2017 at 17:41.
 */
public class CurationEndpoint implements SparkApplication {

    @Override
    public void init() {
        Spark.get("/details/:item_id/:property_id", (req, res) -> "not implemented");

        Spark.get("/details/:item_id", (req, res) -> "not implemented");

        Spark.get("/summary/:item_id/:property_id", (req, res) -> "not implemented");

        Spark.get("/summary/:item_id", (req, res) -> "not implemented");

    }
}
