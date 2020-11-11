package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface CollectionValidator<TYPE, VALIDATOR> extends ContainerValidator<TYPE, VALIDATOR> {
    VALIDATOR isAnyNumericEntry(LongEntryValidator validator) throws ValidationException;

    VALIDATOR isAnyStringEntry(StringEntryValidator validator) throws ValidationException;

    @FunctionalInterface
    interface LongEntryValidator {
        LongValidator apply(NullableObjectValidator<Long, LongValidator> validator) throws ValidationException;
    }

    @FunctionalInterface
    interface StringEntryValidator {
        StringValidator apply(NullableObjectValidator<String, StringValidator> validator) throws ValidationException;
    }
}
