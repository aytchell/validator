package com.github.aytchell.validator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// This class shall only be instantiated by Validator
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DisarmedLongValidator extends LongValidatorBase {
    @Getter
    public static final DisarmedLongValidator INSTANCE = new DisarmedLongValidator();

    @Override
    public LongValidator isLowerThan(long lowerBound) {
        return this;
    }

    @Override
    public LongValidator isGreaterThan(long upperBound) {
        return this;
    }

    @Override
    public LongValidator isNoValidPortNumber() {
        return this;
    }
}
