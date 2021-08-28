package io.blockfrost.sdk.api.exception;

public class APIException extends Exception {
    private int errorCode;

    public APIException(String message) {
        super(message);
    }

    public APIException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public APIException(String message, Exception exp) {
        super(message, exp);
    }

}
