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
                () -> Validator.expect(nullString, "nullString").notNull(),
                List.of("nullString", "is missing"));
    }

    @Test
    void isEmptyGivenFilledStringPasses() throws ValidationException {
        final String blankString = "\t \n";
        final String filledString = "filled";
        final String nullString = null;

        Validator.expect(blankString, "blankString").notNull().isEmpty();
        Validator.expect(filledString, "filledString").notNull().isEmpty();

        Validator.expect(blankString, "blankString").ifNotNull().isEmpty();
        Validator.expect(filledString, "filledString").ifNotNull().isEmpty();
        Validator.expect(nullString, "nullString").ifNotNull().isEmpty();
    }

    @Test
    void isEmptyGivenEmptyStringThrows() {
        final String emptyString = "";

        assertThrowsAndMessageContains(
                () -> Validator.expect(emptyString, "emptyString").notNull().isEmpty(),
                List.of("emptyString", "must not be empty")
        );

        assertThrowsAndMessageContains(
                () -> Validator.expect(emptyString, "emptyString").ifNotNull().isEmpty(),
                List.of("emptyString", "must not be empty")
        );
    }

    @Test
    void isBlankGivenFilledStringPasses() throws ValidationException {
        final String filledString = "filled";
        final String nullString = null;

        Validator.expect(filledString, "filledString").notNull().isBlank();

        Validator.expect(filledString, "filledString").ifNotNull().isBlank();
        Validator.expect(nullString, "nullString").ifNotNull().isBlank();
    }

    @Test
    void isBlankGivenBlankStringThrows() {
        final String blankString = "\t \n";

        assertThrowsAndMessageContains(
                () -> Validator.expect(blankString, "blankString").notNull().isBlank(),
                List.of("blankString", "must not be blank")
        );

        assertThrowsAndMessageContains(
                () -> Validator.expect(blankString, "blankString").ifNotNull().isBlank(),
                List.of("blankString", "must not be blank")
        );
    }

    @Test
    void isLongerThanGivenShortStringPasses() throws ValidationException {
        final String nullString = null;
        final String emptyString = "";
        final String shortString = "short";

        Validator.expect(emptyString, "emptyString").notNull().isLongerThan(10);
        Validator.expect(shortString, "shortString").notNull().isLongerThan(10);

        Validator.expect(emptyString, "emptyString").ifNotNull().isLongerThan(10);
        Validator.expect(shortString, "shortString").ifNotNull().isLongerThan(10);
        Validator.expect(nullString, "nullString").ifNotNull().isLongerThan(10);
    }

    @Test
    void isLongerThanGivenLongStringFails() {
        final String longString = "This is an extremely long string. It is too long";
        final String stringSize = "48";

        assertEquals(stringSize, String.valueOf(longString.length()));

        assertThrowsAndMessageContains(
                () -> Validator.expect(longString, "longString").notNull().isLongerThan(10),
                List.of("longString", "must not be longer than", "10", stringSize)
        );

        assertThrowsAndMessageContains(
                () -> Validator.expect(longString, "longString").ifNotNull().isLongerThan(10),
                List.of("longString", "must not be longer than", "10", stringSize)
        );
    }
}
