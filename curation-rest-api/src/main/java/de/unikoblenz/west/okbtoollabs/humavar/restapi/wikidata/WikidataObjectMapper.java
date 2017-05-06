package de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata;

import com.google.gson.JsonObject;

/**
 * okb-toollabs
 * Created by Alex on 06.05.2017 at 01:37.
 */
public interface WikidataObjectMapper<I, S> {

    public I mapToItem(String itemId) throws EntityMissingException, InvalidEntityIdException;
    public S mapToStatementGroup(String itemId, String projectId) throws EntityMissingException, InvalidEntityIdException;

}
