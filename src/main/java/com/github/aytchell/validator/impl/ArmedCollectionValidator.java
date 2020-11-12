package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.CollectionValidator;
import com.github.aytchell.validator.LongEntryValidator;
import com.github.aytchell.validator.StringEntryValidator;
import com.github.aytchell.validator.Validator;
import com.github.aytchell.validator.exceptions.ValidationException;

import java.util.Collection;

abstract class ArmedCollectionValidator<TYPE, VALIDATOR>
        extends ArmedContainerValidator<TYPE, VALIDATOR>
        implements CollectionValidator<TYPE, VALIDATOR> {
    ArmedCollectionValidator(String containerType, Collection<TYPE> value, String name) {
        super(containerType, value, name);
    }

    @Override
    public VALIDATOR eachNumericEntry(LongEntryValidator entryValidator) throws ValidationException {
        try {
            for (TYPE entry : getValue()) {
                if (entry instanceof Integer) {
                    final Integer value = (Integer) entry;
                    entryValidator.apply(Validator.expect(Long.valueOf(value)));
                } else if (entry instanceof Long) {
                    entryValidator.apply(Validator.expect((Long) entry));
                } else {
                    throw new ClassCastException(String.format(
                            "Tried to validate entries in %s '%s' as numeric values (but are '%s')",
                            getContainerType(), getName(), entry.getClass().getSimpleName()));
                }
            }
            return getValidator();
        } catch (ValidationException exception) {
            throw exception.setSurroundingContainerInfo(getContainerType(), getName(), "entry");
        }
    }

    @Override
    public VALIDATOR eachStringEntry(StringEntryValidator entryValidator) throws ValidationException {
        try {
            for (TYPE entry : getValue()) {
                if (entry instanceof String) {
                    entryValidator.apply(Validator.expect((String) entry));
                } else {
                    throw new ClassCastException(
                            String.format("Tried to validate entries in %s '%s' as Strings (but are '%s')",
                                    getContainerType(), getName(), entry.getClass().getSimpleName()));
                }
            }
            return getValidator();
        } catch (ValidationException exception) {
            throw exception.setSurroundingContainerInfo(getContainerType(), getName(), "entry");
        }
    }
}