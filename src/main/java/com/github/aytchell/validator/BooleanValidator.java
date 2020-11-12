package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface BooleanValidator {
    void isTrue() throws ValidationException;

    void isFalse() throws ValidationException;
}
