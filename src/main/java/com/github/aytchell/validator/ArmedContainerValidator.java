package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;

// This class shall only be instantiated by Validator
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter(value = AccessLevel.PROTECTED)
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
}
