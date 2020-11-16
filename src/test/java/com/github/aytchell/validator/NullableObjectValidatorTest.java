package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.aytchell.validator.ExceptionMessageCheck.assertThrowsAndMessageReadsLike;

public class NullableObjectValidatorTest {
    private final Long longValue = 45L;
    private final Boolean nullBool = null;
    private final Boolean trueBool = true;
    private final Boolean falseBool = false;

    @Test
    void ifTrueWhenTrueTriggers() {
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(longValue).ifTrue(true).notNull().greaterThan(100),
                List.of());
    }

    @Test
    void ifTrueWhenFalseSkipsTest() throws ValidationException {
        Validator.expect(longValue).ifTrue(false).notNull().greaterThan(100);
    }

    @Test
    void ifFalseWhenTrueSkipsTest() throws ValidationException {
        Validator.expect(longValue).ifFalse(true).notNull().greaterThan(100);
    }

    @Test
    void ifFalseWhenFalseTriggers() {
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(longValue).ifFalse(false).notNull().greaterThan(100),
                List.of("is greater than 100"));
    }

    @Test
    void ifGivenAndTrueCheckAllCases() throws ValidationException {
        // given and true
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(longValue).ifGivenAndTrue(trueBool).notNull().greaterThan(100),
                List.of("is greater than 100"));

        // given and false
        Validator.expect(longValue).ifGivenAndTrue(falseBool).notNull().greaterThan(100);

        // not given; neither true nor false
        Validator.expect(longValue).ifGivenAndTrue(nullBool).notNull().greaterThan(100);
    }

    @Test
    void ifNotGivenOrFalseCheckAllCases() throws ValidationException {
        // given and true
        Validator.expect(longValue).ifNotGivenOrFalse(trueBool).notNull().greaterThan(100);

        // given and false
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(longValue).ifNotGivenOrFalse(falseBool).notNull().greaterThan(100),
                List.of("is greater than 100"));

        // not given; neither true nor false
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(longValue).ifNotGivenOrFalse(nullBool).notNull().greaterThan(100),
                List.of("is greater than 100"));
    }

    @Test
    void notNullWhenObjectGivenPerformsTest() {
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(longValue).notNull().greaterThan(100),
                List.of("is greater than 100"));
    }

    @Test
    void notNullWhenNullGivenThrows() {
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(nullBool).notNull().isTrue(),
                List.of("is not null"));
    }

    @Test
    void ifNotNullWhenObjectGivenPerformsTest() {
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(longValue).ifNotNull().greaterThan(100),
                List.of("is greater than 100"));
    }

    @Test
    void ifNotNullWhenNullGivenSkipsTest() throws ValidationException {
        Validator.expect(nullBool).ifNotNull().isTrue();
    }

    @Test
    void ifTrueWithFalseSkipsAllFurtherTests() throws ValidationException {
        final String nullString = null;
        final String blankString = " ";

        Validator.expect(nullString).ifTrue(false).ifTrue(true).notNull();
        Validator.expect(nullString).ifTrue(false).ifFalse(false).notNull();
        Validator.expect(nullString).ifTrue(false).ifGivenAndTrue(true).notNull();
        Validator.expect(nullString).ifTrue(false).ifNotGivenOrFalse(false).notNull();
        Validator.expect(nullString).ifTrue(false).notNull();
        Validator.expect(blankString).ifTrue(false).ifNotNull().notBlank();
    }
}