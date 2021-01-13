package com.github.aytchell.validator.impl;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.BooleanValidator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class DisarmedBooleanValidator implements BooleanValidator {
    @Getter
    private static final BooleanValidator INSTANCE = new DisarmedBooleanValidator();

    @Override
    public BooleanValidator isTrue() {
        return this;
    }

    @Override
    public BooleanValidator isFalse() {
        return this;
    }

    @Override
    public BooleanValidator matches(Boolean otherValue) {
        return this;
    }

    @Override
    public BooleanValidator matches(Boolean otherValue, String otherName) {
        return this;
    }
}
