package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.LongValidator;
import com.github.aytchell.validator.exceptions.ValidationException;

abstract class LongValidatorBase implements LongValidator {
    @Override
    public LongValidator greaterEqThan(int lowerBound) throws ValidationException {
        return greaterEqThan((long) lowerBound);
    }

    @Override
    public LongValidator lessEqThan(int upperBound) throws ValidationException {
        return lessEqThan((long) upperBound);
    }
}
