package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

import java.util.Map;

public class NullableNameValueMapValidator {
    private final Map<String, String> value;
    private final String name;

    public NullableNameValueMapValidator(Map<String, String> value, String name) {
        this.value = value;
        this.name = name;
    }

    public NameValueMapValidator isNull() throws ValidationException {
        Validator.throwIfNull(value, name);
        return new NameValueMapValidator(value, name);
    }
}
