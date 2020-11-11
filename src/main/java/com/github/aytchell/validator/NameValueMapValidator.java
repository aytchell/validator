package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

import java.util.Map;

public class NameValueMapValidator {
    private final Map<String, String> value;
    private final String name;

    NameValueMapValidator(Map<String, String> value, String name) {
        this.value = value;
        this.name = name;
    }

    public NameValueMapValidator isKeyMissing(String key) throws ValidationException {
        if (!value.containsKey(key)) {
            throw new ValidationException(
                String.format("Parameter '%s' must contain '%s'", name, key));
        }
        return this;
    }

    public NameValueMapValidator anyValueIsLongerThan(int maxLength) throws ValidationException {
        for (Map.Entry<String, String> entry : value.entrySet()) {
            Validator.throwIf(entry.getValue(), entry.getKey()).isNull().isLongerThan(255);
        }
        return this;
    }
}
