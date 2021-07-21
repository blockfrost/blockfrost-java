package io.blockfrost.sdk.api.exception;

public class BlockfrostAPIException extends Exception {

    public BlockfrostAPIException( String message ){
        super(message);
    }

    public BlockfrostAPIException( String message, Exception exp ){
        super(message, exp);
    }

}
