package de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.details;

import com.google.gson.annotations.SerializedName;

/**
 * okb-toollabs
 * Created by Alex on 10.04.2017 at 14:02.
 */
public class DetailedQualifier {

    @SerializedName("snak")
    public final DetailedSnak snak;

    public DetailedQualifier(DetailedSnak snak) {
        this.snak = snak;
    }
}
