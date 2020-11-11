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
    public LongValidator isLowerThan(long lowerBound) throws ValidationException {
        if (value < lowerBound) {
            throw new ValidationException(
                String.format("Parameter '%s' (given value: %d) is too small (min. value: %d)",
                    name, value, lowerBound));
        }
        return this;
    }

    @Override
    public LongValidator isGreaterThan(long upperBound) throws ValidationException {
        if (value > upperBound) {
            throw new ValidationException(
                String.format("Parameter '%s' (given value: %d) is too big (max. value: %d)",
                    name, value, upperBound));
        }
        return this;
    }

    @Override
    public LongValidator isNoValidPortNumber() throws ValidationException {
        if ((value < MIN_TCP_PORT_NUMBER) || (value > MAX_TCP_PORT_NUMBER)) {
            throw new ValidationException(
                String.format("Parameter '%s' (given value: %d) is no valid port number", name, value));
        }
        return this;
    }
}
