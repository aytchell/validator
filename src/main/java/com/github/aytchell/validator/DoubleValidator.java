package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface DoubleValidator {
    DoubleValidator greaterThan(double lowerBound) throws ValidationException;

    DoubleValidator greaterThan(double lowerBound, String otherName) throws ValidationException;

    DoubleValidator greaterEqThan(double lowerBound) throws ValidationException;

    DoubleValidator greaterEqThan(double lowerBound, String otherName) throws ValidationException;

    DoubleValidator lessThan(double upperBound) throws ValidationException;

    DoubleValidator lessThan(double upperBound, String otherName) throws ValidationException;

    DoubleValidator lessEqThan(double upperBound) throws ValidationException;

    DoubleValidator lessEqThan(double upperBound, String otherName) throws ValidationException;
}
