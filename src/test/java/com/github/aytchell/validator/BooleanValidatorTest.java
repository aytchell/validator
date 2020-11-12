package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.aytchell.validator.ExceptionMessageCheck.assertThrowsAndMessageReadsLike;

public class BooleanValidatorTest {
    @Test
    void isNullGivenNullThrows() {
        final Boolean nullBoolean = null;

        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(nullBoolean, "nullBoolean").notNull(),
                List.of("nullBoolean", "is not null"));
    }

    @Test
    void testForCorrectValuePasses() throws ValidationException {
        Validator.expect(true).notNull().isTrue();
        Validator.expect(false).notNull().isFalse();
    }

    @Test
    void testIfNotNullIsSkipped() throws ValidationException {
        final Boolean nullBool = null;

        Validator.expect(nullBool).ifNotNull().isTrue();
        Validator.expect(nullBool).ifNotNull().isFalse();
    }

    @Test
    void testForWrongValueFails() {
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(true, "trueValue").notNull().isFalse(),
                List.of("trueValue", "value: true", "is false"));

        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(false, "falseValue").notNull().isTrue(),
                List.of("falseValue", "value: false", "is true"));
    }
}
