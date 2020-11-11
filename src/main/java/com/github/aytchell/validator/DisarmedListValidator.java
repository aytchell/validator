package com.github.aytchell.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

// This class shall only be instantiated by Validator
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class DisarmedListValidator<T> extends DisarmedCollectionValidator<T, ListValidator<T>> implements ListValidator<T> {
    @Override
    protected ListValidator<T> getValidator() {
        return this;
    }
}
