package de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata;

import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.details.DetailedReference;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.details.DetailedSnak;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.details.DetailedStatement;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.details.DetailedStatementGroup;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.summary.SourceHistogramEntry;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * Checks whether a given string is a valid Wikidata property ID.
     * @param propertyId possible property ID
     * @return true, if valid, else, false
     */
    public static boolean isValidPropertyId(String propertyId) { return propertyId.matches("^P\\d+$"); }

    /**
     * Checks whether a given string is a valid Wikidata entity ID.
     * [QP][0-9]*
     * @param entityId possible entity ID
     * @return true if valid, else false
     */
    public static boolean isValidEntityId(String entityId) { return entityId.matches("^[QP]\\d+$"); }

    public static List<SourceHistogramEntry> sourceHistogram(DetailedStatementGroup statementGroup) {
        Map<String, Integer> sourceCountMap = new HashMap<>();

        for (DetailedStatement statement : statementGroup.statements)
            for (DetailedReference reference : statement.references)
                for (DetailedSnak snak : reference.snaks)
                    if (snak.dataType.equals("url")) {
                        String host = URI.create(snak.dataValue.get("value").getAsString()).getHost();
                        sourceCountMap.put(host, sourceCountMap.getOrDefault(host, 0) + 1);
                    }

        List<SourceHistogramEntry> sourceHistogram = new ArrayList<>();
        sourceCountMap.forEach((host, count)->sourceHistogram.add(new SourceHistogramEntry(host, count)));
        return sourceHistogram;
    }

    public static List<SourceHistogramEntry> combineSourceHistograms(List<List<SourceHistogramEntry>> sourceHistograms) {
        Map<String, Integer> sourceCountMap = new HashMap<>();
        for (List<SourceHistogramEntry> sourceHistogram : sourceHistograms)
            for (SourceHistogramEntry entry : sourceHistogram)
                sourceCountMap.put(entry.source, sourceCountMap.getOrDefault(entry.source, 0) + entry.count);

        List<SourceHistogramEntry> sourceHistogram = new ArrayList<>();
        sourceCountMap.forEach((host, count)->sourceHistogram.add(new SourceHistogramEntry(host, count)));
        return sourceHistogram;
    }
}
