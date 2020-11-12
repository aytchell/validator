package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.aytchell.validator.ExceptionMessageCheck.assertThrowsAndMessageReadsLike;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringValidatorTest {
    @Test
    void isNullGivenNullThrows() {
        final String nullString = null;

        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(nullString, "nullString").notNull(),
                List.of("nullString", "is not null"));
    }

    @Test
    void isEmptyGivenFilledStringPasses() throws ValidationException {
        final String blankString = "\t \n";
        final String filledString = "filled";
        final String nullString = null;

        Validator.expect(blankString, "blankString").notNull().notEmpty();
        Validator.expect(filledString, "filledString").notNull().notEmpty();

        Validator.expect(blankString, "blankString").ifNotNull().notEmpty();
        Validator.expect(filledString, "filledString").ifNotNull().notEmpty();
        Validator.expect(nullString, "nullString").ifNotNull().notEmpty();
    }

    @Test
    void isEmptyGivenEmptyStringThrows() {
        final String emptyString = "";

        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(emptyString, "emptyString").notNull().notEmpty(),
                List.of("emptyString", "is not empty")
        );

        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(emptyString, "emptyString").ifNotNull().notEmpty(),
                List.of("emptyString", "is not empty")
        );
    }

    @Test
    void isBlankGivenFilledStringPasses() throws ValidationException {
        final String filledString = "filled";
        final String nullString = null;

        Validator.expect(filledString, "filledString").notNull().notBlank();

        Validator.expect(filledString, "filledString").ifNotNull().notBlank();
        Validator.expect(nullString, "nullString").ifNotNull().notBlank();
    }

    @Test
    void isBlankGivenBlankStringThrows() {
        final String blankString = "\t \n";

        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(blankString, "blankString").notNull().notBlank(),
                List.of("blankString", "is not blank")
        );

        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(blankString, "blankString").ifNotNull().notBlank(),
                List.of("blankString", "is not blank")
        );
    }

    @Test
    void isLongerThanGivenShortStringPasses() throws ValidationException {
        final String nullString = null;
        final String emptyString = "";
        final String shortString = "short";

        Validator.expect(emptyString, "emptyString").notNull().lengthAtMost(10);
        Validator.expect(shortString, "shortString").notNull().lengthAtMost(10);

        Validator.expect(emptyString, "emptyString").ifNotNull().lengthAtMost(10);
        Validator.expect(shortString, "shortString").ifNotNull().lengthAtMost(10);
        Validator.expect(nullString, "nullString").ifNotNull().lengthAtMost(10);
    }

    @Test
    void isLongerThanGivenLongStringFails() {
        final String longString = "This is an extremely long string. It is too long";
        final String stringSize = "48";

        assertEquals(stringSize, String.valueOf(longString.length()));

        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(longString, "longString").notNull().lengthAtMost(10),
                List.of("length of longString", stringSize, "is at most", "10")
        );

        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(longString, "longString").ifNotNull().lengthAtMost(10),
                List.of("length of longString", stringSize, "is at most", "10")
        );
    }
}
