package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.BooleanValidator;
import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
class ArmedBooleanValidator implements BooleanValidator {
    private final Boolean value;
    private final String name;
    private final String extraInfo;

    @Override
    public void isTrue() throws ValidationException {
        if (!value) {
            throw new ValidationException()
                    .setActualValuesName(name)
                    .setActualValue(value)
                    .setValuesExtraInfo(extraInfo)
                    .setExpectation("is " + Boolean.TRUE.toString());
        }
    }

    @Override
    public void isFalse() throws ValidationException {
        if (value) {
            throw new ValidationException()
                    .setActualValuesName(name)
                    .setActualValue(value)
                    .setValuesExtraInfo(extraInfo)
                    .setExpectation("is " + Boolean.FALSE.toString());
        }
    }
}
