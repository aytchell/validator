package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.LongValidator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DisarmedLongValidator extends LongValidatorBase {
    @Getter
    private static final LongValidator INSTANCE = new DisarmedLongValidator();

    @Override
    public LongValidator greaterThan(long lowerBound, String otherName) {
        return this;
    }

    @Override
    public LongValidator greaterEqThan(long lowerBound, String otherName) {
        return this;
    }

    @Override
    public LongValidator lessThan(long upperBound, String otherName) {
        return this;
    }

    @Override
    public LongValidator lessEqThan(long upperBound, String otherName) {
        return this;
    }

    @Override
    public LongValidator validPortNumber() {
        return this;
    }
}
