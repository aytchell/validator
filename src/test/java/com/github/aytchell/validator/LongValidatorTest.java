package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.aytchell.validator.ExceptionMessageCheck.assertThrowsAndMessageReadsLike;

public class LongValidatorTest {
    @Test
    void isNullGivenNullThrows() {
        final Long nullLong = null;

        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(nullLong, "nullLong").notNull(),
                List.of("nullLong", "is not null"));
    }

    @Test
    void isLowerThanGivenValidValuesPass() throws ValidationException {
        final Short validShort = 12;
        final Integer validInteger = 42;
        final Long validLong = 42L;
        final Long nullLong = null;

        Validator.expect(validLong, "validLong").notNull().greaterEqThan(2);
        Validator.expect(validLong, "validLong").notNull().greaterEqThan(4L);
        Validator.expect(validShort, "validShort").notNull().greaterEqThan(2);
        Validator.expect(validShort, "validShort").notNull().greaterEqThan(4L);
        Validator.expect(validInteger, "validInteger").notNull().greaterEqThan(2);
        Validator.expect(validInteger, "validInteger").notNull().greaterEqThan(4L);

        Validator.expect(nullLong, "nullLong").ifNotNull().greaterEqThan(2);
        Validator.expect(nullLong, "nullLong").ifNotNull().greaterEqThan(4L);
        Validator.expect(nullLong, "nullLong").ifNotNull().greaterEqThan(1024);
        Validator.expect(nullLong, "nullLong").ifNotNull().greaterEqThan(2048L);
    }

    @Test
    void isNullIsLowerThanGivenHighValuesThrows() throws ValidationException {
        final Long validLong = 42L;
        final Integer validInteger = 112;

        // Test <Long> against <int>  with mode 'notNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validLong, "validLong").notNull().greaterEqThan(1024),
                List.of("validLong", "42", "is greater or equal", "1024"));

        // Test <Long> against <long>  with mode 'notNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validLong, "validLong").notNull().greaterEqThan(2048L),
                List.of("validLong", "42", "is greater or equal", "2048"));

        // Test <Integer> against <int>  with mode 'notNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validInteger, "validInteger").notNull().greaterEqThan(1024),
                List.of("validInteger", "112", "is greater or equal", "1024"));

        // Test <Integer> against <long>  with mode 'notNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validInteger, "validInteger").notNull().greaterEqThan(2048L),
                List.of("validInteger", "112", "is greater or equal", "2048"));
    }

    @Test
    void isNotNullAndIsLowerThanGivenHighValuesThrows() throws ValidationException {
        final Long validLong = 42L;
        final Integer validInteger = 112;

        // Test <Long> against <int>  with mode 'ifNotNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validLong, "validLong").ifNotNull().greaterEqThan(1024),
                List.of("validLong", "42", "is greater or equal", "1024"));

        // Test <Long> against <long>  with mode 'ifNotNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validLong, "validLong").ifNotNull().greaterEqThan(2048L),
                List.of("validLong", "42", "is greater or equal", "2048"));

        // Test <Integer> against <int>  with mode 'ifNotNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validInteger, "validInteger").ifNotNull().greaterEqThan(1024),
                List.of("validInteger", "112", "is greater or equal", "1024"));

        // Test <Integer> against <long>  with mode 'ifNotNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validInteger, "validInteger").ifNotNull().greaterEqThan(2048L),
                List.of("validInteger", "112", "is greater or equal", "2048"));
    }

    @Test
    void isGreaterThanGivenValidValuesPass() throws ValidationException {
        final Long validLong = 42L;
        final Integer validInteger = 110;
        final Long nullLong = null;

        Validator.expect(validLong, "validLong").notNull().lessEqThan(1024);
        Validator.expect(validLong, "validLong").notNull().lessEqThan(4096L);
        Validator.expect(validInteger, "validInteger").notNull().lessEqThan(1024);
        Validator.expect(validInteger, "validInteger").notNull().lessEqThan(4096L);

        Validator.expect(nullLong, "nullLong").ifNotNull().lessEqThan(2);
        Validator.expect(nullLong, "nullLong").ifNotNull().lessEqThan(4L);
        Validator.expect(nullLong, "nullLong").ifNotNull().lessEqThan(1024);
        Validator.expect(nullLong, "nullLong").ifNotNull().lessEqThan(2048L);
    }

    @Test
    void isNullIsGreaterThanGivenLowValuesThrows() throws ValidationException {
        final Long validLong = 42L;
        final Integer validInteger = 1729;

        // Test <Long> against <int>  with mode 'notNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validLong, "validLong").notNull().lessEqThan(3),
                List.of("validLong", "42", "is smaller or equal", "3"));

        // Test <Long> against <long>  with mode 'notNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validLong, "validLong").notNull().lessEqThan(5L),
                List.of("validLong", "42", "is smaller or equal", "5"));

        // Test <Integer> against <int>  with mode 'notNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validInteger, "validInteger").notNull().lessEqThan(3),
                List.of("validInteger", "1729", "is smaller or equal", "3"));

        // Test <Integer> against <long>  with mode 'notNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validInteger, "validInteger").notNull().lessEqThan(5L),
                List.of("validInteger", "1729", "is smaller or equal", "5"));
    }

    @Test
    void isNotNullAndisGreaterThanGivenLowValuesThrows() throws ValidationException {
        final Long validLong = 42L;
        final Integer validInteger = 1729;

        // Test <Long> against <int>  with mode 'ifNotNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validLong, "validLong").ifNotNull().lessEqThan(3),
                List.of("validLong", "42", "is smaller or equal", "3"));

        // Test <Long> against <long>  with mode 'ifNotNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validLong, "validLong").ifNotNull().lessEqThan(5L),
                List.of("validLong", "42", "is smaller or equal", "5"));

        // Test <Integer> against <int>  with mode 'ifNotNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validInteger, "validInteger").ifNotNull().lessEqThan(3),
                List.of("validInteger", "1729", "is smaller or equal", "3"));

        // Test <Integer> against <long>  with mode 'ifNotNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validInteger, "validInteger").ifNotNull().lessEqThan(5L),
                List.of("validInteger", "1729", "is smaller or equal", "5"));
    }

    @Test
    void isNotValidPortNumberGivenValidValuesPasses() throws ValidationException {
        final Long validLongPort = 19377L;
        final Integer validIntegerPort = 4711;

        Validator.expect(validLongPort, "validLongPort").notNull().validPortNumber();
        Validator.expect(validIntegerPort, "validIntegerPort").notNull().validPortNumber();

        Validator.expect(validLongPort, "validLongPort").ifNotNull().validPortNumber();
        Validator.expect(validIntegerPort, "validIntegerPort").ifNotNull().validPortNumber();
    }

    @Test
    void isNotValidPortNumberGivenLowValuesThrows() throws ValidationException {
        final Long invalidLongPort = 0L;
        final Integer invalidIntegerPort = -5;

        // Test <Long> with mode 'notNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(invalidLongPort, "invalidLongPort").notNull().validPortNumber(),
                List.of("invalidLongPort", "0", "is a valid port number"));

        // Test <Integer> with mode 'notNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(invalidIntegerPort, "invalidIntegerPort").notNull().validPortNumber(),
                List.of("invalidIntegerPort", "-5", "is a valid port number"));

        // Test <Long> with mode 'ifNotNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(invalidLongPort, "invalidLongPort").ifNotNull().validPortNumber(),
                List.of("invalidLongPort", "0", "is a valid port number"));

        // Test <Integer> with mode 'ifNotNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(invalidIntegerPort, "invalidIntegerPort").ifNotNull().validPortNumber(),
                List.of("invalidIntegerPort", "-5", "is a valid port number"));
    }

    @Test
    void isNotValidPortNumberGivenHighValuesThrows() throws ValidationException {
        final Long invalidLongPort = 128247L;
        final Integer invalidIntegerPort = 65536;

        // Test <Long> with mode 'notNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(invalidLongPort, "invalidLongPort").notNull().validPortNumber(),
                List.of("invalidLongPort", "128247", "is a valid port number"));

        // Test <Integer> with mode 'notNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(invalidIntegerPort, "invalidIntegerPort").notNull().validPortNumber(),
                List.of("invalidIntegerPort", "65536", "is a valid port number"));

        // Test <Long> with mode 'ifNotNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(invalidLongPort, "invalidLongPort").ifNotNull().validPortNumber(),
                List.of("invalidLongPort", "128247", "is a valid port number"));

        // Test <Integer> with mode 'ifNotNull()'
        ExceptionMessageCheck.assertThrowsAndMessageReadsLike(
                () -> Validator.expect(invalidIntegerPort, "invalidIntegerPort").ifNotNull().validPortNumber(),
                List.of("invalidIntegerPort", "65536", "is a valid port number"));
    }
}