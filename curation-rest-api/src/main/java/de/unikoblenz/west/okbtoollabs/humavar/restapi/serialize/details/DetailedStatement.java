package de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.details;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * okb-toollabs
 * Created by Alex on 10.04.2017 at 14:02.
 */
public class DetailedStatement {

    @SerializedName("item_id")
    public final int itemId;
    @SerializedName("property_id")
    public final int propertyId;
    @SerializedName("statement_uid")
    public final String statementUid;
    @SerializedName("snak")
    public final DetailedSnak snak;
    @SerializedName("qualifiers")
    public final List<DetailedQualifier> qualifiers;
    @SerializedName("references")
    public final List<DetailedReference> references;
    @SerializedName("rank")
    public final Ranks rank;

    public DetailedStatement(int itemId, int propertyId, String statementUid, DetailedSnak snak, List<DetailedQualifier> qualifiers, List<DetailedReference> references, Ranks rank) {
        this.itemId = itemId;
        this.propertyId = propertyId;
        this.statementUid = statementUid;
        this.snak = snak;
        this.qualifiers = qualifiers;
        this.references = references;
        this.rank = rank;
    }
}
