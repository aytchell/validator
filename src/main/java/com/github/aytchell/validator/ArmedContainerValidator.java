package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Collection;

// This class shall only be instantiated by Validator
@AllArgsConstructor(access = AccessLevel.PACKAGE)
abstract class ArmedContainerValidator<TYPE, VALIDATOR> implements ContainerValidator<TYPE, VALIDATOR> {
    private final String containerType;
    private final Collection<TYPE> value;
    private final String name;

    protected abstract VALIDATOR getValidator();

    @Override
    public VALIDATOR isEmpty() throws ValidationException {
        if (value.isEmpty()) {
            throw new ValidationException(String.format("%s '%s' must not be empty", containerType, name));
        }
        return getValidator();
    }

    public VALIDATOR isContained(TYPE key) throws ValidationException {
        if (value.contains(key)) {
            throw new ValidationException(String.format("%s '%s' must not contain '%s'",
                    containerType, name, key.toString()));
        }
        return getValidator();
    }

    public VALIDATOR isMissing(TYPE key) throws ValidationException {
        if (!value.contains(key)) {
            throw new ValidationException(String.format("%s '%s' must contain '%s'",
                    containerType, name, key.toString()));
        }
        return getValidator();
    }

    public VALIDATOR containsLessThan(int minNumberOfElements)
            throws ValidationException {
        if (value.size() < minNumberOfElements) {
            throw new ValidationException(String.format("%s '%s' (size: %d) must contain at least %d entries",
                    containerType, name, value.size(), minNumberOfElements));
        }
        return getValidator();
    }

    public VALIDATOR containsMoreThan(int maxNumberOfElements)
            throws ValidationException {
        if (value.size() > maxNumberOfElements) {
            throw new ValidationException(
                    String.format("%s '%s' (size: %d) must not contain more than %d entries",
                            containerType, name, value.size(), maxNumberOfElements));
        }
        return getValidator();
    }

    @Override
    public VALIDATOR isAnyNumericEntry(LongEntryValidator entryValidator) throws ValidationException {
        for (TYPE entry : value) {
            if (entry instanceof Integer) {
                final Integer value = (Integer) entry;
                entryValidator.apply(
                        Validator.throwIf(Long.valueOf(value), String.format("inside %s <%s>", containerType, name))
                );
            } else if (entry instanceof Long) {
                entryValidator.apply(
                        Validator.throwIf((Long) entry, String.format("inside %s <%s>", containerType, name))
                );
            } else {
                throw new ClassCastException(String.format(
                        "Tried to validate entries in '%s' as numeric values (but are '%s')",
                        name, entry.getClass().getSimpleName()));
            }
        }
        return getValidator();
    }

    @Override
    public VALIDATOR isAnyStringEntry(StringEntryValidator entryValidator) throws ValidationException {
        for (TYPE entry : value) {
            if (entry instanceof String) {
                entryValidator.apply(
                        Validator.throwIf((String) entry, String.format("inside %s <%s>", containerType, name))
                );
            } else {
                throw new ClassCastException(String.format(
                        "Tried to validate entries in '%s' as Strings (but are '%s')",
                        name, entry.getClass().getSimpleName()));
            }
        }
        return getValidator();
    }
}
