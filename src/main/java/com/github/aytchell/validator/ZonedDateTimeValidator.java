package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * Specialized validator for {@link LocalDate}, {@link LocalDateTime} and {@link ZonedDateTime}
 * <p>
 * Each method of this class will check the value under test and if the check fails a {@link ValidationException} will
 * be thrown.
 */
public interface ZonedDateTimeValidator {
    /**
     * Checks that the timestamp under test is before a given other date
     * <p>
     * To be comparable with other time formats the argument {@code otherDate} is internally expanded to a {@link
     * ZonedDateTime}. The time component is set to 0:00 and the timezone is set to UTC.
     *
     * @param otherDate the other date to compare with
     * @return this validator so you can add more tests
     * @throws ValidationException if the timestamp under test is after (or at the same time than) {@code
     *         otherDate}
     */
    ZonedDateTimeValidator isBefore(LocalDate otherDate) throws ValidationException;

    /**
     * Checks that the timestamp under test is before a given other (named) date
     * <p>
     * To be comparable with other time formats the argument {@code otherDate} is internally expanded to a {@link
     * ZonedDateTime}. The time component is set to 0:00 and the timezone is set to UTC.
     *
     * @param otherDate the other date to compare with
     * @param otherName a name for the {@code otherDate} which will be included in the error message
     * @return this validator so you can add more tests
     * @throws ValidationException if the timestamp under test is after (or at the same time than) {@code
     *         otherDate}
     */
    ZonedDateTimeValidator isBefore(LocalDate otherDate, String otherName) throws ValidationException;

    /**
     * Checks that the timestamp under test is before a given other timestamp
     * <p>
     * To be comparable with other time formats the argument {@code otherDate} is internally expanded to a {@link
     * ZonedDateTime}. The timezone is set to UTC.
     *
     * @param otherDateTime the other date and time to compare with
     * @return this validator so you can add more tests
     * @throws ValidationException if the timestamp under test is after (or at the same time than) {@code
     *         otherDateTime}
     */
    ZonedDateTimeValidator isBefore(LocalDateTime otherDateTime) throws ValidationException;

    /**
     * Checks that the timestamp under test is before a given other (named) timestamp
     * <p>
     * To be comparable with other time formats the argument {@code otherDate} is internally expanded to a {@link
     * ZonedDateTime}. The timezone is set to UTC.
     *
     * @param otherDateTime the other date and time to compare with
     * @param otherName a name for the {@code otherDateTime} which will be included in the error message
     * @return this validator so you can add more tests
     * @throws ValidationException if the timestamp under test is after (or at the same time than) {@code
     *         otherDateTime}
     */
    ZonedDateTimeValidator isBefore(LocalDateTime otherDateTime, String otherName) throws ValidationException;

    /**
     * Checks that the timestamp under test is before a given other timestamp
     *
     * @param otherDateTime the other date and time to compare with
     * @return this validator so you can add more tests
     * @throws ValidationException if the timestamp under test is after (or at the same time than) {@code
     *         otherDateTime}
     */
    ZonedDateTimeValidator isBefore(ZonedDateTime otherDateTime) throws ValidationException;

    /**
     * Checks that the timestamp under test is before a given other (named) timestamp
     *
     * @param otherDateTime the other date and time to compare with
     * @param otherName a name for the {@code otherDateTime} which will be included in the error message
     * @return this validator so you can add more tests
     * @throws ValidationException if the timestamp under test is after (or at the same time than) {@code
     *         otherDateTime}
     */
    ZonedDateTimeValidator isBefore(ZonedDateTime otherDateTime, String otherName) throws ValidationException;

    /**
     * Checks that the timestamp under test is after a given other date
     * <p>
     * To be comparable with other time formats the argument {@code otherDate} is internally expanded to a {@link
     * ZonedDateTime}. The time component is set to 0:00 and the timezone is set to UTC.
     *
     * @param otherDate the other date to compare with
     * @return this validator so you can add more tests
     * @throws ValidationException if the timestamp under test is before (or at the same time than) {@code
     *         otherDate}
     */
    ZonedDateTimeValidator isAfter(LocalDate otherDate) throws ValidationException;

    /**
     * Checks that the timestamp under test is after a given other (named) date
     * <p>
     * To be comparable with other time formats the argument {@code otherDate} is internally expanded to a {@link
     * ZonedDateTime}. The time component is set to 0:00 and the timezone is set to UTC.
     *
     * @param otherDate the other date to compare with
     * @param otherName a name for the {@code otherDate} which will be included in the error message
     * @return this validator so you can add more tests
     * @throws ValidationException if the timestamp under test is before (or at the same time than) {@code
     *         otherDate}
     */
    ZonedDateTimeValidator isAfter(LocalDate otherDate, String otherName) throws ValidationException;

    /**
     * Checks that the timestamp under test is after a given other timestamp
     * <p>
     * To be comparable with other time formats the argument {@code otherDate} is internally expanded to a {@link
     * ZonedDateTime}. The timezone is set to UTC.
     *
     * @param otherDateTime the other date and time to compare with
     * @return this validator so you can add more tests
     * @throws ValidationException if the timestamp under test is before (or at the same time than) {@code
     *         otherDateTime}
     */
    ZonedDateTimeValidator isAfter(LocalDateTime otherDateTime) throws ValidationException;

    /**
     * Checks that the timestamp under test is after a given other (named) timestamp
     * <p>
     * To be comparable with other time formats the argument {@code otherDate} is internally expanded to a {@link
     * ZonedDateTime}. The timezone is set to UTC.
     *
     * @param otherDateTime the other date and time to compare with
     * @param otherName a name for the {@code otherDateTime} which will be included in the error message
     * @return this validator so you can add more tests
     * @throws ValidationException if the timestamp under test is before (or at the same time than) {@code
     *         otherDateTime}
     */
    ZonedDateTimeValidator isAfter(LocalDateTime otherDateTime, String otherName) throws ValidationException;

    /**
     * Checks that the timestamp under test is after a given other timestamp
     *
     * @param otherDateTime the other date and time to compare with
     * @return this validator so you can add more tests
     * @throws ValidationException if the timestamp under test is before (or at the same time than) {@code
     *         otherDateTime}
     */
    ZonedDateTimeValidator isAfter(ZonedDateTime otherDateTime) throws ValidationException;

    /**
     * Checks that the timestamp under test is after a given other (named) timestamp
     *
     * @param otherDateTime the other date and time to compare with
     * @param otherName a name for the {@code otherDateTime} which will be included in the error message
     * @return this validator so you can add more tests
     * @throws ValidationException if the timestamp under test is before (or at the same time than) {@code
     *         otherDateTime}
     */
    ZonedDateTimeValidator isAfter(ZonedDateTime otherDateTime, String otherName) throws ValidationException;
}
