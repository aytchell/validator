package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.ZonedDateTimeValidator;
import com.github.aytchell.validator.exceptions.ValidationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

abstract class ZonedDateTimeValidatorBase implements ZonedDateTimeValidator {
    @Override
    public ZonedDateTimeValidator isBefore(LocalDate otherDate) throws ValidationException {
        return isBefore(otherDate, null);
    }

    @Override
    public ZonedDateTimeValidator isBefore(LocalDate otherDate, String otherName) throws ValidationException {
        return isBefore(wrapValue(otherDate), otherName);
    }

    @Override
    public ZonedDateTimeValidator isAfter(LocalDate otherDate) throws ValidationException {
        return isAfter(otherDate, null);
    }

    @Override
    public ZonedDateTimeValidator isAfter(LocalDate otherDate, String otherName) throws ValidationException {
        return isAfter(wrapValue(otherDate), otherName);
    }

    @Override
    public ZonedDateTimeValidator isBefore(LocalDateTime otherDateTime) throws ValidationException {
        return isBefore(otherDateTime, null);
    }

    @Override
    public ZonedDateTimeValidator isBefore(LocalDateTime otherDateTime, String otherName) throws ValidationException {
        return isBefore(wrapValue(otherDateTime), otherName);
    }

    @Override
    public ZonedDateTimeValidator isAfter(LocalDateTime otherDateTime) throws ValidationException {
        return isAfter(otherDateTime, null);
    }

    @Override
    public ZonedDateTimeValidator isAfter(LocalDateTime otherDateTime, String otherName) throws ValidationException {
        return isAfter(wrapValue(otherDateTime), otherName);
    }

    @Override
    public ZonedDateTimeValidator isBefore(ZonedDateTime otherDateTime) throws ValidationException {
        return isBefore(otherDateTime, null);
    }

    @Override
    public ZonedDateTimeValidator isAfter(ZonedDateTime otherDateTime) throws ValidationException {
        return isAfter(otherDateTime, null);
    }

    private ZonedDateTime wrapValue(LocalDate value) {
        return wrapValue(LocalDateTime.of(value, LocalTime.of(0, 0)));
    }

    private ZonedDateTime wrapValue(LocalDateTime value) {
        return ZonedDateTime.of(value, ZoneId.of("UTC"));
    }
}
