package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface LongValidator {
    LongValidator isLowerThan(int lowerBound) throws ValidationException;

    LongValidator isLowerThan(long lowerBound) throws ValidationException;

    LongValidator isGreaterThan(int upperBound) throws ValidationException;

    LongValidator isGreaterThan(long upperBound) throws ValidationException;

    LongValidator isNoValidPortNumber() throws ValidationException;
}
