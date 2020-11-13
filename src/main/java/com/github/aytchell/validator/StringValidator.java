package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface StringValidator {
    StringValidator notEmpty() throws ValidationException;

    StringValidator notBlank() throws ValidationException;

    StringValidator lengthAtMost(int maxLength) throws ValidationException;

    StringValidator validUrl() throws ValidationException;
}
