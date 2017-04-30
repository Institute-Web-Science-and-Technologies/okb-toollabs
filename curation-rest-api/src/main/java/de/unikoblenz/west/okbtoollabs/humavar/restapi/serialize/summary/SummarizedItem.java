package de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.summary;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * okb-toollabs
 * Created by Alex on 16.04.2017 at 15:44.
 */
public class SummarizedItem {

    @SerializedName("item_id")
    public final int itemId;
    @SerializedName("label")
    public final String label;
    @SerializedName("description")
    public final String description;
    @SerializedName("property_count")
    public final int propertyCount;
    @SerializedName("reference_count")
    public final int referenceCount;
    @SerializedName("source_histogram")
    public final List<SourceHistogramEntry> sourceHistogram;
    @SerializedName("statement_groups")
    public final List<SummarizedStatementGroup> statementGroups;
    @SerializedName("url")
    public final String url;

    public SummarizedItem(int itemId, String label, String description, int propertyCount, int referenceCount,
                          List<SourceHistogramEntry> sourceHistogram, List<SummarizedStatementGroup> statementGroups, String url) {
        this.itemId = itemId;
        this.label = label;
        this.description = description;
        this.propertyCount = propertyCount;
        this.referenceCount = referenceCount;
        this.sourceHistogram = sourceHistogram;
        this.statementGroups = statementGroups;
        this.url = url;
    }
}
