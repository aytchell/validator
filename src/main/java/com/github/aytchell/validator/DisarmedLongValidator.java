package com.github.aytchell.validator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DisarmedLongValidator extends LongValidatorBase {
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
