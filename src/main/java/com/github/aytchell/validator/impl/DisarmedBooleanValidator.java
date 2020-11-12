package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.BooleanValidator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class DisarmedBooleanValidator implements BooleanValidator {
    @Getter
    private static final BooleanValidator INSTANCE = new DisarmedBooleanValidator();

    @Override
    public void isTrue() {
    }

    @Override
    public void isFalse() {
    }
}
