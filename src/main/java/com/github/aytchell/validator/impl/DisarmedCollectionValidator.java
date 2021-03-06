package com.github.aytchell.validator.impl;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.CollectionValidator;
import com.github.aytchell.validator.CustomEntryValidator;
import com.github.aytchell.validator.LongEntryValidator;
import com.github.aytchell.validator.StringEntryValidator;

abstract class DisarmedCollectionValidator<TYPE, VALIDATOR>
        extends DisarmedContainerValidator<TYPE, VALIDATOR>
        implements CollectionValidator<TYPE, VALIDATOR> {

    @Override
    public VALIDATOR contains(TYPE key) {
        return getValidator();
    }

    @Override
    public VALIDATOR containsNot(TYPE key) {
        return getValidator();
    }

    @Override
    public VALIDATOR eachNumericEntry(LongEntryValidator entryValidator) {
        return getValidator();
    }

    @Override
    public VALIDATOR eachStringEntry(StringEntryValidator entryValidator) {
        return getValidator();
    }

    @Override
    public VALIDATOR eachCustomEntry(CustomEntryValidator<TYPE> entryValidator) {
        return getValidator();
    }
}
