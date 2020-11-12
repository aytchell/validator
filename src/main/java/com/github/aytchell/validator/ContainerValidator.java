package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface ContainerValidator<TYPE, VALIDATOR> {
    VALIDATOR notEmpty() throws ValidationException;

    VALIDATOR sizeAtLeast(int minNumberOfElements) throws ValidationException;

    VALIDATOR sizeAtMost(int maxNumberOfElements) throws ValidationException;

    VALIDATOR containsNot(TYPE key) throws ValidationException;

    VALIDATOR contains(TYPE key) throws ValidationException;
}
