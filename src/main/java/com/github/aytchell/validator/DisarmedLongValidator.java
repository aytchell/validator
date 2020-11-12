package com.github.aytchell.validator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// This class shall only be instantiated by Validator
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DisarmedLongValidator extends LongValidatorBase {
    @Getter
    public static final LongValidator INSTANCE = new DisarmedLongValidator();

    @Override
    public LongValidator gtEqThan(long lowerBound) {
        return this;
    }

    @Override
    public LongValidator ltEqThan(long upperBound) {
        return this;
    }

    @Override
    public LongValidator validPortNumber() {
        return this;
    }
}
