package de.unikoblenz.west.okbtoollabs.humavar.restapi.serialize.errors;

/**
 * Created by alex on 03.05.17.
 */
public class InternalServerError extends HttpError {
    public InternalServerError(String errorMessage) { super(errorMessage); }
}
