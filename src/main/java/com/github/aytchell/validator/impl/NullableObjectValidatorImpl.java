package com.github.aytchell.validator.impl;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.NullableObjectValidator;
import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
abstract class NullableObjectValidatorImpl<VALUE, VALIDATOR> implements NullableObjectValidator<VALUE, VALIDATOR> {
    private final VALUE value;
    private final String name;
    private final String extraInfo;
    private final VALIDATOR disarmedValidator;

    public NullableObjectValidator<VALUE, VALIDATOR> ifTrue(boolean condition) {
        if (condition) {
            return this;
        } else {
            return new DisarmedNullableObjectValidator<>(disarmedValidator);
        }
    }

    public NullableObjectValidator<VALUE, VALIDATOR> ifFalse(boolean condition) {
        return ifTrue(!condition);
    }

    public NullableObjectValidator<VALUE, VALIDATOR> ifGivenAndTrue(Boolean condition) {
        return ifTrue(condition != null && condition);
    }

    public NullableObjectValidator<VALUE, VALIDATOR> ifNotGivenOrFalse(Boolean condition) {
        return ifFalse(condition != null && condition);
    }

    @Override
    public VALIDATOR notNull() throws ValidationException {
        if (value == null) {
            throw new ValidationException()
                    .setActualValuesName(name)
                    .setValuesExtraInfo(extraInfo)
                    .setExpectation("is not null");
        }
        return createValidator(value, name, extraInfo);
    }

    @Override
    public VALIDATOR ifNotNull() {
        if (value != null) {
            return createValidator(value, name, extraInfo);
        } else {
            return disarmedValidator;
        }
    }

    protected abstract VALIDATOR createValidator(VALUE value, String name, String extraInfo);
}
