package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.aytchell.validator.ExceptionMessageCheck.assertThrowsAndMessageContains;

public class LongValidatorTest {
    @Test
    void isNullGivenValidLongPasses() throws ValidationException {
        final Long validLong = 42L;
        final Integer validInteger = 23;

        Validator.throwIf(validLong, "validLong").isNull();
        Validator.throwIf(validInteger, "validInteger").isNull();
    }

    @Test
    void isNullGivenNullThrows() {
        final Long nullLong = null;

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(nullLong, "nullLong").isNull(),
                List.of("nullLong", "is missing"));
    }

    @Test
    void isLowerThanGivenValidValuesPass() throws ValidationException {
        final Long validLong = 42L;
        final Long validInteger = 42L;
        final Long nullLong = null;

        Validator.throwIf(validLong, "validLong").isNull().isLowerThan(2);
        Validator.throwIf(validLong, "validLong").isNull().isLowerThan(4L);
        Validator.throwIf(validInteger, "validInteger").isNull().isLowerThan(2);
        Validator.throwIf(validInteger, "validInteger").isNull().isLowerThan(4L);

        Validator.throwIf(nullLong, "nullLong").isNotNullAnd().isLowerThan(2);
        Validator.throwIf(nullLong, "nullLong").isNotNullAnd().isLowerThan(4L);
        Validator.throwIf(nullLong, "nullLong").isNotNullAnd().isLowerThan(1024);
        Validator.throwIf(nullLong, "nullLong").isNotNullAnd().isLowerThan(2048L);
    }

    @Test
    void isLowerThanGivenHighValuesThrows() throws ValidationException {
        final Long validLong = 42L;
        final Integer validInteger = 112;

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validLong, "validLong").isNull().isLowerThan(1024),
                List.of("validLong", "is too small", "42", "1024"));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validLong, "validLong").isNull().isLowerThan(2048L),
                List.of("validLong", "is too small", "42", "2048"));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validInteger, "validInteger").isNull().isLowerThan(1024),
                List.of("validInteger", "is too small", "112", "1024"));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validInteger, "validInteger").isNull().isLowerThan(2048L),
                List.of("validInteger", "is too small", "112", "2048"));
    }

    @Test
    void isGreaterThanGivenValidValuesPass() throws ValidationException {
        final Long validLong = 42L;
        final Integer validInteger = 110;
        final Long nullLong = null;

        Validator.throwIf(validLong, "validLong").isNull().isGreaterThan(1024);
        Validator.throwIf(validLong, "validLong").isNull().isGreaterThan(4096L);
        Validator.throwIf(validInteger, "validInteger").isNull().isGreaterThan(1024);
        Validator.throwIf(validInteger, "validInteger").isNull().isGreaterThan(4096L);

        Validator.throwIf(nullLong, "nullLong").isNotNullAnd().isGreaterThan(2);
        Validator.throwIf(nullLong, "nullLong").isNotNullAnd().isGreaterThan(4L);
        Validator.throwIf(nullLong, "nullLong").isNotNullAnd().isGreaterThan(1024);
        Validator.throwIf(nullLong, "nullLong").isNotNullAnd().isGreaterThan(2048L);
    }

    @Test
    void isGreaterThanGivenLowValuesThrows() throws ValidationException {
        final Long validLong = 42L;
        final Integer validInteger = 1729;

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validLong, "validLong").isNull().isGreaterThan(3),
                List.of("validLong", "is too big", "42", "3"));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validLong, "validLong").isNull().isGreaterThan(5L),
                List.of("validLong", "is too big", "42", "5"));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validInteger, "validInteger").isNull().isGreaterThan(3),
                List.of("validInteger", "is too big", "1729", "3"));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validInteger, "validInteger").isNull().isGreaterThan(5L),
                List.of("validInteger", "is too big", "1729", "5"));
    }

    @Test
    void isNotValidPortNumberGivenValidValuesPasses() throws ValidationException {
        final Long validLongPort = 19377L;
        final Integer validIntegerPort = 4711;

        Validator.throwIf(validLongPort, "validLongPort").isNull().isNoValidPortNumber();
        Validator.throwIf(validIntegerPort, "validIntegerPort").isNull().isNoValidPortNumber();
    }

    @Test
    void isNotValidPortNumberGivenLowValuesThrows() throws ValidationException {
        final Long invalidLongPort = 0L;
        final Integer invalidIntegerPort = -5;

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(invalidLongPort, "invalidLongPort").isNull().isNoValidPortNumber(),
                List.of("invalidLongPort", "0", "no valid port number"));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(invalidIntegerPort, "invalidIntegerPort").isNull().isNoValidPortNumber(),
                List.of("invalidIntegerPort", "-5", "no valid port number"));
    }

    @Test
    void isNotValidPortNumberGivenHighValuesThrows() throws ValidationException {
        final Long invalidLongPort = 128247L;
        final Integer invalidIntegerPort = 65536;

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(invalidLongPort, "invalidLongPort").isNull().isNoValidPortNumber(),
                List.of("invalidLongPort", "128247", "no valid port number"));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(invalidIntegerPort, "invalidIntegerPort").isNull().isNoValidPortNumber(),
                List.of("invalidIntegerPort", "65536", "no valid port number"));
    }
}