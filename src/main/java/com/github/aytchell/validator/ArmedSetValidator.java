package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.Set;

public class ArmedSetValidator<E> implements SetValidator<E> {
    private final Set<E> value;
    private final String name;
    private final ArmedContainerValidator<E> containerValidator;

    // This class shall only be instantiated by Validator
    ArmedSetValidator(Set<E> value, String name) {
        this.value = value;
        this.name = name;
        this.containerValidator = new ArmedContainerValidator<>("Set", value, name);
    }

    @Override
    public SetValidator<E> isKeyContained(E key) throws ValidationException {
        if (value.contains(key)) {
            throw new ValidationException(String.format("Set '%s' must not contain '%s'", name, key.toString()));
        }
        return this;
    }

    @Override
    public SetValidator<E> isKeyMissing(E key) throws ValidationException {
        if (!value.contains(key)) {
            throw new ValidationException(String.format("Set '%s' must contain '%s'", name, key.toString()));
        }
        return this;
    }

    @Override
    public SetValidator<E> isEmpty() throws ValidationException {
        containerValidator.isEmpty();
        return this;
    }

    @Override
    public SetValidator<E> containsLessThan(int minNumberOfElements) throws ValidationException {
        containerValidator.containsLessThan(minNumberOfElements);
        return this;
    }

    @Override
    public SetValidator<E> containsMoreThan(int maxNumberOfElements) throws ValidationException {
        containerValidator.containsMoreThan(maxNumberOfElements);
        return this;
    }
}
