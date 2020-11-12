package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

// This class shall only be instantiated by Validator
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class ArmedStringValidator implements StringValidator {
    private final String value;
    private final String name;

    @Override
    public StringValidator isEmpty() throws ValidationException {
        if (this.value.isEmpty()) {
            throw newExceptionWithNameAndValue()
                    .setExpectation("shall not be empty");
        }
        return this;
    }

    @Override
    public StringValidator isBlank() throws ValidationException {
        if (this.value.isBlank()) {
            throw newExceptionWithNameAndValue()
                    .setExpectation("shall not be blank");
        }
        return this;
    }

    @Override
    public StringValidator isLongerThan(int maxLength) throws ValidationException {
        if (value.length() > maxLength) {
            throw new ValidationException()
                    .setActualValuesName("length of " + name)
                    .setActualValue(String.valueOf(value.length()))
                    .setExpectation("shall not be longer than")
                    .setExpectedValue(String.valueOf(maxLength));
        }
        return this;
    }

    private ValidationException newExceptionWithNameAndValue() {
        return new ValidationException()
                .setActualValuesName(name)
                .setActualValue(value);
    }
}
