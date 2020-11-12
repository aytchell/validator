package com.github.aytchell.validator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// This class shall only be instantiated by Validator
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DisarmedStringValidator implements StringValidator {
    @Getter
    private static final StringValidator INSTANCE = new DisarmedStringValidator();

    @Override
    public StringValidator notEmpty() {
        return this;
    }

    @Override
    public StringValidator notBlank() {
        return this;
    }

    @Override
    public StringValidator lengthAtMost(int maxLength) {
        return this;
    }
}
