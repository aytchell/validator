package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.ContainerValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
abstract class DisarmedContainerValidator<TYPE, VALIDATOR> implements ContainerValidator<TYPE, VALIDATOR> {

    protected abstract VALIDATOR getValidator();

    @Override
    public VALIDATOR notEmpty() {
        return getValidator();
    }

    @Override
    public VALIDATOR sizeAtLeast(int minNumberOfElements) {
        return getValidator();
    }

    @Override
    public VALIDATOR sizeAtMost(int maxNumberOfElements) {
        return getValidator();
    }

    @Override
    public VALIDATOR misses(TYPE key) {
        return getValidator();
    }

    @Override
    public VALIDATOR contains(TYPE key) {
        return getValidator();
    }
}
