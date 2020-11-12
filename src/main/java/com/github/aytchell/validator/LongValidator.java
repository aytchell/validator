package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface LongValidator {
    LongValidator greaterEqThan(int lowerBound) throws ValidationException;

    LongValidator greaterEqThan(int lowerBound, String otherName) throws ValidationException;

    LongValidator greaterEqThan(long lowerBound) throws ValidationException;

    LongValidator greaterEqThan(long lowerBound, String otherName) throws ValidationException;

    LongValidator lessEqThan(int upperBound) throws ValidationException;

    LongValidator lessEqThan(int upperBound, String otherName) throws ValidationException;

    LongValidator lessEqThan(long upperBound) throws ValidationException;

    LongValidator lessEqThan(long upperBound, String otherName) throws ValidationException;

    LongValidator validPortNumber() throws ValidationException;
}
