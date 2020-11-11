package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

import java.util.Set;

// This class shall only be instantiated by Validator
class ArmedSetValidator<E> implements SetValidator<E> {
    private final ArmedContainerValidator<E> containerValidator;

    ArmedSetValidator(Set<E> value, String name) {
        this.containerValidator = new ArmedContainerValidator<>("Set", value, name);
    }

    @Override
    public SetValidator<E> isContained(E key) throws ValidationException {
        containerValidator.isContained(key);
        return this;
    }

    @Override
    public SetValidator<E> isMissing(E key) throws ValidationException {
        containerValidator.isMissing(key);
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
