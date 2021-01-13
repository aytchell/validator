package com.github.aytchell.validator.impl;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

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
