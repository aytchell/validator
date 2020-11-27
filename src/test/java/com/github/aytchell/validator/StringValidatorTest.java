package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Pattern;

import static com.github.aytchell.validator.ExceptionMessageCheck.assertThrowsAndMessageReadsLike;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringValidatorTest {
    @Test
    void isNullGivenNullThrows() {
        final String nullString = null;

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(nullString, "nullString", "extras").notNull(),
                List.of("nullString", "extras", "is not null"));
    }

    @Test
    void testIfConditionNotMetIsSkipped() throws ValidationException {
        final String emptyString = "";
        final String blankString = "\t\t\t\t\t\t\t";
        final Pattern privateString = Pattern.compile("private.*String.*=.*");

        Validator.expect(emptyString).ifTrue(false).ifNotNull().notEmpty();
        Validator.expect(blankString).ifTrue(false).ifNotNull().notBlank();
        Validator.expect(blankString).ifTrue(false).ifNotNull().lengthAtMost(2);
        Validator.expect(blankString).ifTrue(false).ifNotNull().bytesAtMost(0, StringValidator.Encoding.UTF_8);
        Validator.expect(blankString).ifTrue(false).ifNotNull().codePointsAtMost(0);
        Validator.expect(blankString).ifTrue(false).ifNotNull().validUrl();
        Validator.expect(blankString).ifTrue(false).ifNotNull().matches(privateString);
        Validator.expect(blankString).ifTrue(false).ifNotNull().matches(privateString, "private");
        Validator.expect(blankString).ifTrue(false).ifNotNull().passes(String::isEmpty, "is empty");
    }

    @Test
    void isEmptyGivenFilledStringPasses() throws ValidationException {
        final String blankString = "\t \n";
        final String filledString = "filled";
        final String nullString = null;

        Validator.expect(blankString, "blankString").notNull().notEmpty();
        Validator.expect(filledString, "filledString").notNull().notEmpty();
    }

    @Test
    void isEmptyGivenEmptyStringThrows() {
        final String emptyString = "";

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(emptyString, "emptyString").notNull().notEmpty(),
                List.of("emptyString", "is not empty")
        );
    }

    @Test
    void isBlankGivenFilledStringPasses() throws ValidationException {
        final String filledString = "filled";

        Validator.expect(filledString, "filledString").notNull().notBlank();
    }

    @Test
    void isBlankGivenBlankStringThrows() {
        final String blankString = "\t \n";

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(blankString, "blankString").notNull().notBlank(),
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
    }

    @Test
    void isLongerThanGivenLongStringFails() {
        final String longString = "This is an extremely long string. It is too long";
        final String stringSize = "48";

        assertEquals(stringSize, String.valueOf(longString.length()));

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(longString, "longString").notNull().lengthAtMost(10),
                List.of("length of longString", stringSize, "is at most", "10")
        );
    }

    private static final String LATIN_SMALL_LETTER_A = "\u0061";
    private static final String DEVANAGARI_LETTER_NA = "\u0928";
    private static final String DEVANAGARI_VOWEL_SIGN_I = "\u093F";
    private static final String DEVANAGARI_SYLLABLE_NI = DEVANAGARI_LETTER_NA + DEVANAGARI_VOWEL_SIGN_I;
    private static final String CJK_UNIFIED_IDEOGRAPH_4E9C = "\u4E9C";
    private static final String LINEAR_B_IDEOGRAM_EQUID = "\uD800\uDC83";

    // See https://unicode.org/faq/char_combmark.html#7 for an explanation of this sample string
    private static final String FAQ_SAMPLE_STRING =
            LATIN_SMALL_LETTER_A + DEVANAGARI_SYLLABLE_NI + CJK_UNIFIED_IDEOGRAPH_4E9C + LINEAR_B_IDEOGRAM_EQUID;

    @Test
    void bytesAtMostWorkAsExpected() throws ValidationException {
        // Expected number of bytes is taken from here: https://unicode.org/faq/char_combmark.html#7
        Validator.expect(FAQ_SAMPLE_STRING).notNull().bytesAtMost(14, StringValidator.Encoding.UTF_8);
        Validator.expect(FAQ_SAMPLE_STRING).notNull().bytesAtMost(12, StringValidator.Encoding.UTF_16);
        Validator.expect(FAQ_SAMPLE_STRING).notNull().bytesAtMost(20, StringValidator.Encoding.UTF_32);

        assertThrows(ValidationException.class, () -> Validator.expect(FAQ_SAMPLE_STRING).notNull()
                .bytesAtMost(13, StringValidator.Encoding.UTF_8));
        assertThrows(ValidationException.class, () -> Validator.expect(FAQ_SAMPLE_STRING).notNull()
                .bytesAtMost(11, StringValidator.Encoding.UTF_16));
        assertThrows(ValidationException.class, () -> Validator.expect(FAQ_SAMPLE_STRING).notNull()
                .bytesAtMost(19, StringValidator.Encoding.UTF_32));
    }

    @Test
    void codepointsAtMostWorkAsExpected() throws ValidationException {
        // Expected number of code points is taken from here: https://unicode.org/faq/char_combmark.html#7
        Validator.expect(FAQ_SAMPLE_STRING).notNull().codePointsAtMost(5);

        assertThrows(ValidationException.class,
                () -> Validator.expect(FAQ_SAMPLE_STRING).notNull().codePointsAtMost(4));
    }

    @Test
    void validUrlWithVAlidUrlGivenPasses() throws ValidationException {
        final String url = "http://www.github.com/aytchell/validator";

        Validator.expect(url).notNull().validUrl();
    }

    @Test
    void validUrlWithMalformedUrlGivenThrows() {
        final String url = "://no valid url/";

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(url, "no-url", "schema is missing")
                        .notNull().validUrl(),
                List.of("no-url", "schema is missing", "is valid URL")
        );
    }

    @Test
    void matchesGivenProperExamplesPass() throws ValidationException {
        final String declaration = "private static final String foo = \"bar\"";
        final Pattern privateString = Pattern.compile("private.*String.*=.*");

        Validator.expect(declaration).notNull().matches(privateString);
    }

    @Test
    void matchesGivenWrongInputThrows() {
        final String declaration = "private static final String foo = \"bar\"";
        final Pattern publicPattern = Pattern.compile(".*public.*");

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(declaration).notNull().matches(publicPattern, "publicPattern"),
                List.of("private static", "matches regex", "'publicPattern'", "'.*public.*'"));
    }

    @Test
    void passesGivenCorrectStringPass() throws ValidationException {
        final String blankString = "\t\t\n";

        Validator.expect(blankString).notNull().passes(String::isBlank, "is blank");
    }

    @Test
    void passesGivenWrongStringThrows() {
        final String filledString = "not blank";

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(filledString).notNull().passes(String::isBlank, "is blank"),
                List.of("'not blank'", "is blank", "but is not"));
    }
}
