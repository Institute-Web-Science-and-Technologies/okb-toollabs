package de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.details.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * okb-toollabs
 * Created by Alex on 19.04.17.
 */
public class DetailedItemMapper {

    private JsonObject object;

    public DetailedItemMapper(JsonObject wikidataResult) {
        object = wikidataResult;
    }

    public DetailedItem mapToItem(String itemId) throws EntityMissingException, InvalidEntityIdException {
        if (!Utils.isValidItemId(itemId))
            throw new InvalidEntityIdException(String.format("%s is not a valid item ID.", itemId), itemId);

        if (object.get("entities") == null
                || object.get("entities").getAsJsonObject().get(itemId) == null
                || object.get("entities").getAsJsonObject().get(itemId).getAsJsonObject().get("missing") != null)
            throw new EntityMissingException(String.format("no item %s found in provided Wikidata result", itemId), itemId);

        JsonObject wdItem = object.get("entities").getAsJsonObject().get(itemId).getAsJsonObject();
        // get numeric item ID
        int numItemId = Integer.parseInt(itemId.substring(1));
        // get English label
        String label = wdItem.get("labels").getAsJsonObject().get("en").getAsJsonObject().get("value").getAsString();
        // get English aliases
        List<String> aliases = new ArrayList<>();
        for (JsonElement alias : wdItem.get("aliases").getAsJsonObject().get("en").getAsJsonArray())
            aliases.add(alias.getAsJsonObject().get("value").getAsString());
        // get English description
        String description = wdItem.get("descriptions").getAsJsonObject().get("en").getAsJsonObject().get("value").getAsString();
        // get statement groups of item
        List<DetailedStatementGroup> statementGroups = new ArrayList<>();
        for (Map.Entry<String, JsonElement> entry : wdItem.get("claims").getAsJsonObject().entrySet())
            statementGroups.add(mapToStatementGroup(numItemId, entry.getKey(), wdItem));
        // TODO: Add URL construction from configuration.
        String url = String.format("details/%d", numItemId);

        return new DetailedItem(numItemId, label, aliases, description, statementGroups, url);
    }

    public DetailedStatementGroup mapToStatementGroup(String itemId, String propertyId) throws EntityMissingException, InvalidEntityIdException {
        if (!Utils.isValidItemId(itemId))
            throw new InvalidEntityIdException(String.format("%s is not a valid item ID.", itemId), itemId);

        if (!Utils.isValidPropertyId(propertyId))
            throw new InvalidEntityIdException(String.format("%s is not a valid property ID.", propertyId), propertyId);

        if (object.get("entities") == null
                || object.get("entities").getAsJsonObject().get(itemId) == null
                || object.get("entities").getAsJsonObject().get(itemId).getAsJsonObject().get("missing") != null)
            throw new EntityMissingException(String.format("no item %s found in provided Wikidata result", itemId), itemId);

        if (object.get("entities").getAsJsonObject().get(itemId).getAsJsonObject().get("claims").getAsJsonObject().get(propertyId) == null)
            throw new EntityMissingException(String.format("no property %s found in item %s in provided Wikidata result", propertyId, itemId), propertyId);

        JsonObject item = object.get("entities").getAsJsonObject().get(itemId).getAsJsonObject();

        return mapToStatementGroup(Integer.parseInt(itemId.substring(1)), propertyId, item);
    }

    private DetailedStatementGroup mapToStatementGroup(int itemId, String propertyId, JsonObject wdItem) {
        int numPropertyId = Integer.parseInt(propertyId.substring(1));
        // TODO: Add retrieval of property labels.
        List<DetailedStatement> statements = new ArrayList<>();
        for (JsonElement statement : wdItem.get("claims").getAsJsonObject().get(propertyId).getAsJsonArray())
            statements.add(mapToStatement(itemId, numPropertyId, statement.getAsJsonObject()));
        // TODO: Add URL construction from configuration.
        String url = String.format("details/%d/%d", itemId, numPropertyId);
        return new DetailedStatementGroup(itemId, numPropertyId, null, statements, url);
    }

    private DetailedStatement mapToStatement(int itemId, int propertyId, JsonObject claim) {
        // get statement UID
        String uid = claim.get("id").getAsString();
        // get mainsnak
        DetailedSnak snak = mapToSnak(claim.get("mainsnak").getAsJsonObject());
        // get qualifiers
        List<DetailedQualifier> qualifiers = new ArrayList<>();
        if (claim.get("qualifiers") != null)
            for (Map.Entry<String, JsonElement> entry : claim.get("qualifiers").getAsJsonObject().entrySet())
                for (JsonElement qualifier : entry.getValue().getAsJsonArray())
                    qualifiers.add(mapToQualifier(qualifier.getAsJsonObject()));
        // get references
        List<DetailedReference> references = new ArrayList<>();
        if (claim.get("references") != null)
            for (JsonElement reference : claim.get("references").getAsJsonArray())
                references.add(mapToReference(reference.getAsJsonObject()));
        // get rank of statement
        Ranks rank = Ranks.valueOf(claim.get("rank").getAsString());

        return new DetailedStatement(itemId, propertyId, uid, snak, qualifiers, references, rank);
    }

    private DetailedReference mapToReference(JsonObject reference) {
        // get snaks
        List<DetailedSnak> snaks = new ArrayList<>();
        for (Map.Entry<String, JsonElement> entry : reference.get("snaks").getAsJsonObject().entrySet())
            for (JsonElement snak : entry.getValue().getAsJsonArray())
                snaks.add(mapToSnak(snak.getAsJsonObject()));
        // get snaks-order
        List<Integer> snakOrder = new ArrayList<>();
        for (JsonElement property : reference.get("snaks-order").getAsJsonArray())
            snakOrder.add(Integer.parseInt(property.getAsString().substring(1)));
        return new DetailedReference(snaks, snakOrder);
    }

    private DetailedQualifier mapToQualifier(JsonObject qualifier) {
        DetailedSnak snak = mapToSnak(qualifier);
        return new DetailedQualifier(snak);
    }

    private DetailedSnak mapToSnak(JsonObject mainsnak) {
        // get property ID of snak
        int propertyId = Integer.parseInt(mainsnak.get("property").getAsString().substring(1));
        // TODO: Retrieve property label.
        // get datatype of snak
        String dataType = mainsnak.get("datatype").getAsString();
        // get snaktype to check if there is a value
        String snaktype = mainsnak.get("snaktype").getAsString();
        // get the datavalue object if snaketype==value, else set it as null.
        JsonObject dataValue = null;
        if (snaktype.equals("value")) {
            dataValue = mainsnak.get("datavalue").getAsJsonObject();
            // TODO: Retrieve item or property label, if data type fits.
        }
        return new DetailedSnak(propertyId, null, dataType, dataValue);
    }
}
