package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.SetValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class DisarmedSetValidator<T> extends DisarmedCollectionValidator<T, SetValidator<T>> implements SetValidator<T> {
    @Override
    protected SetValidator<T> getValidator() {
        return this;
    }
}