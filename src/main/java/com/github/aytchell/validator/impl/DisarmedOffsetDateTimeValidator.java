package com.github.aytchell.validator.impl;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.OffsetDateTimeValidator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DisarmedOffsetDateTimeValidator extends OffsetDateTimeValidatorBase {
    @Getter
    private static final OffsetDateTimeValidator INSTANCE = new DisarmedOffsetDateTimeValidator();

    @Override
    public OffsetDateTimeValidator isBefore(OffsetDateTime otherDateTime, String otherName) {
        return this;
    }

    @Override
    public OffsetDateTimeValidator isAfter(OffsetDateTime otherDateTime, String otherName) {
        return this;
    }
}
