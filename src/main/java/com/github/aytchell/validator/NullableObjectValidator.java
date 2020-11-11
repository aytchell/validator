package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public abstract class NullableObjectValidator<VALUE, VALIDATOR> {
    private final VALUE value;
    private final String name;
    private final VALIDATOR disarmedValidator;

    public VALIDATOR isNull() throws ValidationException {
        Validator.throwIfNull(value, name);
        return createValidator(value, name);
    }

    public VALIDATOR isNotNullAnd() {
        if (value != null) {
            return createValidator(value, name);
        } else {
            return disarmedValidator;
        }
    }

    protected abstract VALIDATOR createValidator(VALUE value, String name);
}
