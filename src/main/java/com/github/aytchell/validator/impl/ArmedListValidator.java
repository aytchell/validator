package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.ListValidator;

import java.util.List;

class ArmedListValidator<E> extends ArmedCollectionValidator<E, ListValidator<E>> implements ListValidator<E> {
    ArmedListValidator(List<E> value, String name, String extraInfo) {
        super("List", value, name, extraInfo);
    }

    @Override
    protected ArmedListValidator<E> getValidator() {
        return this;
    }
}
