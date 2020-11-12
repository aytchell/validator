package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

// This class shall only be derived by package internal classes
abstract class LongValidatorBase implements LongValidator {
    @Override
    public LongValidator isSmallerThan(int lowerBound) throws ValidationException {
        return isSmallerThan((long) lowerBound);
    }

    @Override
    public LongValidator isGreaterThan(int upperBound) throws ValidationException {
        return isGreaterThan((long) upperBound);
    }
}
