package com.github.aytchell.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

// This class shall only be instantiated by Validator
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class DisarmedSetValidator<T> extends DisarmedContainerValidator<T, SetValidator<T>> implements SetValidator<T> {
    @Override
    protected SetValidator<T> getValidator() {
        return this;
    }
}