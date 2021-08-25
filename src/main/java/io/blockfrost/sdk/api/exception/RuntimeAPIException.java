package io.blockfrost.sdk.api.exception;

public class RuntimeAPIException extends RuntimeException {

    public RuntimeAPIException(Throwable cause) {
        super(cause);
    }

    public RuntimeAPIException(String message, Throwable cause) {
        super(message, cause);
    }

}
