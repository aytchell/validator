package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface SetValidator<E> {
    SetValidator<E> isEmpty() throws ValidationException;

    SetValidator<E> containsLessThan(int minNumberOfElements) throws ValidationException;

    SetValidator<E> containsMoreThan(int maxNumberOfElements) throws ValidationException;

    SetValidator<E> isContained(E key) throws ValidationException;

    SetValidator<E> isMissing(E key) throws ValidationException;
}
