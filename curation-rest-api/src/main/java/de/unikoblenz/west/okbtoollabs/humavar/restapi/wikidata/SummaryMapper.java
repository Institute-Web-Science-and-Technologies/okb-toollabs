package de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata;

import com.google.gson.JsonObject;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.details.DetailedItem;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.details.DetailedStatementGroup;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.details.Ranks;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.summary.SourceHistogramEntry;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.summary.SummarizedItem;
import de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.summary.SummarizedStatementGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * okb-toollabs
 * Created by Alex on 29.04.2017 at 00:30.
 */
public class SummaryMapper {

    private DetailsMapper mapper;

    public SummaryMapper(JsonObject wikidataResult) {
        mapper = new DetailsMapper(wikidataResult);
    }

    public SummarizedItem mapToItem(String itemId) throws InvalidEntityIdException, EntityMissingException {
        DetailedItem detailedItem = mapper.mapToItem(itemId);

        int numItemId = detailedItem.itemId;
        String label = detailedItem.label;
        String description = detailedItem.description;
        int propertyCount = detailedItem.statementGroups.size();
        List<SummarizedStatementGroup> statementGroups = new ArrayList<>();
        for (DetailedStatementGroup statementGroup : detailedItem.statementGroups)
            statementGroups.add(mapToStatementGroup(statementGroup));
        int referenceCount = statementGroups.stream().mapToInt((stmt)->stmt.referenceCount).sum();
        List<SourceHistogramEntry> sourceHistogram = Utils.combineSourceHistograms(statementGroups.stream()
                .map((group)->group.sourceHistogram).collect(Collectors.toList()));
        // TODO: Add URL construction from configuration.
        String url = String.format("summary/%d", numItemId);

        return new SummarizedItem(numItemId, label, description, propertyCount, referenceCount, sourceHistogram, statementGroups, url);
    }

    public SummarizedStatementGroup mapToStatementGroup(String itemId, String propertyId) throws InvalidEntityIdException, EntityMissingException {
        DetailedStatementGroup detailedStatementGroup = mapper.mapToStatementGroup(itemId, propertyId);

        return mapToStatementGroup(detailedStatementGroup);
    }

    private SummarizedStatementGroup mapToStatementGroup(DetailedStatementGroup statementGroup) {
        int itemId = statementGroup.itemId;
        int propertyId = statementGroup.propertyId;
        String propertyLabel = statementGroup.propertyLabel;
        String dataType = statementGroup.statements.get(0).snak.dataType;
        int statementCount = statementGroup.statements.size();
        int referenceCount = statementGroup.statements.stream().mapToInt((stmt)->stmt.references.size()).sum();
        List<SourceHistogramEntry> sourceHistogram = Utils.sourceHistogram(statementGroup);
        int preferredCount = Math.toIntExact(statementGroup.statements.stream().filter((stmt)->stmt.rank == Ranks.preferred).count());
        int normalCount = Math.toIntExact(statementGroup.statements.stream().filter((stmt)->stmt.rank == Ranks.normal).count());
        int deprecatedCount = Math.toIntExact(statementGroup.statements.stream().filter((stmt)->stmt.rank == Ranks.deprecated).count());
        // TODO: Add URL construction from configuration.
        String url = String.format("summary/%d/%d", itemId, propertyId);

        return new SummarizedStatementGroup(itemId, propertyId, propertyLabel,
                dataType, statementCount, referenceCount, sourceHistogram, preferredCount, normalCount, deprecatedCount, url);
    }

}
