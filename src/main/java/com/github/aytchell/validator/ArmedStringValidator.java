package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public class ArmedStringValidator implements StringValidator {
    private final String value;
    private final String name;

    public ArmedStringValidator(String value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public StringValidator isEmpty() throws ValidationException {
        if (this.value.isEmpty()) {
            throw new ValidationException(String.format("Parameter '%s' must not be empty", name));
        }
        return this;
    }

    @Override
    public StringValidator isBlank() throws ValidationException {
        if (this.value.isBlank()) {
            throw new ValidationException(String.format("Parameter '%s' must not be blank", name));
        }
        return this;
    }

    @Override
    public StringValidator isLongerThan(int maxLength) throws ValidationException {
        if (value.length() > maxLength) {
            throw new ValidationException(
                String.format("Parameter '%s' (given length: %d) must not be longer than %d",
                    name, value.length(), maxLength));
        }
        return this;
    }
}
