package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.StringValidator;
import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
class ArmedStringValidator implements StringValidator {
    private final String value;
    private final String name;

    @Override
    public StringValidator notEmpty() throws ValidationException {
        if (this.value.isEmpty()) {
            throw newExceptionWithNameAndValue()
                    .setExpectation("not empty");
        }
        return this;
    }

    @Override
    public StringValidator notBlank() throws ValidationException {
        if (this.value.isBlank()) {
            throw newExceptionWithNameAndValue()
                    .setExpectation("not blank");
        }
        return this;
    }

    @Override
    public StringValidator lengthAtMost(int maxLength) throws ValidationException {
        if (value.length() > maxLength) {
            throw new ValidationException()
                    .setActualValuesName("length of " + name)
                    .setActualValue(String.valueOf(value.length()))
                    .setExpectation("at most")
                    .setExpectedValue(String.valueOf(maxLength));
        }
        return this;
    }

    private ValidationException newExceptionWithNameAndValue() {
        return new ValidationException()
                .setActualValuesName(name)
                .setActualValue(value);
    }
}
