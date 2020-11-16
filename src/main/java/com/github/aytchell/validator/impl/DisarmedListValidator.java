package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.CustomValidator;
import com.github.aytchell.validator.ListValidator;
import com.github.aytchell.validator.SetValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class DisarmedListValidator<T> extends DisarmedCollectionValidator<T, ListValidator<T>> implements ListValidator<T> {
    @Override
    protected ListValidator<T> getValidator() {
        return this;
    }
}
