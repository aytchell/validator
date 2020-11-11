package com.github.aytchell.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

// This class shall only be instantiated by Validator
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class DisarmedListValidator<E> implements ListValidator<E> {

    @Override
    public ListValidator<E> isContained(Object key) {
        return this;
    }

    @Override
    public ListValidator<E> isMissing(Object key) {
        return this;
    }

    @Override
    public ListValidator<E> isEmpty() {
        return this;
    }

    @Override
    public ListValidator<E> containsLessThan(int minNumberOfElements) {
        return this;
    }

    @Override
    public ListValidator<E> containsMoreThan(int maxNumberOfElements) {
        return this;
    }
}
