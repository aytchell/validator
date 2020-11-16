package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.SetValidator;

import java.util.Set;

class ArmedSetValidator<E> extends ArmedCollectionValidator<E, SetValidator<E>> implements SetValidator<E> {
    ArmedSetValidator(Set<E> value, String name, String extraInfo) {
        super("Set", value, name, extraInfo);
    }

    @Override
    protected SetValidator<E> getValidator() {
        return this;
    }

    @Override
    protected String compileNameOfElement(int index, String entryName) {
        final String setName = getName();
        if (setName != null) {
            return String.format("%s.%s", setName, entryName);
        } else {
            return String.format("Set.%s", entryName);
        }
    }
}
