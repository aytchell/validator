package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.LongValidator;
import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
class ArmedLongValidator extends LongValidatorBase {
    private static final int MIN_TCP_PORT_NUMBER = 1;
    private static final int MAX_TCP_PORT_NUMBER = 65535;

    private final Long value;
    private final String name;

    @Override
    public LongValidator greaterEqThan(long lowerBound) throws ValidationException {
        if (value < lowerBound) {
            throw newExceptionWithNameAndValue()
                    .setExpectation("greater or eqal than")
                    .setExpectedValue(lowerBound);
        }
        return this;
    }

    @Override
    public LongValidator lessEqThan(long upperBound) throws ValidationException {
        if (value > upperBound) {
            throw newExceptionWithNameAndValue()
                    .setExpectation("smaller or equal than")
                    .setExpectedValue(upperBound);
        }
        return this;
    }

    @Override
    public LongValidator validPortNumber() throws ValidationException {
        if ((value < MIN_TCP_PORT_NUMBER) || (value > MAX_TCP_PORT_NUMBER)) {
            throw newExceptionWithNameAndValue()
                    .setExpectation(
                            String.format("a valid port number (%d to %d)",
                                    MIN_TCP_PORT_NUMBER, MAX_TCP_PORT_NUMBER));
        }
        return this;
    }

    private ValidationException newExceptionWithNameAndValue() {
        return new ValidationException()
                .setActualValuesName(name)
                .setActualValue(value);
    }
}
