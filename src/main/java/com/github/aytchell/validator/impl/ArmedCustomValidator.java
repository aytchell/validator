package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.CustomEntryValidator;
import com.github.aytchell.validator.CustomValidator;
import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
class ArmedCustomValidator<E> implements CustomValidator<E> {
    private final E value;
    private final String name;
    private final String extraInfo;

    @Override
    public CustomValidator<E> passes(CustomEntryValidator<E> entryValidator) throws ValidationException {
        try {
            entryValidator.apply(value);
        } catch (ValidationException exception) {
            final String entryName = exception.getActualValuesName();
            exception.setActualValuesName(compileNameOfElement(
                    Objects.requireNonNullElseGet(entryName, () -> "<" + exception.getActualValue() + ">")));
            if (exception.getValuesExtraInfo() == null && extraInfo != null) {
                exception.setValuesExtraInfo(compileExtraInfo());
            }
            throw exception;
        }
        return this;
    }

    private String compileNameOfElement(String entryName) {
        if (this.name != null) {
            return String.format("%s.%s", this.name, entryName);
        } else {
            return String.format("Object.%s", entryName);
        }
    }

    private String compileExtraInfo() {
        if (name != null) {
            return String.format("(about %s) %s", name, extraInfo);
        } else {
            return String.format("(about parent object) %s", extraInfo);
        }
    }
}
