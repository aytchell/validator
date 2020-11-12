package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface LongValidator {
    LongValidator greaterEqThan(int lowerBound) throws ValidationException;

    LongValidator greaterEqThan(long lowerBound) throws ValidationException;

    LongValidator lessEqThan(int upperBound) throws ValidationException;

    LongValidator lessEqThan(long upperBound) throws ValidationException;

    LongValidator validPortNumber() throws ValidationException;
}
