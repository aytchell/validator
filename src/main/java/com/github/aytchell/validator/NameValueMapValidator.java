package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface NameValueMapValidator {
    NameValueMapValidator isKeyMissing(String key) throws ValidationException;

    NameValueMapValidator anyValueIsLongerThan(int maxLength) throws ValidationException;
}
