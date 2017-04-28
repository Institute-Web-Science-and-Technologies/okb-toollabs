package de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata;

/**
 * okb-toollabs
 * Created by Alex on 19.04.17.
 */
public class InvalidEntityIdException extends Exception {

    private String invalidEntityId;

    InvalidEntityIdException(String message, String invalidEntityId) {
        super(message);
        this.invalidEntityId = invalidEntityId;
    }

    public String getInvalidEntityId() { return invalidEntityId; }
}
