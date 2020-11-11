package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

import java.util.List;

// This class shall only be instantiated by Validator
class ArmedListValidator<E> extends ArmedContainerValidator<E, ListValidator<E>> implements ListValidator<E> {
    ArmedListValidator(List<E> value, String name) {
        super("List", value, name);
    }

    @Override
    protected ArmedListValidator<E> getValidator() {
        return this;
    }
}
