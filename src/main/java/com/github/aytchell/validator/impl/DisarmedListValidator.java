package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.ListValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class DisarmedListValidator<T> extends DisarmedCollectionValidator<T, ListValidator<T>> implements ListValidator<T> {
    @Override
    protected ListValidator<T> getValidator() {
        return this;
    }
}
