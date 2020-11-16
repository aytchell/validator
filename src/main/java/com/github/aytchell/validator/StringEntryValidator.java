package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

@FunctionalInterface
public interface StringEntryValidator {
    void apply(NullableObjectValidator<String, StringValidator> validator) throws ValidationException;
}
