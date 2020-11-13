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
    public BooleanValidator isTrue() throws ValidationException {
        if (!value) {
            throw new ValidationException()
                    .setActualValuesName(name)
                    .setActualValue(value)
                    .setValuesExtraInfo(extraInfo)
                    .setExpectation("is " + Boolean.TRUE.toString());
        }
        return this;
    }

    @Override
    public BooleanValidator isFalse() throws ValidationException {
        if (value) {
            throw new ValidationException()
                    .setActualValuesName(name)
                    .setActualValue(value)
                    .setValuesExtraInfo(extraInfo)
                    .setExpectation("is " + Boolean.FALSE.toString());
        }
        return this;
    }

    @Override
    public BooleanValidator matches(Boolean otherValue) throws ValidationException {
        return matches(otherValue, null);
    }

    @Override
    public BooleanValidator matches(Boolean otherValue, String otherName) throws ValidationException {
        if (value != otherValue) {
            throw new ValidationException()
                    .setActualValuesName(name)
                    .setActualValue(value)
                    .setValuesExtraInfo(extraInfo)
                    .setExpectation("equals")
                    .setExpectedValue(otherValue)
                    .setExpectedValuesName(otherName);
        }
        return this;
    }
}
