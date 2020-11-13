package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.aytchell.validator.ExceptionMessageCheck.assertThrowsAndMessageReadsLike;

class ValidatorTest {

    @Test
    void throwIfNullGivenValidStringPasses() throws ValidationException {
        final String validString = "valid";
        Validator.throwIfNull(validString, "validString");
    }

    @Test
    void throwIfNullGivenValidLongPasses() throws ValidationException {
        final Long validLong = 42L;
        Validator.throwIfNull(validLong, "validLong");
    }

    @Test
    void throwIfNullGivenNullThrows() {
        final String nullString = null;

        assertThrowsAndMessageReadsLike(
                () -> Validator.throwIfNull(nullString, "nullString"),
                List.of("nullString", "is not null"));

        assertThrowsAndMessageReadsLike(
                () -> Validator.throwIfNull(nullString, "nullString", "important info"),
                List.of("nullString", "important info", "is not null"));
    }
}