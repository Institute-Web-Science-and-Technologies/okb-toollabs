package de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata;

/**
 * okb-toollabs
 * Created by Alex on 19.04.17.
 */
public class Utils {

    /**
     * Checks whether a given string is a valid Wikidata item ID.
     * A Wikidata item ID has the following form: Q[0-9]+
     * @param itemId possible item ID
     * @return true if valid, else false
     */
    public static boolean isValidItemId(String itemId) {
        return itemId.matches("^Q\\d+$");
    }

    /**
     * Checks whether a given string is a valid Wikidata entity ID.
     * [QP][0-9]*
     * @param entityId possible entity ID
     * @return true if valid, else false
     */
    public static boolean isValidEntityId(String entityId) { return entityId.matches("^[QP]\\d+$"); }

}
