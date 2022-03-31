package com.github.aytchell.validator.impl;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.OffsetDateTimeValidator;
import com.github.aytchell.validator.exceptions.ValidationException;

import java.time.*;

abstract class OffsetDateTimeValidatorBase implements OffsetDateTimeValidator {
    @Override
    public OffsetDateTimeValidator isBefore(LocalDate otherDate) throws ValidationException {
        return isBefore(otherDate, null);
    }

    @Override
    public OffsetDateTimeValidator isBefore(LocalDate otherDate, String otherName) throws ValidationException {
        return isBefore(wrapValue(otherDate), otherName);
    }

    @Override
    public OffsetDateTimeValidator isAfter(LocalDate otherDate) throws ValidationException {
        return isAfter(otherDate, null);
    }

    @Override
    public OffsetDateTimeValidator isAfter(LocalDate otherDate, String otherName) throws ValidationException {
        return isAfter(wrapValue(otherDate), otherName);
    }

    @Override
    public OffsetDateTimeValidator isBefore(LocalDateTime otherDateTime) throws ValidationException {
        return isBefore(otherDateTime, null);
    }

    @Override
    public OffsetDateTimeValidator isBefore(LocalDateTime otherDateTime, String otherName) throws ValidationException {
        return isBefore(wrapValue(otherDateTime), otherName);
    }

    @Override
    public OffsetDateTimeValidator isAfter(LocalDateTime otherDateTime) throws ValidationException {
        return isAfter(otherDateTime, null);
    }

    @Override
    public OffsetDateTimeValidator isAfter(LocalDateTime otherDateTime, String otherName) throws ValidationException {
        return isAfter(wrapValue(otherDateTime), otherName);
    }

    @Override
    public OffsetDateTimeValidator isBefore(ZonedDateTime otherDateTime) throws ValidationException {
        return isBefore(otherDateTime, null);
    }

    @Override
    public OffsetDateTimeValidator isBefore(ZonedDateTime otherDateTime, String otherName) throws ValidationException {
        return isBefore(wrapValue(otherDateTime), otherName);
    }

    @Override
    public OffsetDateTimeValidator isAfter(ZonedDateTime otherDateTime) throws ValidationException {
        return isAfter(otherDateTime, null);
    }

    @Override
    public OffsetDateTimeValidator isAfter(ZonedDateTime otherDateTime, String otherName) throws ValidationException {
        return isAfter(wrapValue(otherDateTime), otherName);
    }

    @Override
    public OffsetDateTimeValidator isBefore(OffsetDateTime otherDateTime) throws ValidationException {
        return isBefore(otherDateTime, null);
    }

    @Override
    public OffsetDateTimeValidator isAfter(OffsetDateTime otherDateTime) throws ValidationException {
        return isAfter(otherDateTime, null);
    }

    private OffsetDateTime wrapValue(LocalDate value) {
        return wrapValue(LocalDateTime.of(value, LocalTime.of(0, 0)));
    }

    private OffsetDateTime wrapValue(LocalDateTime value) {
        return OffsetDateTime.of(value, ZoneOffset.UTC);
    }

    private OffsetDateTime wrapValue(ZonedDateTime value) {
        return value.toOffsetDateTime();
    }
}
