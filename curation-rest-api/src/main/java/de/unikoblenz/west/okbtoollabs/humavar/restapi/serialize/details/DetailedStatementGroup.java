package de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.details;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * okb-toollabs
 * Created by Alex on 10.04.2017 at 13:59.
 */
public class DetailedStatementGroup {
    @SerializedName("item_id")
    public final int itemId;
    @SerializedName("property_id")
    public final int propertyId;
    @SerializedName("property_label")
    public final String propertyLabel;
    @SerializedName("statements")
    public final List<DetailedStatement> statements;
    @SerializedName("url")
    public final String url;

    public DetailedStatementGroup(int itemId, int propertyId, String propertyLabel, List<DetailedStatement> statements, String url) {
        this.itemId = itemId;
        this.propertyId = propertyId;
        this.propertyLabel = propertyLabel;
        this.statements = statements;
        this.url = url;
    }
}
