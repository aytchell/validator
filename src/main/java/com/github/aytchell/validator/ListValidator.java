package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface ListValidator<T> extends CollectionValidator<T, ListValidator<T>> {
    ListValidator<T> eachCustomEntry(CustomValidator<T> customValidator) throws ValidationException;
}
