package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface ListValidator<E> {
    ListValidator<E> isEmpty() throws ValidationException;

    ListValidator<E> containsLessThan(int minNumberOfElements) throws ValidationException;

    ListValidator<E> containsMoreThan(int maxNumberOfElements) throws ValidationException;

    ListValidator<E> isContained(E key) throws ValidationException;

    ListValidator<E> isMissing(E key) throws ValidationException;
}
