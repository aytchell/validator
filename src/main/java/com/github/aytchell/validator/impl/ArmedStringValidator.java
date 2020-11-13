package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.StringValidator;
import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.net.MalformedURLException;
import java.net.URL;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
class ArmedStringValidator implements StringValidator {
    private final String value;
    private final String name;
    private final String extraInfo;

    @Override
    public StringValidator notEmpty() throws ValidationException {
        if (this.value.isEmpty()) {
            throw newExceptionWithBasics()
                    .setExpectation("is not empty");
        }
        return this;
    }

    @Override
    public StringValidator notBlank() throws ValidationException {
        if (this.value.isBlank()) {
            throw newExceptionWithBasics()
                    .setExpectation("is not blank");
        }
        return this;
    }

    @Override
    public StringValidator lengthAtMost(int maxLength) throws ValidationException {
        if (value.length() > maxLength) {
            throw new ValidationException()
                    .setActualValuesName("length of " + name)
                    .setActualValue(value.length())
                    .setValuesExtraInfo(extraInfo)
                    .setExpectation("is at most")
                    .setExpectedValue(maxLength);
        }
        return this;
    }

    @Override
    public StringValidator validUrl() throws ValidationException {
        try {
            new URL(value);
            return this;
        } catch (MalformedURLException e) {
            throw newExceptionWithBasics()
                    .setExpectation("is valid URL");
        }
    }

    private ValidationException newExceptionWithBasics() {
        return new ValidationException()
                .setActualValuesName(name)
                .setActualValue(value)
                .setValuesExtraInfo(extraInfo);
    }
}
