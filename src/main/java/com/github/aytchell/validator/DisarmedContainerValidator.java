package com.github.aytchell.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
abstract class DisarmedContainerValidator<TYPE, VALIDATOR> implements ContainerValidator<TYPE, VALIDATOR> {

    protected abstract VALIDATOR getValidator();

    @Override
    public VALIDATOR isEmpty() {
        return getValidator();
    }

    @Override
    public VALIDATOR containsLessThan(int minNumberOfElements) {
        return getValidator();
    }

    @Override
    public VALIDATOR containsMoreThan(int maxNumberOfElements) {
        return getValidator();
    }

    @Override
    public VALIDATOR isContained(TYPE key) {
        return getValidator();
    }

    @Override
    public VALIDATOR isMissing(TYPE key) {
        return getValidator();
    }
}
