package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public interface ZonedDateTimeValidator {
    // -- checks against LocalDate ---
    ZonedDateTimeValidator isBefore(LocalDate otherDateTime) throws ValidationException;

    ZonedDateTimeValidator isBefore(LocalDate otherDateTime, String otherName) throws ValidationException;

    ZonedDateTimeValidator isAfter(LocalDate otherDateTime) throws ValidationException;

    ZonedDateTimeValidator isAfter(LocalDate otherDateTime, String otherName) throws ValidationException;

    // -- checks against LocalDateTime ---
    ZonedDateTimeValidator isBefore(LocalDateTime otherDateTime) throws ValidationException;

    ZonedDateTimeValidator isBefore(LocalDateTime otherDateTime, String otherName) throws ValidationException;

    ZonedDateTimeValidator isAfter(LocalDateTime otherDateTime) throws ValidationException;

    ZonedDateTimeValidator isAfter(LocalDateTime otherDateTime, String otherName) throws ValidationException;

    // -- checks against ZonedDateTime ---
    ZonedDateTimeValidator isBefore(ZonedDateTime otherDateTime) throws ValidationException;

    ZonedDateTimeValidator isBefore(ZonedDateTime otherDateTime, String otherName) throws ValidationException;

    ZonedDateTimeValidator isAfter(ZonedDateTime otherDateTime) throws ValidationException;

    ZonedDateTimeValidator isAfter(ZonedDateTime otherDateTime, String otherName) throws ValidationException;
}
