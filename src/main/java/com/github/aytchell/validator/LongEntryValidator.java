package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

@FunctionalInterface
public interface LongEntryValidator {
    void apply(NullableObjectValidator<Long, LongValidator> validator) throws ValidationException;
}
