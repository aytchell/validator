package com.github.aytchell.validator.impl;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.ListValidator;
import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.BiPredicate;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class DisarmedListValidator<T> extends DisarmedCollectionValidator<T, ListValidator<T>> implements ListValidator<T> {
    @Override
    protected ListValidator<T> getValidator() {
        return this;
    }

    @Override
    public ListValidator<T> allEntriesAreUnique(BiPredicate<T, T> compare, String entryName,
                                                Function<T, String> toString) {
        return this;
    }

    @Override
    public ListValidator<T> allEntriesAreUnique() throws ValidationException {
        return this;
    }
}
