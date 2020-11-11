package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Map;

// This class shall only be instantiated by Validator
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class NameValueMapValidator {
    private final Map<String, String> value;
    private final String name;

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
