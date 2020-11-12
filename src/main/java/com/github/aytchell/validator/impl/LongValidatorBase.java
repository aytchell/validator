package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.LongValidator;
import com.github.aytchell.validator.exceptions.ValidationException;

abstract class LongValidatorBase implements LongValidator {
    @Override
    public LongValidator gtEqThan(int lowerBound) throws ValidationException {
        return gtEqThan((long) lowerBound);
    }

    @Override
    public LongValidator ltEqThan(int upperBound) throws ValidationException {
        return ltEqThan((long) upperBound);
    }
}
