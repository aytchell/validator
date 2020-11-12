package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.LongValidator;
import com.github.aytchell.validator.exceptions.ValidationException;

abstract class LongValidatorBase implements LongValidator {
    @Override
    public LongValidator greaterEqThan(int lowerBound) throws ValidationException {
        return greaterEqThan((long) lowerBound, null);
    }

    @Override
    public LongValidator greaterEqThan(int lowerBound, String otherName) throws ValidationException {
        return greaterEqThan((long) lowerBound, otherName);
    }

    @Override
    public LongValidator greaterEqThan(long lowerBound) throws ValidationException {
        return greaterEqThan(lowerBound, null);
    }

    @Override
    public LongValidator lessEqThan(int upperBound) throws ValidationException {
        return lessEqThan((long)upperBound, null);
    }

    @Override
    public LongValidator lessEqThan(int upperBound, String otherName) throws ValidationException {
        return lessEqThan((long)upperBound, otherName);
    }

    @Override
    public LongValidator lessEqThan(long upperBound) throws ValidationException {
        return lessEqThan(upperBound, null);
    }
}
