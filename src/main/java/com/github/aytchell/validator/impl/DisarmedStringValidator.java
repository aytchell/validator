package com.github.aytchell.validator.impl;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.StringValidator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DisarmedStringValidator implements StringValidator {
    @Getter
    private static final StringValidator INSTANCE = new DisarmedStringValidator();

    @Override
    public StringValidator notEmpty() {
        return this;
    }

    @Override
    public StringValidator notBlank() {
        return this;
    }

    @Override
    public StringValidator lengthAtMost(int maxLength) {
        return this;
    }

    @Override
    public StringValidator bytesAtMost(int maxBytes, Encoding encoding) {
        return this;
    }

    @Override
    public StringValidator codePointsAtMost(int maxCodepoints) {
        return this;
    }

    @Override
    public StringValidator validUrl() {
        return this;
    }

    @Override
    public StringValidator validUuid() {
        return this;
    }

    @Override
    public StringValidator matches(Pattern pattern) {
        return this;
    }

    @Override
    public StringValidator matches(Pattern pattern, String regexName) {
        return this;
    }

    @Override
    public StringValidator passes(Predicate<String> predicate, String expectation) {
        return this;
    }
}
