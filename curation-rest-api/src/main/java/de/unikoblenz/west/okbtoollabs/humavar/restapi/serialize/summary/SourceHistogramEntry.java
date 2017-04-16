package de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.summary;

import com.google.gson.annotations.SerializedName;

/**
 * okb-toollabs
 * Created by Alex on 16.04.2017 at 15:45.
 */
public class SourceHistogramEntry {
    @SerializedName("source")
    public final String source;
    @SerializedName("count")
    public final int count;

    public SourceHistogramEntry(String source, int count) {
        this.source = source;
        this.count = count;
    }
}
