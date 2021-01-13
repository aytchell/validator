package com.github.aytchell.validator;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.exceptions.ValidationException;

/**
 * Specialized validator for {@link java.util.List}
 * <p>
 * Each method of this class will check the value under test and if the check fails a {@link ValidationException} will
 * be thrown. Currently this class adds no methods to {@link CollectionValidator}.
 */
public interface ListValidator<T> extends CollectionValidator<T, ListValidator<T>> {
}
