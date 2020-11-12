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
                () -> Validator.expect(nullLong, "nullLong").notNull(),
                List.of("nullLong", "is missing"));
    }

    @Test
    void isLowerThanGivenValidValuesPass() throws ValidationException {
        final Short validShort = 12;
        final Integer validInteger = 42;
        final Long validLong = 42L;
        final Long nullLong = null;

        Validator.expect(validLong, "validLong").notNull().isSmallerThan(2);
        Validator.expect(validLong, "validLong").notNull().isSmallerThan(4L);
        Validator.expect(validShort, "validShort").notNull().isSmallerThan(2);
        Validator.expect(validShort, "validShort").notNull().isSmallerThan(4L);
        Validator.expect(validInteger, "validInteger").notNull().isSmallerThan(2);
        Validator.expect(validInteger, "validInteger").notNull().isSmallerThan(4L);

        Validator.expect(nullLong, "nullLong").ifNotNull().isSmallerThan(2);
        Validator.expect(nullLong, "nullLong").ifNotNull().isSmallerThan(4L);
        Validator.expect(nullLong, "nullLong").ifNotNull().isSmallerThan(1024);
        Validator.expect(nullLong, "nullLong").ifNotNull().isSmallerThan(2048L);
    }

    @Test
    void isNullIsLowerThanGivenHighValuesThrows() throws ValidationException {
        final Long validLong = 42L;
        final Integer validInteger = 112;

        // Test <Long> against <int>  with mode 'notNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(validLong, "validLong").notNull().isSmallerThan(1024),
                List.of("validLong", "is too small", "42", "1024"));

        // Test <Long> against <long>  with mode 'notNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(validLong, "validLong").notNull().isSmallerThan(2048L),
                List.of("validLong", "is too small", "42", "2048"));

        // Test <Integer> against <int>  with mode 'notNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(validInteger, "validInteger").notNull().isSmallerThan(1024),
                List.of("validInteger", "is too small", "112", "1024"));

        // Test <Integer> against <long>  with mode 'notNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(validInteger, "validInteger").notNull().isSmallerThan(2048L),
                List.of("validInteger", "is too small", "112", "2048"));
    }

    @Test
    void isNotNullAndIsLowerThanGivenHighValuesThrows() throws ValidationException {
        final Long validLong = 42L;
        final Integer validInteger = 112;

        // Test <Long> against <int>  with mode 'ifNotNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(validLong, "validLong").ifNotNull().isSmallerThan(1024),
                List.of("validLong", "is too small", "42", "1024"));

        // Test <Long> against <long>  with mode 'ifNotNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(validLong, "validLong").ifNotNull().isSmallerThan(2048L),
                List.of("validLong", "is too small", "42", "2048"));

        // Test <Integer> against <int>  with mode 'ifNotNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(validInteger, "validInteger").ifNotNull().isSmallerThan(1024),
                List.of("validInteger", "is too small", "112", "1024"));

        // Test <Integer> against <long>  with mode 'ifNotNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(validInteger, "validInteger").ifNotNull().isSmallerThan(2048L),
                List.of("validInteger", "is too small", "112", "2048"));
    }

    @Test
    void isGreaterThanGivenValidValuesPass() throws ValidationException {
        final Long validLong = 42L;
        final Integer validInteger = 110;
        final Long nullLong = null;

        Validator.expect(validLong, "validLong").notNull().isGreaterThan(1024);
        Validator.expect(validLong, "validLong").notNull().isGreaterThan(4096L);
        Validator.expect(validInteger, "validInteger").notNull().isGreaterThan(1024);
        Validator.expect(validInteger, "validInteger").notNull().isGreaterThan(4096L);

        Validator.expect(nullLong, "nullLong").ifNotNull().isGreaterThan(2);
        Validator.expect(nullLong, "nullLong").ifNotNull().isGreaterThan(4L);
        Validator.expect(nullLong, "nullLong").ifNotNull().isGreaterThan(1024);
        Validator.expect(nullLong, "nullLong").ifNotNull().isGreaterThan(2048L);
    }

    @Test
    void isNullIsGreaterThanGivenLowValuesThrows() throws ValidationException {
        final Long validLong = 42L;
        final Integer validInteger = 1729;

        // Test <Long> against <int>  with mode 'notNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(validLong, "validLong").notNull().isGreaterThan(3),
                List.of("validLong", "is too big", "42", "3"));

        // Test <Long> against <long>  with mode 'notNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(validLong, "validLong").notNull().isGreaterThan(5L),
                List.of("validLong", "is too big", "42", "5"));

        // Test <Integer> against <int>  with mode 'notNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(validInteger, "validInteger").notNull().isGreaterThan(3),
                List.of("validInteger", "is too big", "1729", "3"));

        // Test <Integer> against <long>  with mode 'notNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(validInteger, "validInteger").notNull().isGreaterThan(5L),
                List.of("validInteger", "is too big", "1729", "5"));
    }

    @Test
    void isNotNullAndisGreaterThanGivenLowValuesThrows() throws ValidationException {
        final Long validLong = 42L;
        final Integer validInteger = 1729;

        // Test <Long> against <int>  with mode 'ifNotNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(validLong, "validLong").ifNotNull().isGreaterThan(3),
                List.of("validLong", "is too big", "42", "3"));

        // Test <Long> against <long>  with mode 'ifNotNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(validLong, "validLong").ifNotNull().isGreaterThan(5L),
                List.of("validLong", "is too big", "42", "5"));

        // Test <Integer> against <int>  with mode 'ifNotNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(validInteger, "validInteger").ifNotNull().isGreaterThan(3),
                List.of("validInteger", "is too big", "1729", "3"));

        // Test <Integer> against <long>  with mode 'ifNotNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(validInteger, "validInteger").ifNotNull().isGreaterThan(5L),
                List.of("validInteger", "is too big", "1729", "5"));
    }

    @Test
    void isNotValidPortNumberGivenValidValuesPasses() throws ValidationException {
        final Long validLongPort = 19377L;
        final Integer validIntegerPort = 4711;

        Validator.expect(validLongPort, "validLongPort").notNull().isNoValidPortNumber();
        Validator.expect(validIntegerPort, "validIntegerPort").notNull().isNoValidPortNumber();

        Validator.expect(validLongPort, "validLongPort").ifNotNull().isNoValidPortNumber();
        Validator.expect(validIntegerPort, "validIntegerPort").ifNotNull().isNoValidPortNumber();
    }

    @Test
    void isNotValidPortNumberGivenLowValuesThrows() throws ValidationException {
        final Long invalidLongPort = 0L;
        final Integer invalidIntegerPort = -5;

        // Test <Long> with mode 'notNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(invalidLongPort, "invalidLongPort").notNull().isNoValidPortNumber(),
                List.of("invalidLongPort", "0", "no valid port number"));

        // Test <Integer> with mode 'notNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(invalidIntegerPort, "invalidIntegerPort").notNull().isNoValidPortNumber(),
                List.of("invalidIntegerPort", "-5", "no valid port number"));

        // Test <Long> with mode 'ifNotNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(invalidLongPort, "invalidLongPort").ifNotNull().isNoValidPortNumber(),
                List.of("invalidLongPort", "0", "no valid port number"));

        // Test <Integer> with mode 'ifNotNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(invalidIntegerPort, "invalidIntegerPort").ifNotNull().isNoValidPortNumber(),
                List.of("invalidIntegerPort", "-5", "no valid port number"));
    }

    @Test
    void isNotValidPortNumberGivenHighValuesThrows() throws ValidationException {
        final Long invalidLongPort = 128247L;
        final Integer invalidIntegerPort = 65536;

        // Test <Long> with mode 'notNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(invalidLongPort, "invalidLongPort").notNull().isNoValidPortNumber(),
                List.of("invalidLongPort", "128247", "no valid port number"));

        // Test <Integer> with mode 'notNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(invalidIntegerPort, "invalidIntegerPort").notNull().isNoValidPortNumber(),
                List.of("invalidIntegerPort", "65536", "no valid port number"));

        // Test <Long> with mode 'ifNotNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(invalidLongPort, "invalidLongPort").ifNotNull().isNoValidPortNumber(),
                List.of("invalidLongPort", "128247", "no valid port number"));

        // Test <Integer> with mode 'ifNotNull()'
        assertThrowsAndMessageContains(
                () -> Validator.expect(invalidIntegerPort, "invalidIntegerPort").ifNotNull().isNoValidPortNumber(),
                List.of("invalidIntegerPort", "65536", "no valid port number"));
    }
}