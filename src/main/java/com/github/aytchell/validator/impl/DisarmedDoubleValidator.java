package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.DoubleValidator;
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
    public DoubleValidator lessThan(double upperBound, String otherName) {
        return this;
    }
}