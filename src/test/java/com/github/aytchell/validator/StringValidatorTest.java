package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.aytchell.validator.ExceptionMessageCheck.assertThrowsAndMessageContains;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringValidatorTest {
    @Test
    void isEmptyGivenFilledStringPasses() throws ValidationException {
        final String blankString = "\t \n";
        final String filledString = "filled";
        final String nullString = null;

        Validator.throwIf(blankString, "blankString").isNull().isEmpty();
        Validator.throwIf(filledString, "filledString").isNull().isEmpty();

        Validator.throwIf(blankString, "blankString").isNotNullAnd().isEmpty();
        Validator.throwIf(filledString, "filledString").isNotNullAnd().isEmpty();
        Validator.throwIf(nullString, "nullString").isNotNullAnd().isEmpty();
    }

    @Test
    void isEmptyGivenEmptyStringThrows() {
        final String emptyString = "";

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(emptyString, "emptyString").isNull().isEmpty(),
                List.of("emptyString", "must not be empty")
        );

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(emptyString, "emptyString").isNotNullAnd().isEmpty(),
                List.of("emptyString", "must not be empty")
        );
    }

    @Test
    void isBlankGivenFilledStringPasses() throws ValidationException {
        final String filledString = "filled";
        final String nullString = null;

        Validator.throwIf(filledString, "filledString").isNull().isBlank();

        Validator.throwIf(filledString, "filledString").isNotNullAnd().isBlank();
        Validator.throwIf(nullString, "nullString").isNotNullAnd().isBlank();
    }

    @Test
    void isBlankGivenBlankStringThrows() {
        final String blankString = "\t \n";

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(blankString, "blankString").isNull().isBlank(),
                List.of("blankString", "must not be blank")
        );

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(blankString, "blankString").isNotNullAnd().isBlank(),
                List.of("blankString", "must not be blank")
        );
    }

    @Test
    void isLongerThanGivenShortStringPasses() throws ValidationException {
        final String nullString = null;
        final String emptyString = "";
        final String shortString = "short";

        Validator.throwIf(emptyString, "emptyString").isNull().isLongerThan(10);
        Validator.throwIf(shortString, "shortString").isNull().isLongerThan(10);

        Validator.throwIf(emptyString, "emptyString").isNotNullAnd().isLongerThan(10);
        Validator.throwIf(shortString, "shortString").isNotNullAnd().isLongerThan(10);
        Validator.throwIf(nullString, "nullString").isNotNullAnd().isLongerThan(10);
    }

    @Test
    void isLongerThanGivenLongStringFails() {
        final String longString = "This is an extremely long string. It is too long";
        final String stringSize = "48";

        assertEquals(stringSize, String.valueOf(longString.length()));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(longString, "longString").isNull().isLongerThan(10),
                List.of("longString", "must not be longer than", "10", stringSize)
        );

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(longString, "longString").isNotNullAnd().isLongerThan(10),
                List.of("longString", "must not be longer than", "10", stringSize)
        );
    }
}
