package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.aytchell.validator.ExceptionMessageCheck.assertThrowsAndMessageReadsLike;

public class BooleanValidatorTest {
    @Test
    void isNullGivenNullThrows() {
        final Boolean nullBoolean = null;

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(nullBoolean).notNull(),
                List.of("is not null"));

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(nullBoolean, "nullBoolean").notNull(),
                List.of("nullBoolean", "is not null"));

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(nullBoolean, "nullBoolean", "extra info").notNull(),
                List.of("nullBoolean", "extra info", "is not null"));
    }

    @Test
    void testIfConditionNotMetIsSkipped() throws ValidationException {
        final Boolean falseBool = false;
        final Boolean trueBool = true;

        Validator.expect(falseBool).ifTrue(false).ifNotNull().isTrue();
        Validator.expect(trueBool).ifTrue(false).ifNotNull().isFalse();
        Validator.expect(trueBool).ifTrue(false).ifNotNull().matches(falseBool);
        Validator.expect(trueBool).ifTrue(false).ifNotNull().matches(falseBool, "falseBool");
    }

    @Test
    void testForCorrectValuePasses() throws ValidationException {
        Validator.expect(true).notNull().isTrue();
        Validator.expect(false).notNull().isFalse();
    }

    @Test
    void testForWrongValueFails() {
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(true, "trueValue").notNull().isFalse(),
                List.of("trueValue", "value: true", "is false"));

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(false, "falseValue").notNull().isTrue(),
                List.of("falseValue", "value: false", "is true"));
    }

    @Test
    void matchesWithEqualValuesPass() throws ValidationException {
        final Boolean trueValue = true;
        final Boolean falseValue = false;
        final Boolean nullBool = null;

        Validator.expect(trueValue, "trueValue").notNull().matches(true);
        Validator.expect(falseValue, "falseValue").notNull().matches(false);
    }

    @Test
    void matchesWithDifferentValuesThrows() {
        final Boolean trueValue = true;
        final Boolean falseValue = false;

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(trueValue, "trueValue").notNull()
                        .matches(false, "falseValue"),
                List.of("'trueValue'", "true", "equals", "'falseValue'", "false"));

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(falseValue, "falseValue").notNull()
                        .matches(true, "trueValue"),
                List.of("'falseValue'", "false", "equals", "'trueValue'", "true"));
    }
}
