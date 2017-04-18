package de.unikoblenz.west.oktoollabs.humavar.restapi.wikidata;

/**
 * okb-toollabs
 * Created by Alex on 18.04.2017 at 23:33.
 */
public class InvalidEntityIdException extends Exception {

    InvalidEntityIdException(String message) {
        super(message);
    }
}
