package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.NullableObjectValidator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DisarmedNullableObjectValidator<VALUE, VALIDATOR> implements NullableObjectValidator<VALUE, VALIDATOR> {
    private final VALIDATOR disarmedValidator;

    @Override
    public NullableObjectValidator<VALUE, VALIDATOR> ifTrue(boolean condition) {
        return this;
    }

    @Override
    public NullableObjectValidator<VALUE, VALIDATOR> ifFalse(boolean condition) {
        return this;
    }

    @Override
    public NullableObjectValidator<VALUE, VALIDATOR> ifGivenAndTrue(Boolean condition) {
        return this;
    }

    @Override
    public NullableObjectValidator<VALUE, VALIDATOR> ifNotGivenOrFalse(Boolean condition) {
        return this;
    }

    @Override
    public VALIDATOR notNull() {
        return disarmedValidator;
    }

    @Override
    public VALIDATOR ifNotNull() {
        return disarmedValidator;
    }
}
