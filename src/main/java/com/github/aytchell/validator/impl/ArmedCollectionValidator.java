package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.CollectionValidator;
import com.github.aytchell.validator.CustomEntryValidator;
import com.github.aytchell.validator.LongEntryValidator;
import com.github.aytchell.validator.StringEntryValidator;
import com.github.aytchell.validator.Validator;
import com.github.aytchell.validator.exceptions.ValidationException;

import java.util.Collection;

abstract class ArmedCollectionValidator<TYPE, VALIDATOR>
        extends ArmedContainerValidator<TYPE, VALIDATOR>
        implements CollectionValidator<TYPE, VALIDATOR> {
    ArmedCollectionValidator(String containerType, Collection<TYPE> value, String name, String extraInfo) {
        super(containerType, value, name, extraInfo);
    }

    @Override
    public VALIDATOR contains(TYPE key) throws ValidationException {
        if (!getValue().contains(key)) {
            throw newExceptionWithBasics()
                    .setExpectation("contains")
                    .setExpectedValue(key);
        }
        return getValidator();
    }

    @Override
    public VALIDATOR containsNot(TYPE key) throws ValidationException {
        if (getValue().contains(key)) {
            throw newExceptionWithBasics()
                    .setExpectation("contains not")
                    .setExpectedValue(key);
        }
        return getValidator();
    }

    @Override
    public VALIDATOR eachNumericEntry(LongEntryValidator entryValidator) throws ValidationException {
        return iterate(entry -> {
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
        });
    }

    @Override
    public VALIDATOR eachStringEntry(StringEntryValidator entryValidator) throws ValidationException {
        return iterate(entry -> {
            if (entry instanceof String) {
                entryValidator.apply(Validator.expect((String) entry));
            } else {
                throw new ClassCastException(
                        String.format("Tried to validate entries in %s '%s' as Strings (but are '%s')",
                                getContainerType(), getName(), entry.getClass().getSimpleName()));
            }
        });
    }

    @Override
    public VALIDATOR eachCustomEntry(CustomEntryValidator<TYPE> validator) throws ValidationException {
        return iterate(validator::apply);
    }

    private VALIDATOR iterate(EntryValidator<TYPE> validator) throws ValidationException {
        int index = 0;
        for (TYPE entry : getValue()) {
            try {
                validator.apply(entry);
                ++index;
            } catch (ValidationException exception) {
                final String entryName = exception.getActualValuesName();
                if (entryName != null) {
                    exception.setActualValuesName(compileNameOfElement(index, entryName));
                } else {
                    exception.setActualValuesName(compileNameOfElement(index,
                            "<" + String.valueOf(entry) + ">"));
                }
                throw exception;
            }
        }
        return getValidator();
    }

    abstract protected String compileNameOfElement(int index, String entryName);

    @FunctionalInterface
    private interface EntryValidator<TYPE> {
        void apply(TYPE entry) throws ValidationException;
    }
}
