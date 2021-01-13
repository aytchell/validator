package com.github.aytchell.validator;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.exceptions.ValidationException;

/**
 * Specialized validator for {@link Boolean}
 * <p>
 * Each method of this class will check the value under test and if the check fails a {@link ValidationException} will
 * be thrown.
 */
public interface BooleanValidator {
    /**
     * Check that the value under test is {@code true}
     *
     * @return this validator so you can add more tests
     * @throws ValidationException if the value under test is {@code false}
     */
    BooleanValidator isTrue() throws ValidationException;

    /**
     * Check that the value under test is {@code false}
     *
     * @return this validator so you can add more tests
     * @throws ValidationException if the value under test is {@code true}
     */
    BooleanValidator isFalse() throws ValidationException;

    /**
     * Check that the value under test equals the given {@code otherValue}
     *
     * @param otherValue the other boolean to be compared with
     * @return this validator so you can add more tests
     * @throws ValidationException if the value under test and the {@code otherValue} don't equal
     */
    BooleanValidator matches(Boolean otherValue) throws ValidationException;

    /**
     * Check that the value under test equals the given (named) {@code otherValue}
     *
     * @param otherValue the other boolean to be compared with
     * @param otherName a name for the given {@code otherValue} which will appear in the error message if the
     *         check fails
     * @return this validator so you can add more tests
     * @throws ValidationException if the value under test and the {@code otherValue} don't equal
     */
    BooleanValidator matches(Boolean otherValue, String otherName) throws ValidationException;
}
