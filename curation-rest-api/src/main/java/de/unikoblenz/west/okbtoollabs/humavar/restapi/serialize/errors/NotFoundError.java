package de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.errors;

import com.google.gson.annotations.SerializedName;

/**
 * okb-toollabs
 * Created by Alex on 16.04.2017 at 15:48.
 */
public class NotFoundError extends HttpError {
    @SerializedName("object_id")
    public final int objectId;
    @SerializedName("object_type")
    public final String objectType;

    public NotFoundError(String errorMessage, int objectId, String objectType) {
        super(errorMessage);
        this.objectId = objectId;
        this.objectType = objectType;
    }
}
