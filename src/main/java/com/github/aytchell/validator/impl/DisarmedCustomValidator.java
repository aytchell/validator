package com.github.aytchell.validator.impl;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.CustomEntryValidator;
import com.github.aytchell.validator.CustomValidator;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DisarmedCustomValidator<E> implements CustomValidator<E> {
    @Override
    public CustomValidator<E> passes(CustomEntryValidator<E> entryValidator) {
        return this;
    }
}
