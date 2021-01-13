package com.github.aytchell.validator;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.exceptions.ValidationException;

@FunctionalInterface
public interface CustomValidator<TYPE> {
    CustomValidator<TYPE> passes(CustomEntryValidator<TYPE> entryValidator) throws ValidationException;
}
