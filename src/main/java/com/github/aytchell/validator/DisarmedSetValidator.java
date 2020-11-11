package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class DisarmedSetValidator<E> implements SetValidator<E> {

    @Override
    public SetValidator<E> isKeyContained(Object key) throws ValidationException {
        return this;
    }

    @Override
    public SetValidator<E> isKeyMissing(Object key) throws ValidationException {
        return this;
    }

    @Override
    public SetValidator<E> isEmpty() throws ValidationException {
        return this;
    }

    @Override
    public SetValidator<E> containsLessThan(int minNumberOfElements) throws ValidationException {
        return this;
    }

    @Override
    public SetValidator<E> containsMoreThan(int maxNumberOfElements) throws ValidationException {
        return this;
    }
}
