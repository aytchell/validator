package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface LongValidator {
    LongValidator gtEqThan(int lowerBound) throws ValidationException;

    LongValidator gtEqThan(long lowerBound) throws ValidationException;

    LongValidator ltEqThan(int upperBound) throws ValidationException;

    LongValidator ltEqThan(long upperBound) throws ValidationException;

    LongValidator validPortNumber() throws ValidationException;
}
