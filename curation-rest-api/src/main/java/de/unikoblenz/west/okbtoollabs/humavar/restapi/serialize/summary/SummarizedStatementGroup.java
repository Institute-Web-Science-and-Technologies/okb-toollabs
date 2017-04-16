package de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.summary;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * okb-toollabs
 * Created by Alex on 16.04.2017 at 15:44.
 */
public class SummarizedStatementGroup {

    @SerializedName("item_id")
    public final int itemId;
    @SerializedName("property_id")
    public final int propertyId;
    @SerializedName("property_label")
    public final String propertyLabel;
    @SerializedName("data_type")
    public final String dataType;
    @SerializedName("statement_count")
    public final int statementCount;
    @SerializedName("reference_count")
    public final int referenceCount;
    @SerializedName("source_histogram")
    public final List<SourceHistogramEntry> sourceHistogram;
    @SerializedName("preferred_count")
    public final int preferredCount;
    @SerializedName("normal_count")
    public final int normalCount;
    @SerializedName("deprecated_count")
    public final int deprecatedCount;
    @SerializedName("url")
    public final String url;

    public SummarizedStatementGroup(int itemId, int propertyId, String propertyLabel, String dataType, int statementCount, int referenceCount, List<SourceHistogramEntry> sourceHistogram, int preferredCount, int normalCount, int deprecatedCount, String url) {
        this.itemId = itemId;
        this.propertyId = propertyId;
        this.propertyLabel = propertyLabel;
        this.dataType = dataType;
        this.statementCount = statementCount;
        this.referenceCount = referenceCount;
        this.sourceHistogram = sourceHistogram;
        this.preferredCount = preferredCount;
        this.normalCount = normalCount;
        this.deprecatedCount = deprecatedCount;
        this.url = url;
    }
}
