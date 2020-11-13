package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.aytchell.validator.ExceptionMessageCheck.assertThrowsAndMessageReadsLike;

class ValidatorTest {

    @Test
    void expectNotNullGivenValidStringPasses() throws ValidationException {
        final String validString = "valid";
        Validator.expectNotNull(validString, "validString");
    }

    @Test
    void expectNotNullGivenValidLongPasses() throws ValidationException {
        final Long validLong = 42L;
        Validator.expectNotNull(validLong, "validLong");
    }

    @Test
    void expectNotNullGivenNullThrows() {
        final String nullString = null;

        assertThrowsAndMessageReadsLike(
                () -> Validator.expectNotNull(nullString, "nullString"),
                List.of("nullString", "is not null"));

        assertThrowsAndMessageReadsLike(
                () -> Validator.expectNotNull(nullString, "nullString", "important info"),
                List.of("nullString", "important info", "is not null"));
    }
}