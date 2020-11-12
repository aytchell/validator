package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

// This class shall only be instantiated by Validator
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class ArmedLongValidator extends LongValidatorBase {
    private static final int MIN_TCP_PORT_NUMBER = 1;
    private static final int MAX_TCP_PORT_NUMBER = 65535;

    private final Long value;
    private final String name;

    @Override
    public LongValidator isSmallerThan(long lowerBound) throws ValidationException {
        if (value < lowerBound) {
            throw newExceptionWithNameAndValue()
                    .setExpectation("shall not be smaller than")
                    .setExpectedValue(String.valueOf(lowerBound));
        }
        return this;
    }

    @Override
    public LongValidator isGreaterThan(long upperBound) throws ValidationException {
        if (value > upperBound) {
            throw newExceptionWithNameAndValue()
                    .setExpectation("shall not be greater than")
                    .setExpectedValue(String.valueOf(upperBound));
        }
        return this;
    }

    @Override
    public LongValidator isNoValidPortNumber() throws ValidationException {
        if ((value < MIN_TCP_PORT_NUMBER) || (value > MAX_TCP_PORT_NUMBER)) {
            throw newExceptionWithNameAndValue()
                    .setExpectation(
                            String.format("shall be a valid port number (%d to %d)",
                                    MIN_TCP_PORT_NUMBER, MAX_TCP_PORT_NUMBER));
        }
        return this;
    }

    private ValidationException newExceptionWithNameAndValue() {
        return new ValidationException()
                .setActualValuesName(name)
                .setActualValue(String.valueOf(value));
    }
}
