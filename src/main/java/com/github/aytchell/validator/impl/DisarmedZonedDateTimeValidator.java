package com.github.aytchell.validator.impl;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.ZonedDateTimeValidator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DisarmedZonedDateTimeValidator extends ZonedDateTimeValidatorBase {
    @Getter
    private static final ZonedDateTimeValidator INSTANCE = new DisarmedZonedDateTimeValidator();

    @Override
    public ZonedDateTimeValidator isBefore(ZonedDateTime otherDateTime, String otherName) {
        return this;
    }

    @Override
    public ZonedDateTimeValidator isAfter(ZonedDateTime otherDateTime, String otherName) {
        return this;
    }
}
