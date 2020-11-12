package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface LongValidator {
    LongValidator isSmallerThan(int lowerBound) throws ValidationException;

    LongValidator isSmallerThan(long lowerBound) throws ValidationException;

    LongValidator isGreaterThan(int upperBound) throws ValidationException;

    LongValidator isGreaterThan(long upperBound) throws ValidationException;

    LongValidator isNoValidPortNumber() throws ValidationException;
}
