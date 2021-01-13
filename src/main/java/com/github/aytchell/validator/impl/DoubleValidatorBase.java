package com.github.aytchell.validator.impl;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.DoubleValidator;
import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
abstract class DoubleValidatorBase implements DoubleValidator {
    @Override
    public DoubleValidator greaterThan(double lowerBound) throws ValidationException {
        return greaterThan(lowerBound, null);
    }

    @Override
    public DoubleValidator greaterEqThan(double lowerBound) throws ValidationException {
        return greaterEqThan(lowerBound, null);
    }

    @Override
    public DoubleValidator lessThan(double upperBound) throws ValidationException {
        return lessThan(upperBound, null);
    }

    @Override
    public DoubleValidator lessEqThan(double upperBound) throws ValidationException {
        return lessEqThan(upperBound, null);
    }
}
