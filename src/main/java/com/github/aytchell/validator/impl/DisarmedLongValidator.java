package com.github.aytchell.validator.impl;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.LongValidator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.function.LongPredicate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DisarmedLongValidator extends LongValidatorBase {
    @Getter
    private static final LongValidator INSTANCE = new DisarmedLongValidator();

    @Override
    public LongValidator greaterThan(long lowerBound, String otherName) {
        return this;
    }

    @Override
    public LongValidator greaterEqThan(long lowerBound, String otherName) {
        return this;
    }

    @Override
    public LongValidator lessThan(long upperBound, String otherName) {
        return this;
    }

    @Override
    public LongValidator lessEqThan(long upperBound, String otherName) {
        return this;
    }

    @Override
    public LongValidator validPortNumber() {
        return this;
    }

    @Override
    public LongValidator passes(LongPredicate predicate, String expectation) {
        return this;
    }
}
