package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

// This class shall only be derived by package internal classes
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public abstract class NullableObjectValidator<VALUE, VALIDATOR> {
    private final VALUE value;
    private final String name;
    private final VALIDATOR disarmedValidator;

    public VALIDATOR notNull() throws ValidationException {
        if (value == null) {
            throw new ValidationException()
                    .setActualValuesName(name)
                    .setExpectation("not null");
        }
        return createValidator(value, name);
    }

    public VALIDATOR ifNotNull() {
        if (value != null) {
            return createValidator(value, name);
        } else {
            return disarmedValidator;
        }
    }

    protected abstract VALIDATOR createValidator(VALUE value, String name);
}
