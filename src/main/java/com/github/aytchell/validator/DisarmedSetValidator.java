package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

// This class shall only be instantiated by Validator
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class DisarmedSetValidator<E> implements SetValidator<E> {

    @Override
    public SetValidator<E> isKeyContained(Object key) {
        return this;
    }

    @Override
    public SetValidator<E> isKeyMissing(Object key) {
        return this;
    }

    @Override
    public SetValidator<E> isEmpty() {
        return this;
    }

    @Override
    public SetValidator<E> containsLessThan(int minNumberOfElements) {
        return this;
    }

    @Override
    public SetValidator<E> containsMoreThan(int maxNumberOfElements) {
        return this;
    }
}
