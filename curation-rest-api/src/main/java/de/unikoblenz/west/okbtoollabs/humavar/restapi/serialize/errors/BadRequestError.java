package de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.errors;

import com.google.gson.annotations.SerializedName;

/**
 * okb-toollabs
 * Created by Alex on 16.04.2017 at 15:48.
 */
public class BadRequestError extends HttpError {
    @SerializedName("parameter")
    public final String parameter;

    public BadRequestError(String errorMessage, String parameter) {
        super(errorMessage);
        this.parameter = parameter;
    }
}
