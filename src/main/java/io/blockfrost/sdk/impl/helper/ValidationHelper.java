package io.blockfrost.sdk.impl.helper;

import io.blockfrost.sdk.api.exception.APIException;

import java.util.regex.Pattern;

public class ValidationHelper {

    public static final String COUNT_VALIDATION_MESSAGE = "Count should be <= 100";
    public static final String BLANK_HASH_VALIDATION_MESSAGE = "Hash cannot be null or empty";
    public static final String HEXADECIMAL_HASH_VALIDATION_MESSAGE = "Hash must be an hexadecimal value";

    private static final String HEXADECIMAL_PATTERN = "\\p{XDigit}+";

    public static void validateCount(int count) throws APIException {
        if (count > 100) {
            throw new APIException(COUNT_VALIDATION_MESSAGE);
        }
    }

    public static void validateHexadecimalHash(String hash) throws APIException {
        if (hash == null || hash.trim().isEmpty()) {
            throw new APIException(BLANK_HASH_VALIDATION_MESSAGE);
        }

        if(!isHexadecimal(hash)) {
            throw new APIException(HEXADECIMAL_HASH_VALIDATION_MESSAGE);
        }
    }

    private static boolean isHexadecimal(String input) {
        return Pattern.compile(HEXADECIMAL_PATTERN).matcher(input).matches();
    }

}
