package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface BooleanValidator {
    BooleanValidator isTrue() throws ValidationException;

    BooleanValidator isFalse() throws ValidationException;

    BooleanValidator matches(Boolean otherValue) throws ValidationException;

    BooleanValidator matches(Boolean otherValue, String otherName) throws ValidationException;
}
