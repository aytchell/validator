package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

import java.util.List;

// This class shall only be instantiated by Validator
class ArmedListValidator<E> implements ListValidator<E> {
    private final ArmedContainerValidator<E> containerValidator;

    ArmedListValidator(List<E> value, String name) {
        this.containerValidator = new ArmedContainerValidator<>("List", value, name);
    }

    @Override
    public ListValidator<E> isContained(E key) throws ValidationException {
        containerValidator.isContained(key);
        return this;
    }

    @Override
    public ListValidator<E> isMissing(E key) throws ValidationException {
        containerValidator.isMissing(key);
        return this;
    }

    @Override
    public ListValidator<E> isEmpty() throws ValidationException {
        containerValidator.isEmpty();
        return this;
    }

    @Override
    public ListValidator<E> containsLessThan(int minNumberOfElements) throws ValidationException {
        containerValidator.containsLessThan(minNumberOfElements);
        return this;
    }

    @Override
    public ListValidator<E> containsMoreThan(int maxNumberOfElements) throws ValidationException {
        containerValidator.containsMoreThan(maxNumberOfElements);
        return this;
    }
}
