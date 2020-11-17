package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.DoubleValidator;
import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DisarmedDoubleValidator extends DoubleValidatorBase {
    @Getter
    private static final DoubleValidator INSTANCE = new DisarmedDoubleValidator();

    @Override
    public DoubleValidator greaterThan(double lowerBound, String otherName) {
        return this;
    }

    @Override
    public DoubleValidator greaterEqThan(double lowerBound, String otherName) throws ValidationException {
        return this;
    }

    @Override
    public DoubleValidator lessThan(double upperBound, String otherName) {
        return this;
    }

    @Override
    public DoubleValidator lessEqThan(double upperBound, String otherName) throws ValidationException {
        return this;
    }
}