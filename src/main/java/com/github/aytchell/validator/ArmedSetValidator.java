package com.github.aytchell.validator;

import java.util.Set;

// This class shall only be instantiated by Validator
class ArmedSetValidator<E> extends ArmedContainerValidator<E, SetValidator<E>> implements SetValidator<E> {
    ArmedSetValidator(Set<E> value, String name) {
        super("Set", value, name);
    }

    @Override
    protected SetValidator<E> getValidator() {
        return this;
    }
}
