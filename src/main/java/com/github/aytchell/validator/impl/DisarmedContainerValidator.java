package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.ContainerValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
abstract class DisarmedContainerValidator<TYPE, VALIDATOR> implements ContainerValidator<VALIDATOR> {

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
}
