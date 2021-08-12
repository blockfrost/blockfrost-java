package io.blockfrost.sdk.api.exception;

public class APIException extends Exception {

    public APIException(String message) {
        super(message);
    }

    public APIException(String message, Exception exp) {
        super(message, exp);
    }

}
