package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface NullableObjectValidator<VALUE, VALIDATOR> {
    VALIDATOR notNull() throws ValidationException;

    VALIDATOR ifNotNull();
}
