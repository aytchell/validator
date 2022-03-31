package com.github.aytchell.validator.impl;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.OffsetDateTimeValidator;
import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.OffsetDateTime;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
class ArmedOffsetDateTimeValidator extends OffsetDateTimeValidatorBase {
    private final OffsetDateTime value;
    private final String name;
    private final String extraInfo;

    @Override
    public OffsetDateTimeValidator isBefore(OffsetDateTime otherDateTime, String otherName) throws ValidationException {
        if (!this.value.isBefore(otherDateTime)) {
            throw newExceptionWithBasics()
                    .setExpectedValuesName(otherName)
                    .setExpectedValue(otherDateTime)
                    .setExpectation("is before");
        }
        return this;
    }

    @Override
    public OffsetDateTimeValidator isAfter(OffsetDateTime otherDateTime, String otherName) throws ValidationException {
        if (!this.value.isAfter(otherDateTime)) {
            throw newExceptionWithBasics()
                    .setExpectedValuesName(otherName)
                    .setExpectedValue(otherDateTime)
                    .setExpectation("is after");
        }
        return this;
    }

    private ValidationException newExceptionWithBasics() {
        return new ValidationException()
                .setActualValue(value)
                .setActualValuesName(name)
                .setValuesExtraInfo(extraInfo);
    }
}
