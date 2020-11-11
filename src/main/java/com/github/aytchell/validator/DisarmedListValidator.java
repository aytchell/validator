package com.github.aytchell.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

// This class shall only be instantiated by Validator
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class DisarmedListValidator<E> implements ListValidator<E> {

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

    @Override
    public ListValidator<E> isContained(E key) {
        return this;
    }

    @Override
    public ListValidator<E> isMissing(E key) {
        return this;
    }

    @Override
    public ListValidator<E> isAnyNumericEntry(LongEntryValidator validator) {
        return this;
    }

    @Override
    public ListValidator<E> isAnyStringEntry(StringEntryValidator validator) {
        return this;
    }
}
