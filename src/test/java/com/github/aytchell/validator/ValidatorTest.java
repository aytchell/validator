package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.aytchell.validator.ExceptionMessageCheck.assertThrowsAndMessageContains;

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

        assertThrowsAndMessageContains(
                () -> Validator.throwIfNull(nullString, "nullString"),
                List.of("nullString", "is missing"));
    }
}