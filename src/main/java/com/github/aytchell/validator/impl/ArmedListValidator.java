package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.CustomValidator;
import com.github.aytchell.validator.ListValidator;
import com.github.aytchell.validator.exceptions.ValidationException;

import java.util.List;

class ArmedListValidator<E> extends ArmedCollectionValidator<E, ListValidator<E>> implements ListValidator<E> {
    ArmedListValidator(List<E> value, String name, String extraInfo) {
        super("List", value, name, extraInfo);
    }

    @Override
    protected ArmedListValidator<E> getValidator() {
        return this;
    }

    @Override
    protected String compileNameOfElement(int index, String entryName) {
        return String.format("%s[%d].%s", getName(), index, entryName);
    }
}
