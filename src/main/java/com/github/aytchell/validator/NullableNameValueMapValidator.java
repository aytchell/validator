package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Map;

// This class shall only be instantiated by Validator
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class NullableNameValueMapValidator {
    private final Map<String, String> value;
    private final String name;

    public NameValueMapValidator isNull() throws ValidationException {
        Validator.throwIfNull(value, name);
        return new NameValueMapValidator(value, name);
    }
}
