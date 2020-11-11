package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface ContainerValidator<TYPE, VALIDATOR> {
    VALIDATOR isEmpty() throws ValidationException;

    VALIDATOR containsLessThan(int minNumberOfElements) throws ValidationException;

    VALIDATOR containsMoreThan(int maxNumberOfElements) throws ValidationException;

    VALIDATOR isContained(TYPE key) throws ValidationException;

    VALIDATOR isMissing(TYPE key) throws ValidationException;

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
