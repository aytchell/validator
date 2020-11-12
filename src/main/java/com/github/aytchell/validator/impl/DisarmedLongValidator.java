package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.LongValidator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DisarmedLongValidator extends LongValidatorBase {
    @Getter
    public static final LongValidator INSTANCE = new DisarmedLongValidator();

    @Override
    public LongValidator greaterEqThan(long lowerBound) {
        return this;
    }

    @Override
    public LongValidator lessEqThan(long upperBound) {
        return this;
    }

    @Override
    public LongValidator validPortNumber() {
        return this;
    }
}
