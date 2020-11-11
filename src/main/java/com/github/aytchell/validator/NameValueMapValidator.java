package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface NameValueMapValidator {
    NameValueMapValidator isMissing(String key) throws ValidationException;

    NameValueMapValidator anyValueIsLongerThan(int maxLength) throws ValidationException;
}

/*
    VALIDATOR isEmpty() throws ValidationException;

    VALIDATOR containsLessThan(int minNumberOfElements) throws ValidationException;

    VALIDATOR containsMoreThan(int maxNumberOfElements) throws ValidationException;

    VALIDATOR isContained(TYPE key) throws ValidationException;

    VALIDATOR isMissing(TYPE key) throws ValidationException;

    VALIDATOR isAnyNumericEntry(LongEntryValidator validator) throws ValidationException;

    VALIDATOR isAnyStringEntry(StringEntryValidator validator) throws ValidationException;

 */