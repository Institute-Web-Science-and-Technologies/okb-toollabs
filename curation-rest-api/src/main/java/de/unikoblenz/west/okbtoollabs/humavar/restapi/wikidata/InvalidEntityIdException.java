package de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata;

/**
 * okb-toollabs
 * Created by Alex on 19.04.17.
 */
public class InvalidEntityIdException extends Exception {
    InvalidEntityIdException(String message) {
        super(message);
    }
}
