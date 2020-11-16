package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface CollectionValidator<TYPE, VALIDATOR> extends ContainerValidator<TYPE, VALIDATOR> {
    VALIDATOR eachNumericEntry(LongEntryValidator validator) throws ValidationException;

    VALIDATOR eachStringEntry(StringEntryValidator validator) throws ValidationException;

    VALIDATOR eachCustomEntry(CustomEntryValidator<TYPE> entryValidator) throws ValidationException;
}
