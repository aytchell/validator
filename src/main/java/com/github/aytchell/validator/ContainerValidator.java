package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface ContainerValidator<VALIDATOR> {
    VALIDATOR notEmpty() throws ValidationException;

    VALIDATOR sizeAtLeast(int minNumberOfElements) throws ValidationException;

    VALIDATOR sizeAtMost(int maxNumberOfElements) throws ValidationException;
}
