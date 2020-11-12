package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

import java.util.Collection;

abstract class ArmedCollectionValidator<TYPE, VALIDATOR>
        extends ArmedContainerValidator<TYPE, VALIDATOR>
        implements CollectionValidator<TYPE, VALIDATOR> {
    ArmedCollectionValidator(String containerType, Collection<TYPE> value, String name) {
        super(containerType, value, name);
    }

    @Override
    public VALIDATOR anyNumericEntry(LongEntryValidator entryValidator) throws ValidationException {
        for (TYPE entry : getValue()) {
            if (entry instanceof Integer) {
                final Integer value = (Integer) entry;
                entryValidator.apply(
                        Validator.throwIf(Long.valueOf(value),
                                String.format("inside %s <%s>", getContainerType(), getName()))
                );
            } else if (entry instanceof Long) {
                entryValidator.apply(
                        Validator.throwIf((Long) entry,
                                String.format("inside %s <%s>", getContainerType(), getName()))
                );
            } else {
                throw new ClassCastException(String.format(
                        "Tried to validate entries in '%s' as numeric values (but are '%s')",
                        getName(), entry.getClass().getSimpleName()));
            }
        }
        return getValidator();
    }

    @Override
    public VALIDATOR anyStringEntry(StringEntryValidator entryValidator) throws ValidationException {
        for (TYPE entry : getValue()) {
            if (entry instanceof String) {
                entryValidator.apply(
                        Validator.throwIf((String) entry,
                                String.format("inside %s <%s>", getContainerType(), getName()))
                );
            } else {
                throw new ClassCastException(
                        String.format("Tried to validate entries in '%s' as Strings (but are '%s')",
                                getName(), entry.getClass().getSimpleName()));
            }
        }
        return getValidator();
    }
}
