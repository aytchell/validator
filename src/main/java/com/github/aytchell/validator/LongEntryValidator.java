package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

@FunctionalInterface
public interface LongEntryValidator {
    LongValidator apply(NullableObjectValidator<Long, LongValidator> validator) throws ValidationException;
}
