package de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.details;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * okb-toollabs
 * Created by Alex on 10.04.2017 at 13:59.
 */
public class DetailedItem {

    @SerializedName("item_id")
    public final int itemId;
    @SerializedName("label")
    public final String label;
    @SerializedName("aliases")
    public final List<String> aliases;
    @SerializedName("description")
    public final String description;
    @SerializedName("statement_groups")
    public final List<DetailedStatementGroup> statementGroups;
    @SerializedName("url")
    public final String url;

    public DetailedItem(int itemId, String label, List<String> aliases, String description, List<DetailedStatementGroup> statementGroups, String url) {
        this.itemId = itemId;
        this.label = label;
        this.aliases = aliases;
        this.description = description;
        this.statementGroups = statementGroups;
        this.url = url;
    }
}
