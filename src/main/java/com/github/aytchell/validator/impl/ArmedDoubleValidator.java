package com.github.aytchell.validator.impl;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.DoubleValidator;
import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
class ArmedDoubleValidator extends DoubleValidatorBase {
    private final Double value;
    private final String name;
    private final String extraInfo;

    @Override
    public DoubleValidator greaterThan(double lowerBound, String otherName) throws ValidationException {
        if (value <= lowerBound) {
            throw newExceptionWithBasics()
                    .setExpectation("is greater than")
                    .setExpectedValuesName(otherName)
                    .setExpectedValue(lowerBound);
        }
        return this;
    }

    @Override
    public DoubleValidator greaterEqThan(double lowerBound, String otherName) throws ValidationException {
        if (value < lowerBound) {
            throw newExceptionWithBasics()
                    .setExpectation("is greater or equal than")
                    .setExpectedValuesName(otherName)
                    .setExpectedValue(lowerBound);
        }
        return this;
    }

    @Override
    public DoubleValidator lessThan(double upperBound, String otherName) throws ValidationException {
        if (value >= upperBound) {
            throw newExceptionWithBasics()
                    .setExpectation("is less than")
                    .setExpectedValuesName(otherName)
                    .setExpectedValue(upperBound);
        }
        return this;
    }

    @Override
    public DoubleValidator lessEqThan(double upperBound, String otherName) throws ValidationException {
        if (value > upperBound) {
            throw newExceptionWithBasics()
                    .setExpectation("is less or equal than")
                    .setExpectedValuesName(otherName)
                    .setExpectedValue(upperBound);
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
