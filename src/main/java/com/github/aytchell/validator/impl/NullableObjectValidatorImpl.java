package com.github.aytchell.validator.impl;

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
