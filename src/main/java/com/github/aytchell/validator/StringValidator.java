package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface StringValidator {
    StringValidator isEmpty() throws ValidationException;

    StringValidator isBlank() throws ValidationException;

    StringValidator isLongerThan(int maxLength) throws ValidationException;
}
