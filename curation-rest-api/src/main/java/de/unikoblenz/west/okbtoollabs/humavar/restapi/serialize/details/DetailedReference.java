package de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.details;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * okb-toollabs
 * Created by Alex on 10.04.2017 at 14:03.
 */
public class DetailedReference {

    @SerializedName("snaks")
    public final List<DetailedSnak> snaks;
    @SerializedName("snaks_order")
    public final List<Integer> snakOrder;

    public DetailedReference(List<DetailedSnak> snaks, List<Integer> snakOrder) {
        this.snaks = snaks;
        this.snakOrder = snakOrder;
    }
}
