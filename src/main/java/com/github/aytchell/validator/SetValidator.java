package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface SetValidator<E> {
    SetValidator<E> isEmpty() throws ValidationException;

    SetValidator<E> containsLessThan(int minNumberOfElemens) throws ValidationException;

    SetValidator<E> containsMoreThan(int maxNumberOfElemens) throws ValidationException;

    SetValidator<E> isKeyContained(E key) throws ValidationException;

    SetValidator<E> isKeyMissing(E key) throws ValidationException;
}
