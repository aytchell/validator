package com.github.aytchell.validator;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.exceptions.ValidationException;

/**
 * Specialized validator for {@link Double}
 * <p>
 * Each method of this class will check the value under test and if the check fails a {@link ValidationException} will
 * be thrown.
 */
public interface DoubleValidator {
    /**
     * Check that the value under test is greater than a given value
     *
     * @param lowerBound the 'other value' which is expected to be less than the value under test
     * @return this validator so you can add more tests
     * @throws ValidationException if the value under test is less or equal to the given value
     */
    DoubleValidator greaterThan(double lowerBound) throws ValidationException;

    /**
     * Check that the value under test is greater than a given (named) value
     *
     * @param lowerBound the 'other value' which is expected to be less than the value under test
     * @param otherName the name of the given value. This name will appear in the error message.
     * @return this validator so you can add more tests
     * @throws ValidationException if the value under test is less or equal to the given value
     */
    DoubleValidator greaterThan(double lowerBound, String otherName) throws ValidationException;

    /**
     * Check that the value under test is greater or equal to a given value
     * <p>
     * Note that this method compares doubles for equality but does not take an epsilon. So {@code lowerBound} should be
     * an integer number to prevent rounding issues.
     *
     * @param lowerBound the 'other value' which is expected to be less or equal to the value under test
     * @return this validator so you can add more tests
     * @throws ValidationException if the value under test is less than the given value
     */
    DoubleValidator greaterEqThan(double lowerBound) throws ValidationException;

    /**
     * Check that the value under test is greater or equal to a given (named) value
     * <p>
     * Note that this method compares doubles for equality but does not take an epsilon. So {@code lowerBound} should be
     * an integer number to prevent rounding issues.
     *
     * @param lowerBound the 'other value' which is expected to be less or equal to the value under test
     * @param otherName the name of the given value. This name will appear in the error message.
     * @return this validator so you can add more tests
     * @throws ValidationException if the value under test is less than the given value
     */
    DoubleValidator greaterEqThan(double lowerBound, String otherName) throws ValidationException;

    /**
     * Check that the value under test is less than a given value
     *
     * @param upperBound the 'other value' which is expected to be greater than the value under test
     * @return this validator so you can add more tests
     * @throws ValidationException if the value under test is greater or equal to the given value
     */
    DoubleValidator lessThan(double upperBound) throws ValidationException;

    /**
     * Check that the value under test is greater than a given (named) value
     *
     * @param upperBound the 'other value' which is expected to be greater than the value under test
     * @param otherName the name of the given value. This name will appear in the error message.
     * @return this validator so you can add more tests
     * @throws ValidationException if the value under test is greater or equal to the given value
     */
    DoubleValidator lessThan(double upperBound, String otherName) throws ValidationException;

    /**
     * Check that the value under test is less or equal to a given value
     * <p>
     * Note that this method compares doubles for equality but does not take an epsilon. So {@code upperBound} should be
     * an integer number to prevent rounding issues.
     *
     * @param upperBound the 'other value' which is expected to be greater or equal to the value under test
     * @return this validator so you can add more tests
     * @throws ValidationException if the value under test is greater than the given value
     */
    DoubleValidator lessEqThan(double upperBound) throws ValidationException;

    /**
     * Check that the value under test is less or equal to a given (named) value
     * <p>
     * Note that this method compares doubles for equality but does not take an epsilon. So {@code upperBound} should be
     * an integer number to prevent rounding issues.
     *
     * @param upperBound the 'other value' which is expected to be greater or equal to the value under test
     * @param otherName the name of the given value. This name will appear in the error message.
     * @return this validator so you can add more tests
     * @throws ValidationException if the value under test is greater than the given value
     */
    DoubleValidator lessEqThan(double upperBound, String otherName) throws ValidationException;
}
