package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.CustomValidator;
import com.github.aytchell.validator.ListValidator;
import com.github.aytchell.validator.exceptions.ValidationException;

import java.util.List;

class ArmedListValidator<E> extends ArmedCollectionValidator<E, ListValidator<E>> implements ListValidator<E> {
    private final List<E> theList;

    ArmedListValidator(List<E> value, String name, String extraInfo) {
        super("List", value, name, extraInfo);
        theList = value;
    }

    @Override
    protected ArmedListValidator<E> getValidator() {
        return this;
    }

    @Override
    public ListValidator<E> eachCustomEntry(CustomValidator<E> customValidator) throws ValidationException {
        for (int i = 0; i < theList.size(); ++i) {
            try {
                customValidator.apply(theList.get(i));
            } catch (ValidationException exception) {
                final String entryName = exception.getActualValuesName();
                if (entryName != null) {
                    exception.setActualValuesName(String.format("%s[%d].%s", getName(), i, entryName));
                }
                throw exception;
            }
        }
        return this;
    }
}
