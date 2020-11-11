package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        final ValidationException e = assertThrows(ValidationException.class,
                () -> Validator.throwIfNull(nullString, "nullString"));

        final String message = e.getMessage();
        assertTrue(message.contains("nullString"), "Failed message: " + message);
        assertTrue(message.contains("is missing"), "Failed message: " + message);
    }
}