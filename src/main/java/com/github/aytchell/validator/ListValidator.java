package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface ListValidator<E> {
    ListValidator<E> isEmpty() throws ValidationException;

    ListValidator<E> containsLessThan(int minNumberOfElements) throws ValidationException;

    ListValidator<E> containsMoreThan(int maxNumberOfElements) throws ValidationException;

    ListValidator<E> isContained(E key) throws ValidationException;

    ListValidator<E> isMissing(E key) throws ValidationException;

    ListValidator<E> isAnyNumericEntry(LongEntryValidator validator) throws ValidationException;

    ListValidator<E> isAnyStringEntry(StringEntryValidator validator) throws ValidationException;

    @FunctionalInterface
    interface LongEntryValidator {
        LongValidator apply(NullableObjectValidator<Long, LongValidator> validator) throws ValidationException;
    }

    @FunctionalInterface
    interface StringEntryValidator {
        StringValidator apply(NullableObjectValidator<String, StringValidator> validator) throws ValidationException;
    }
}
