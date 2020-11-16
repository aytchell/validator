package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface CustomValidator<TYPE> {
    CustomValidator<TYPE> passes(CustomEntryValidator<TYPE> entryValidator) throws ValidationException;
}
