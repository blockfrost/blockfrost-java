package io.blockfrost.sdk.impl.helper;

import io.blockfrost.sdk.api.exception.APIException;

public class ValidationHelper {

    public static final String COUNT_VALIDATION_MESSAGE = "Count should be <= 100";

    public static void validateCount(int count) throws APIException {
        if (count > 100) {
            throw new APIException(COUNT_VALIDATION_MESSAGE);
        }
    }

}
