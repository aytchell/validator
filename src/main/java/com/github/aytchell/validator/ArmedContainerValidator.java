package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Collection;

// This class shall only be instantiated by Validator
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class ArmedContainerValidator<E> {
    private final String containerType;
    private final Collection<E> value;
    private final String name;

    void isEmpty() throws ValidationException {
        if (value.isEmpty()) {
            throw new ValidationException(String.format("%s '%s' must not be empty", containerType, name));
        }
    }

    void isContained(E key) throws ValidationException {
        if (value.contains(key)) {
            throw new ValidationException(String.format("%s '%s' must not contain '%s'",
                    containerType, name, key.toString()));
        }
    }

    void isMissing(E key) throws ValidationException {
        if (!value.contains(key)) {
            throw new ValidationException(String.format("%s '%s' must contain '%s'",
                    containerType, name, key.toString()));
        }
    }

    void containsLessThan(int minNumberOfElements)
            throws ValidationException {
        if (value.size() < minNumberOfElements) {
            throw new ValidationException(String.format("%s '%s' (size: %d) must contain at least %d entries",
                    containerType, name, value.size(), minNumberOfElements));
        }
    }

    void containsMoreThan(int maxNumberOfElements)
            throws ValidationException {
        if (value.size() > maxNumberOfElements) {
            throw new ValidationException(
                    String.format("%s '%s' (size: %d) must not contain more than %d entries",
                            containerType, name, value.size(), maxNumberOfElements));
        }
    }

    void isAnyNumericEntry(ListValidator.LongEntryValidator validator) throws ValidationException {
        for (E entry : value) {
            if (entry instanceof Integer) {
                final Integer value = (Integer)entry;
                validator.apply(
                        Validator.throwIf(Long.valueOf(value), String.format("inside %s <%s>", containerType, name))
                );
            } else if (entry instanceof Long) {
                validator.apply(
                        Validator.throwIf((Long)entry, String.format("inside %s <%s>", containerType, name))
                );
            } else {
                throw new ClassCastException(String.format(
                        "Tried to validate entries in '%s' as numeric values (but are '%s')",
                        name, entry.getClass().getSimpleName()));
            }
        }
    }

    void isAnyStringEntry(ListValidator.StringEntryValidator validator) throws ValidationException {
        for (E entry : value) {
            if (entry instanceof String) {
                validator.apply(
                        Validator.throwIf((String) entry, String.format("inside %s <%s>", containerType, name))
                );
            } else {
                throw new ClassCastException(String.format(
                        "Tried to validate entries in '%s' as Strings (but are '%s')",
                        name, entry.getClass().getSimpleName()));
            }
        }
    }
}
