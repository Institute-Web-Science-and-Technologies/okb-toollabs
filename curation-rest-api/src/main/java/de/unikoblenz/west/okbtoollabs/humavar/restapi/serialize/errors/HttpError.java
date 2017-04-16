package de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.errors;

import com.google.gson.annotations.SerializedName;

/**
 * okb-toollabs
 * Created by Alex on 16.04.2017 at 15:59.
 */
public abstract class HttpError {
    @SerializedName("error_message")
    public final String errorMessage;

    protected HttpError(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
