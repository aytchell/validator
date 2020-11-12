package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

// This class shall only be derived by package internal classes
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
