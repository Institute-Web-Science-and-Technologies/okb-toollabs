package de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata;

/**
 * okb-toollabs
 * Created by Alex on 19.04.17.
 */
public class EntityMissingException extends Exception {

    private String missingEntityId;

    EntityMissingException(String message, String missingEntityId) {
        super(message);
        this.missingEntityId = missingEntityId;
    }

    public String getMissingEntityId() { return missingEntityId; }
}
