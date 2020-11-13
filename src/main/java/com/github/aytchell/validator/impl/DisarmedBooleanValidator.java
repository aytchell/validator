package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.BooleanValidator;
import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class DisarmedBooleanValidator implements BooleanValidator {
    @Getter
    private static final BooleanValidator INSTANCE = new DisarmedBooleanValidator();

    @Override
    public BooleanValidator isTrue() {
        return this;
    }

    @Override
    public BooleanValidator isFalse() {
        return this;
    }

    @Override
    public BooleanValidator matches(Boolean otherValue) throws ValidationException {
        return this;
    }

    @Override
    public BooleanValidator matches(Boolean otherValue, String otherName) throws ValidationException {
        return this;
    }
}
