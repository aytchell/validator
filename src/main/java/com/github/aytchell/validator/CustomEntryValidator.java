package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

@FunctionalInterface
public interface CustomEntryValidator<T> {
    void apply(T value) throws ValidationException;
}
