package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.LongValidator;
import com.github.aytchell.validator.exceptions.ValidationException;

abstract class LongValidatorBase implements LongValidator {
    @Override
    public LongValidator greaterThan(long lowerBound) throws ValidationException {
        return greaterThan(lowerBound, null);
    }

    @Override
    public LongValidator greaterEqThan(long lowerBound) throws ValidationException {
        return greaterEqThan(lowerBound, null);
    }

    @Override
    public LongValidator lessThan(long upperBound) throws ValidationException {
        return lessThan(upperBound, null);
    }

    @Override
    public LongValidator lessEqThan(long upperBound) throws ValidationException {
        return lessEqThan(upperBound, null);
    }
}
