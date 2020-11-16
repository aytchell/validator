package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface NullableObjectValidator<VALUE, VALIDATOR> {
    NullableObjectValidator<VALUE, VALIDATOR> ifTrue(boolean condition);

    NullableObjectValidator<VALUE, VALIDATOR> ifFalse(boolean condition);

    NullableObjectValidator<VALUE, VALIDATOR> ifGivenAndTrue(Boolean condition);

    NullableObjectValidator<VALUE, VALIDATOR> ifNotGivenOrFalse(Boolean condition);

    VALIDATOR notNull() throws ValidationException;

    VALIDATOR ifNotNull();
}
