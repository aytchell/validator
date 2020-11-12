package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.aytchell.validator.ExceptionMessageCheck.assertThrowsAndMessageContains;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringValidatorTest {
    @Test
    void isNullGivenNullThrows() {
        final String nullString = null;

        assertThrowsAndMessageContains(
                () -> Validator.expect(nullString, "nullString").isNull(),
                List.of("nullString", "is missing"));
    }

    @Test
    void isEmptyGivenFilledStringPasses() throws ValidationException {
        final String blankString = "\t \n";
        final String filledString = "filled";
        final String nullString = null;

        Validator.expect(blankString, "blankString").isNull().isEmpty();
        Validator.expect(filledString, "filledString").isNull().isEmpty();

        Validator.expect(blankString, "blankString").isNotNullAnd().isEmpty();
        Validator.expect(filledString, "filledString").isNotNullAnd().isEmpty();
        Validator.expect(nullString, "nullString").isNotNullAnd().isEmpty();
    }

    @Test
    void isEmptyGivenEmptyStringThrows() {
        final String emptyString = "";

        assertThrowsAndMessageContains(
                () -> Validator.expect(emptyString, "emptyString").isNull().isEmpty(),
                List.of("emptyString", "must not be empty")
        );

        assertThrowsAndMessageContains(
                () -> Validator.expect(emptyString, "emptyString").isNotNullAnd().isEmpty(),
                List.of("emptyString", "must not be empty")
        );
    }

    @Test
    void isBlankGivenFilledStringPasses() throws ValidationException {
        final String filledString = "filled";
        final String nullString = null;

        Validator.expect(filledString, "filledString").isNull().isBlank();

        Validator.expect(filledString, "filledString").isNotNullAnd().isBlank();
        Validator.expect(nullString, "nullString").isNotNullAnd().isBlank();
    }

    @Test
    void isBlankGivenBlankStringThrows() {
        final String blankString = "\t \n";

        assertThrowsAndMessageContains(
                () -> Validator.expect(blankString, "blankString").isNull().isBlank(),
                List.of("blankString", "must not be blank")
        );

        assertThrowsAndMessageContains(
                () -> Validator.expect(blankString, "blankString").isNotNullAnd().isBlank(),
                List.of("blankString", "must not be blank")
        );
    }

    @Test
    void isLongerThanGivenShortStringPasses() throws ValidationException {
        final String nullString = null;
        final String emptyString = "";
        final String shortString = "short";

        Validator.expect(emptyString, "emptyString").isNull().isLongerThan(10);
        Validator.expect(shortString, "shortString").isNull().isLongerThan(10);

        Validator.expect(emptyString, "emptyString").isNotNullAnd().isLongerThan(10);
        Validator.expect(shortString, "shortString").isNotNullAnd().isLongerThan(10);
        Validator.expect(nullString, "nullString").isNotNullAnd().isLongerThan(10);
    }

    @Test
    void isLongerThanGivenLongStringFails() {
        final String longString = "This is an extremely long string. It is too long";
        final String stringSize = "48";

        assertEquals(stringSize, String.valueOf(longString.length()));

        assertThrowsAndMessageContains(
                () -> Validator.expect(longString, "longString").isNull().isLongerThan(10),
                List.of("longString", "must not be longer than", "10", stringSize)
        );

        assertThrowsAndMessageContains(
                () -> Validator.expect(longString, "longString").isNotNullAnd().isLongerThan(10),
                List.of("longString", "must not be longer than", "10", stringSize)
        );
    }
}
