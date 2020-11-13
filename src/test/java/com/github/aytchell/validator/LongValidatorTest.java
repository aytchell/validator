package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.aytchell.validator.ExceptionMessageCheck.assertThrowsAndMessageReadsLike;

public class LongValidatorTest {
    @Test
    void isNullGivenNullThrows() {
        final Short nullShort = null;
        final Integer nullInteger = null;
        final Long nullLong = null;

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(nullShort, "nullShort", "more info").notNull(),
                List.of("nullShort", "more info", "is not null"));

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(nullInteger, "nullInteger", "more info").notNull(),
                List.of("nullInteger", "more info", "is not null"));

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(nullLong, "nullLong", "more info").notNull(),
                List.of("nullLong", "more info", "is not null"));
    }

    @Test
    void greaterThanGivenValidValuesPass() throws ValidationException {
        final Short validShort = 12;
        final Integer validInteger = 42;
        final Long validLong = 42L;
        final Long nullLong = null;

        Validator.expect(validShort).notNull().greaterThan(2);
        Validator.expect(validShort, "validShort").notNull().greaterThan(4L);
        Validator.expect(validInteger).notNull().greaterThan(2);
        Validator.expect(validInteger, "validInteger").notNull().greaterThan(41);
        Validator.expect(validLong).notNull().greaterThan(2);
        Validator.expect(validLong, "validLong").notNull().greaterThan(41);

        Validator.expect(nullLong, "nullLong").ifNotNull().greaterThan(2);
        Validator.expect(nullLong, "nullLong").ifNotNull().greaterThan(2048L);
    }

    @Test
    void greaterThanGivenHighValuesThrows() {
        final Long validLong = 42L;
        final Integer validInteger = 112;

        // Test <Long> against <long>  with mode 'notNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validLong, "validLong").notNull().greaterThan(2048),
                List.of("validLong", "42", "is greater than", "2048"));

        // Test <Integer> against <long>  with mode 'notNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validInteger, "validInteger", "info").notNull().greaterThan(112L),
                List.of("validInteger", "112", "info", "is greater than", "112"));
    }

    @Test
    void ifNotNullAndGreaterThanGivenHighValuesThrows() {
        final Long validLong = 42L;
        final Integer validInteger = 112;

        // Test <Long> against <long>  with mode 'ifNotNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validLong, "validLong").ifNotNull().greaterThan(2048L),
                List.of("validLong", "42", "is greater than", "2048"));

        // Test <Integer> against <int>  with mode 'ifNotNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validInteger, "validInteger").ifNotNull().greaterThan(1024),
                List.of("validInteger", "112", "is greater than", "1024"));
    }

    @Test
    void greaterThanWithOtherNameGiven() {
        final Long validLong = 42L;

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validLong, "validLong").ifNotNull()
                        .greaterThan(42, "otherInt"),
                List.of("validLong", "42", "is greater than", "otherInt", "42"));
    }

    @Test
    void greaterEqThanGivenValidValuesPass() throws ValidationException {
        final Short validShort = 12;
        final Integer validInteger = 42;
        final Long validLong = 42L;
        final Long nullLong = null;

        Validator.expect(validShort, "validShort").notNull().greaterEqThan(2);
        Validator.expect(validShort, "validShort").notNull().greaterEqThan(4L);
        Validator.expect(validInteger, "validInteger").notNull().greaterEqThan(2);
        Validator.expect(validInteger, "validInteger").notNull().greaterEqThan(42);
        Validator.expect(validLong, "validLong").notNull().greaterEqThan(2);
        Validator.expect(validLong, "validLong").notNull().greaterEqThan(4L);

        Validator.expect(nullLong, "nullLong").ifNotNull().greaterEqThan(4L);
        Validator.expect(nullLong, "nullLong").ifNotNull().greaterEqThan(1024);
    }

    @Test
    void isNullGreaterEqThanGivenHighValuesThrows() {
        final Long validLong = 42L;
        final Integer validInteger = 112;

        // Test <Long> against <long>  with mode 'notNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validLong, "validLong").notNull().greaterEqThan(2048L),
                List.of("validLong", "42", "is greater or equal", "2048"));

        // Test <Integer> against <int>  with mode 'notNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validInteger, "validInteger").notNull().greaterEqThan(1024),
                List.of("validInteger", "112", "is greater or equal", "1024"));
    }

    @Test
    void isNotNullAndGreaterEqThanGivenHighValuesThrows() {
        final Long validLong = 42L;
        final Integer validInteger = 112;

        // Test <Long> against <long>  with mode 'ifNotNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validLong, "validLong").ifNotNull().greaterEqThan(2048L),
                List.of("validLong", "42", "is greater or equal", "2048"));

        // Test <Integer> against <int>  with mode 'ifNotNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validInteger, "validInteger").ifNotNull().greaterEqThan(1024),
                List.of("validInteger", "112", "is greater or equal", "1024"));
    }

    @Test
    void greaterEqThanWithOtherNameGiven() {
        final Long validLong = 42L;

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validLong, "validLong").ifNotNull()
                        .greaterEqThan(2000L, "otherLong"),
                List.of("validLong", "42", "is greater or equal", "otherLong", "2000"));
    }

    @Test
    void lessThanGivenValidValuesPass() throws ValidationException {
        final Short validShort = -12;
        final Integer validInteger = -42;
        final Long validLong = -42L;
        final Long nullLong = null;

        Validator.expect(validShort).notNull().lessThan(2);
        Validator.expect(validShort, "validShort").notNull().lessThan(4L);
        Validator.expect(validInteger).notNull().lessThan(2);
        Validator.expect(validInteger, "validInteger").notNull().lessThan(41);
        Validator.expect(validLong).notNull().lessThan(2);
        Validator.expect(validLong, "validLong").notNull().lessThan(4L);

        Validator.expect(nullLong, "nullLong").ifNotNull().lessThan(4L);
    }

    @Test
    void lessThanGivenSmallValuesThrows() {
        final Long validLong = 42L;
        final Integer validInteger = 112;

        // Test <Long> against <long>  with mode 'notNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validLong, "validLong").notNull().lessThan(24L),
                List.of("validLong", "42", "is less than", "24"));

        // Test <Integer> against <int>  with mode 'notNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validInteger, "validInteger").notNull().lessThan(12),
                List.of("validInteger", "112", "is less than", "12"));
    }

    @Test
    void ifNotNullAndLessThanGivenSmallValuesThrows() {
        final Long validLong = 42L;
        final Integer validInteger = 112;

        // Test <Long> against <long>  with mode 'ifNotNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validLong, "validLong").ifNotNull().lessThan(24L),
                List.of("validLong", "42", "is less than", "24"));

        // Test <Integer> against <int>  with mode 'ifNotNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validInteger, "validInteger").ifNotNull().lessThan(12),
                List.of("validInteger", "112", "is less than", "12"));
    }

    @Test
    void lessThanWithOtherNameGiven() {
        final Long validLong = 42L;

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validLong, "validLong").ifNotNull()
                        .lessThan(20L, "otherLong"),
                List.of("validLong", "42", "is less than", "otherLong", "20"));
    }


    @Test
    void isLessEqThanGivenValidValuesPass() throws ValidationException {
        final Short validShort = 10;
        final Integer validInteger = 110;
        final Long validLong = 42L;
        final Long nullLong = null;

        Validator.expect(validShort, "validShort").notNull().lessEqThan(10);
        Validator.expect(validShort, "validShort").notNull().lessEqThan(96L);
        Validator.expect(validInteger, "validInteger").notNull().lessEqThan(1024);
        Validator.expect(validInteger, "validInteger").notNull().lessEqThan(110);
        Validator.expect(validLong, "validLong").notNull().lessEqThan(42);
        Validator.expect(validLong, "validLong").notNull().lessEqThan(4096L);

        Validator.expect(nullLong, "nullLong").ifNotNull().lessEqThan(2);
        Validator.expect(nullLong, "nullLong").ifNotNull().lessEqThan(4L);
        Validator.expect(nullLong, "nullLong").ifNotNull().lessEqThan(1024);
        Validator.expect(nullLong, "nullLong").ifNotNull().lessEqThan(2048L);
    }

    @Test
    void isNullIsLessEqThanGivenLowValuesThrows() {
        final Long validLong = 42L;
        final Integer validInteger = 1729;

        // Test <Long> against <long>  with mode 'notNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validLong, "validLong").notNull().lessEqThan(5L),
                List.of("validLong", "42", "is less or equal", "5"));

        // Test <Integer> against <int>  with mode 'notNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validInteger, "validInteger").notNull().lessEqThan(3),
                List.of("validInteger", "1729", "is less or equal", "3"));
    }

    @Test
    void isNotNullAndLessEqThanGivenLowValuesThrows() {
        final Long validLong = 42L;
        final Integer validInteger = 1729;

        // Test <Long> against <long>  with mode 'ifNotNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validLong, "validLong").ifNotNull().lessEqThan(5L),
                List.of("validLong", "42", "is less or equal", "5"));

        // Test <Integer> against <int>  with mode 'ifNotNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validInteger, "validInteger").ifNotNull().lessEqThan(3),
                List.of("validInteger", "1729", "is less or equal", "3"));
    }

    @Test
    void lessEqThanWithOtherNameGiven() {
        final Long validLong = 4200L;

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(validLong, "validLong").ifNotNull()
                        .lessEqThan(1024, "otherInt"),
                List.of("validLong", "4200", "is less or equal", "otherInt", "1024"));
    }

    @Test
    void isNotValidPortNumberGivenValidValuesPasses() throws ValidationException {
        final Long validLongPort = 19377L;
        final Integer validIntegerPort = 4711;
        final Short nullShort = null;

        Validator.expect(validLongPort, "validLongPort").notNull().validPortNumber();
        Validator.expect(validIntegerPort, "validIntegerPort").notNull().validPortNumber();

        Validator.expect(validLongPort, "validLongPort").ifNotNull().validPortNumber();
        Validator.expect(validIntegerPort, "validIntegerPort").ifNotNull().validPortNumber();

        Validator.expect(nullShort).ifNotNull().validPortNumber();
    }

    @Test
    void isNotValidPortNumberGivenLowValuesThrows() {
        final Long invalidLongPort = 0L;
        final Integer invalidIntegerPort = -5;

        // Test <Long> with mode 'notNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(invalidLongPort, "invalidLongPort").notNull().validPortNumber(),
                List.of("invalidLongPort", "0", "is a valid port number"));

        // Test <Integer> with mode 'notNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(invalidIntegerPort, "invalidIntegerPort").notNull().validPortNumber(),
                List.of("invalidIntegerPort", "-5", "is a valid port number"));

        // Test <Long> with mode 'ifNotNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(invalidLongPort, "invalidLongPort").ifNotNull().validPortNumber(),
                List.of("invalidLongPort", "0", "is a valid port number"));

        // Test <Integer> with mode 'ifNotNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(invalidIntegerPort, "invalidIntegerPort").ifNotNull().validPortNumber(),
                List.of("invalidIntegerPort", "-5", "is a valid port number"));
    }

    @Test
    void isNotValidPortNumberGivenHighValuesThrows() {
        final Long invalidLongPort = 128247L;
        final Integer invalidIntegerPort = 65536;

        // Test <Long> with mode 'notNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(invalidLongPort, "invalidLongPort").notNull().validPortNumber(),
                List.of("invalidLongPort", "128247", "is a valid port number"));

        // Test <Integer> with mode 'notNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(invalidIntegerPort, "invalidIntegerPort").notNull().validPortNumber(),
                List.of("invalidIntegerPort", "65536", "is a valid port number"));

        // Test <Long> with mode 'ifNotNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(invalidLongPort, "invalidLongPort").ifNotNull().validPortNumber(),
                List.of("invalidLongPort", "128247", "is a valid port number"));

        // Test <Integer> with mode 'ifNotNull()'
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(invalidIntegerPort, "invalidIntegerPort").ifNotNull().validPortNumber(),
                List.of("invalidIntegerPort", "65536", "is a valid port number"));
    }
}
