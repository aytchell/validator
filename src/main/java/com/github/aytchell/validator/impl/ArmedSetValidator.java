package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.SetValidator;

import java.util.Set;

class ArmedSetValidator<E> extends ArmedCollectionValidator<E, SetValidator<E>> implements SetValidator<E> {
    ArmedSetValidator(Set<E> value, String name) {
        super("Set", value, name);
    }

    @Override
    protected SetValidator<E> getValidator() {
        return this;
    }
}