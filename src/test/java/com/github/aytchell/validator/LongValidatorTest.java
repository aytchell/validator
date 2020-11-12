package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.aytchell.validator.ExceptionMessageCheck.assertThrowsAndMessageContains;

public class LongValidatorTest {
    @Test
    void isNullGivenNullThrows() {
        final Long nullLong = null;

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(nullLong, "nullLong").isNull(),
                List.of("nullLong", "is missing"));
    }

    @Test
    void isLowerThanGivenValidValuesPass() throws ValidationException {
        final Short validShort = 12;
        final Integer validInteger = 42;
        final Long validLong = 42L;
        final Long nullLong = null;

        Validator.throwIf(validLong, "validLong").isNull().isSmallerThan(2);
        Validator.throwIf(validLong, "validLong").isNull().isSmallerThan(4L);
        Validator.throwIf(validShort, "validShort").isNull().isSmallerThan(2);
        Validator.throwIf(validShort, "validShort").isNull().isSmallerThan(4L);
        Validator.throwIf(validInteger, "validInteger").isNull().isSmallerThan(2);
        Validator.throwIf(validInteger, "validInteger").isNull().isSmallerThan(4L);

        Validator.throwIf(nullLong, "nullLong").isNotNullAnd().isSmallerThan(2);
        Validator.throwIf(nullLong, "nullLong").isNotNullAnd().isSmallerThan(4L);
        Validator.throwIf(nullLong, "nullLong").isNotNullAnd().isSmallerThan(1024);
        Validator.throwIf(nullLong, "nullLong").isNotNullAnd().isSmallerThan(2048L);
    }

    @Test
    void isNullIsLowerThanGivenHighValuesThrows() throws ValidationException {
        final Long validLong = 42L;
        final Integer validInteger = 112;

        // Test <Long> against <int>  with mode 'isNull()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validLong, "validLong").isNull().isSmallerThan(1024),
                List.of("validLong", "is too small", "42", "1024"));

        // Test <Long> against <long>  with mode 'isNull()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validLong, "validLong").isNull().isSmallerThan(2048L),
                List.of("validLong", "is too small", "42", "2048"));

        // Test <Integer> against <int>  with mode 'isNull()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validInteger, "validInteger").isNull().isSmallerThan(1024),
                List.of("validInteger", "is too small", "112", "1024"));

        // Test <Integer> against <long>  with mode 'isNull()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validInteger, "validInteger").isNull().isSmallerThan(2048L),
                List.of("validInteger", "is too small", "112", "2048"));
    }

    @Test
    void isNotNullAndIsLowerThanGivenHighValuesThrows() throws ValidationException {
        final Long validLong = 42L;
        final Integer validInteger = 112;

        // Test <Long> against <int>  with mode 'isNotNullAnd()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validLong, "validLong").isNotNullAnd().isSmallerThan(1024),
                List.of("validLong", "is too small", "42", "1024"));

        // Test <Long> against <long>  with mode 'isNotNullAnd()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validLong, "validLong").isNotNullAnd().isSmallerThan(2048L),
                List.of("validLong", "is too small", "42", "2048"));

        // Test <Integer> against <int>  with mode 'isNotNullAnd()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validInteger, "validInteger").isNotNullAnd().isSmallerThan(1024),
                List.of("validInteger", "is too small", "112", "1024"));

        // Test <Integer> against <long>  with mode 'isNotNullAnd()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validInteger, "validInteger").isNotNullAnd().isSmallerThan(2048L),
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
    void isNullIsGreaterThanGivenLowValuesThrows() throws ValidationException {
        final Long validLong = 42L;
        final Integer validInteger = 1729;

        // Test <Long> against <int>  with mode 'isNull()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validLong, "validLong").isNull().isGreaterThan(3),
                List.of("validLong", "is too big", "42", "3"));

        // Test <Long> against <long>  with mode 'isNull()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validLong, "validLong").isNull().isGreaterThan(5L),
                List.of("validLong", "is too big", "42", "5"));

        // Test <Integer> against <int>  with mode 'isNull()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validInteger, "validInteger").isNull().isGreaterThan(3),
                List.of("validInteger", "is too big", "1729", "3"));

        // Test <Integer> against <long>  with mode 'isNull()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validInteger, "validInteger").isNull().isGreaterThan(5L),
                List.of("validInteger", "is too big", "1729", "5"));
    }

    @Test
    void isNotNullAndisGreaterThanGivenLowValuesThrows() throws ValidationException {
        final Long validLong = 42L;
        final Integer validInteger = 1729;

        // Test <Long> against <int>  with mode 'isNotNullAnd()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validLong, "validLong").isNotNullAnd().isGreaterThan(3),
                List.of("validLong", "is too big", "42", "3"));

        // Test <Long> against <long>  with mode 'isNotNullAnd()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validLong, "validLong").isNotNullAnd().isGreaterThan(5L),
                List.of("validLong", "is too big", "42", "5"));

        // Test <Integer> against <int>  with mode 'isNotNullAnd()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validInteger, "validInteger").isNotNullAnd().isGreaterThan(3),
                List.of("validInteger", "is too big", "1729", "3"));

        // Test <Integer> against <long>  with mode 'isNotNullAnd()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(validInteger, "validInteger").isNotNullAnd().isGreaterThan(5L),
                List.of("validInteger", "is too big", "1729", "5"));
    }

    @Test
    void isNotValidPortNumberGivenValidValuesPasses() throws ValidationException {
        final Long validLongPort = 19377L;
        final Integer validIntegerPort = 4711;

        Validator.throwIf(validLongPort, "validLongPort").isNull().isNoValidPortNumber();
        Validator.throwIf(validIntegerPort, "validIntegerPort").isNull().isNoValidPortNumber();

        Validator.throwIf(validLongPort, "validLongPort").isNotNullAnd().isNoValidPortNumber();
        Validator.throwIf(validIntegerPort, "validIntegerPort").isNotNullAnd().isNoValidPortNumber();
    }

    @Test
    void isNotValidPortNumberGivenLowValuesThrows() throws ValidationException {
        final Long invalidLongPort = 0L;
        final Integer invalidIntegerPort = -5;

        // Test <Long> with mode 'isNull()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(invalidLongPort, "invalidLongPort").isNull().isNoValidPortNumber(),
                List.of("invalidLongPort", "0", "no valid port number"));

        // Test <Integer> with mode 'isNull()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(invalidIntegerPort, "invalidIntegerPort").isNull().isNoValidPortNumber(),
                List.of("invalidIntegerPort", "-5", "no valid port number"));

        // Test <Long> with mode 'isNotNullAnd()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(invalidLongPort, "invalidLongPort").isNotNullAnd().isNoValidPortNumber(),
                List.of("invalidLongPort", "0", "no valid port number"));

        // Test <Integer> with mode 'isNotNullAnd()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(invalidIntegerPort, "invalidIntegerPort").isNotNullAnd().isNoValidPortNumber(),
                List.of("invalidIntegerPort", "-5", "no valid port number"));
    }

    @Test
    void isNotValidPortNumberGivenHighValuesThrows() throws ValidationException {
        final Long invalidLongPort = 128247L;
        final Integer invalidIntegerPort = 65536;

        // Test <Long> with mode 'isNull()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(invalidLongPort, "invalidLongPort").isNull().isNoValidPortNumber(),
                List.of("invalidLongPort", "128247", "no valid port number"));

        // Test <Integer> with mode 'isNull()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(invalidIntegerPort, "invalidIntegerPort").isNull().isNoValidPortNumber(),
                List.of("invalidIntegerPort", "65536", "no valid port number"));

        // Test <Long> with mode 'isNotNullAnd()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(invalidLongPort, "invalidLongPort").isNotNullAnd().isNoValidPortNumber(),
                List.of("invalidLongPort", "128247", "no valid port number"));

        // Test <Integer> with mode 'isNotNullAnd()'
        assertThrowsAndMessageContains(
                () -> Validator.throwIf(invalidIntegerPort, "invalidIntegerPort").isNotNullAnd().isNoValidPortNumber(),
                List.of("invalidIntegerPort", "65536", "no valid port number"));
    }
}