package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.StringValidator;
import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Override
    public StringValidator validUrl() throws ValidationException {
        return this;
    }
}
