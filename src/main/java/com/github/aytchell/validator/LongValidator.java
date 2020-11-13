package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface LongValidator {
    // ----- greater than -----
    LongValidator greaterThan(long lowerBound) throws ValidationException;

    LongValidator greaterThan(long lowerBound, String otherName) throws ValidationException;

    // ----- greater or equal -----
    LongValidator greaterEqThan(long lowerBound) throws ValidationException;

    LongValidator greaterEqThan(long lowerBound, String otherName) throws ValidationException;

    // ----- less than -----
    LongValidator lessThan(long upperBound) throws ValidationException;

    LongValidator lessThan(long upperBound, String otherName) throws ValidationException;

    // ----- less or equal -----
    LongValidator lessEqThan(long upperBound) throws ValidationException;

    LongValidator lessEqThan(long upperBound, String otherName) throws ValidationException;

    // ----- check for valid TCP/UDP port number -----
    LongValidator validPortNumber() throws ValidationException;
}
