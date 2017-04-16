package de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.details;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * okb-toollabs
 * Created by Alex on 10.04.2017 at 14:03.
 */
public class DetailedSnak {

    @SerializedName("property_id")
    public final int propertyId;
    @SerializedName("property_label")
    public final String propertyLabel;
    @SerializedName("datatype")
    public final String dataType;
    @SerializedName("datavalue")
    public final JsonObject dataValue;

    public DetailedSnak(int propertyId, String propertyLabel, String dataType, JsonObject dataValue) {
        this.propertyId = propertyId;
        this.propertyLabel = propertyLabel;
        this.dataType = dataType;
        this.dataValue = dataValue;
    }
}
