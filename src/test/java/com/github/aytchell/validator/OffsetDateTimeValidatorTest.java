package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.List;

import static com.github.aytchell.validator.ExceptionMessageCheck.assertThrowsAndMessageReadsLike;

public class OffsetDateTimeValidatorTest {
    @Test
    void isNullGivenNullThrows() {
        final LocalDate nullLocalDate = null;
        final LocalDateTime nullLocalDateTime = null;
        final OffsetDateTime nullOffsetDateTime = null;
        final ZonedDateTime nullZonedDateTime = null;

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(nullLocalDate, "nullLocalDate").notNull(),
                List.of("nullLocalDate", "is not null"));

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(nullLocalDateTime, "nullLocalDateTime").notNull(),
                List.of("nullLocalDateTime", "is not null"));

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(nullOffsetDateTime, "nullOffsetDateTime").notNull(),
                List.of("nullOffsetDateTime", "is not null"));

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(nullZonedDateTime, "nullZonedDateTime").notNull(),
                List.of("nullZonedDateTime", "is not null"));
    }

    @Test
    void compareDatesCorrectly() throws ValidationException {
        final LocalDate early = LocalDate.of(1912, 1, 1);
        final LocalDateTime earlier = LocalDateTime.of(1901, 1, 1, 12, 34);
        final OffsetDateTime earliest = OffsetDateTime.of (1876, 3, 1,
                4, 1, 5, 9, ZoneOffset.UTC);
        final ZonedDateTime archaic = ZonedDateTime.of(1796, 3, 4,
                5, 34, 12, 12, ZoneId.of("GMT+3"));

        final LocalDate late = LocalDate.of(2000, 12, 31);
        final LocalDateTime later = LocalDateTime.of (2010, 12, 31, 1, 0);
        final OffsetDateTime latest = OffsetDateTime.of(2020, 12, 1, 5, 4,
                3, 2, ZoneOffset.UTC);
        final ZonedDateTime future = ZonedDateTime.of(2050, 12, 31, 1, 0,
                0, 0, ZoneId.of("GMT-13"));

        // make sure each parameter type is used for each method
        Validator.expect(early).notNull().isBefore(future);
        Validator.expect(earlier).notNull().isBefore(early);
        Validator.expect(earliest).notNull().isBefore(earlier);
        Validator.expect(archaic).notNull().isBefore(earliest);

        // make sure each parameter type is used for each method
        Validator.expect(late).notNull().isAfter(archaic);
        Validator.expect(later).notNull().isAfter(late);
        Validator.expect(latest).notNull().isAfter(later);
        Validator.expect(future).notNull().isAfter(latest);
    }

    @Test
    void compareLocalDateIncorrectly() throws ValidationException {
        final LocalDate early = LocalDate.of(1912, 1, 1);
        final LocalDate late = LocalDate.of(2050, 12, 31);

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(early, "early", "start of things")
                        .notNull().isAfter(late, "end of things"),
                List.of("early", "1912", "start of things", "is after", "end of things", "2050"));

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(late, "late", "future")
                        .notNull().isBefore(early, "past"),
                List.of("late", "2050", "future", "is before", "past", "1912"));
    }

    @Test
    void compareLocalDateTimeIncorrectly() throws ValidationException {
        final LocalDateTime early = LocalDateTime.of(1912, 1, 1, 12, 0);
        final LocalDateTime late = LocalDateTime.of(2050, 12, 31, 12, 0);

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(early, "early", "start of things")
                        .notNull().isAfter(late, "end of things"),
                List.of("early", "1912", "start of things", "is after", "end of things", "2050"));

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(late, "late", "future")
                        .notNull().isBefore(early, "past"),
                List.of("late", "2050", "future", "is before", "past", "1912"));
    }

    @Test
    void compareOffsetDateTimeIncorrectly() throws ValidationException {
        final OffsetDateTime early = OffsetDateTime.of(1912, 1, 1, 12, 0,
                0, 0, ZoneOffset.UTC);

        final OffsetDateTime late = OffsetDateTime.of(2050, 12, 31, 12, 0,
                0, 0, ZoneOffset.UTC);

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(early, "early", "start of things")
                        .notNull().isAfter(late, "end of things"),
                List.of("early", "1912", "start of things", "is after", "end of things", "2050"));

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(late, "late", "future")
                        .notNull().isBefore(early, "past"),
                List.of("late", "2050", "future", "is before", "past", "1912"));
    }

    @Test
    void compareZonedDateTimeIncorrectly() throws ValidationException {
        final ZonedDateTime early = ZonedDateTime.of(1912, 1, 1, 12, 0,
                0, 0, ZoneId.of("GMT+2"));

        final ZonedDateTime late = ZonedDateTime.of(2050, 12, 31, 12, 0,
                0, 0, ZoneId.of("GMT+2"));

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(early, "early", "start of things")
                        .notNull().isAfter(late, "end of things"),
                List.of("early", "1912", "start of things", "is after", "end of things", "2050"));

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(late, "late", "future")
                        .notNull().isBefore(early, "past"),
                List.of("late", "2050", "future", "is before", "past", "1912"));
    }

    @Test
    void ifLocalDateIsNullEverythingPasses() throws ValidationException {
        final LocalDate nullLocalDate = null;
        final LocalDateTime nullLocalDateTime = null;
        final OffsetDateTime nullOffsetDateTime = null;
        final ZonedDateTime nullZonedDateTime = null;

        final LocalDate past = LocalDate.of(1900, 1, 1);
        final LocalDate future = LocalDate.of(2050, 1, 1);

        // these tests will only pass if they are not performed at all
        Validator.expect(nullLocalDate).ifNotNull().isAfter(future).isBefore(past);
        Validator.expect(nullLocalDateTime).ifNotNull().isAfter(future).isBefore(past);
        Validator.expect(nullOffsetDateTime).ifNotNull().isAfter(future).isBefore(past);
        Validator.expect(nullZonedDateTime).ifNotNull().isAfter(future).isBefore(past);
    }
}
