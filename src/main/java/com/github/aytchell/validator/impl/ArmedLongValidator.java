package com.github.aytchell.validator.impl;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.LongValidator;
import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.function.LongPredicate;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
class ArmedLongValidator extends LongValidatorBase {
    private static final int MIN_TCP_PORT_NUMBER = 1;
    private static final int MAX_TCP_PORT_NUMBER = 65535;

    private final Long value;
    private final String name;
    private final String extraInfo;

    @Override
    public LongValidator greaterThan(long lowerBound, String otherName) throws ValidationException {
        if (value <= lowerBound) {
            throw newExceptionWithBasics()
                    .setExpectation("is greater than")
                    .setExpectedValuesName(otherName)
                    .setExpectedValue(lowerBound);
        }
        return this;
    }

    @Override
    public LongValidator greaterEqThan(long lowerBound, String otherName) throws ValidationException {
        if (value < lowerBound) {
            throw newExceptionWithBasics()
                    .setExpectation("is greater or equal than")
                    .setExpectedValuesName(otherName)
                    .setExpectedValue(lowerBound);
        }
        return this;
    }

    @Override
    public LongValidator lessThan(long upperBound, String otherName) throws ValidationException {
        if (value >= upperBound) {
            throw newExceptionWithBasics()
                    .setExpectation("is less than")
                    .setExpectedValuesName(otherName)
                    .setExpectedValue(upperBound);
        }
        return this;
    }

    @Override
    public LongValidator lessEqThan(long upperBound, String otherName) throws ValidationException {
        if (value > upperBound) {
            throw newExceptionWithBasics()
                    .setExpectation("is less or equal than")
                    .setExpectedValuesName(otherName)
                    .setExpectedValue(upperBound);
        }
        return this;
    }

    @Override
    public LongValidator validPortNumber() throws ValidationException {
        if ((value < MIN_TCP_PORT_NUMBER) || (value > MAX_TCP_PORT_NUMBER)) {
            throw newExceptionWithBasics()
                    .setExpectation(
                            String.format("is a valid port number (%d to %d)",
                                    MIN_TCP_PORT_NUMBER, MAX_TCP_PORT_NUMBER));
        }
        return this;
    }

    @Override
    public LongValidator passes(LongPredicate predicate, String expectation) throws ValidationException {
        if (!predicate.test(value)) {
            throw newExceptionWithBasics()
                    .setExpectation(expectation + " (but is not)");
        }
        return this;
    }

    private ValidationException newExceptionWithBasics() {
        return new ValidationException()
                .setActualValuesName(name)
                .setActualValue(value)
                .setValuesExtraInfo(extraInfo);
    }
}
