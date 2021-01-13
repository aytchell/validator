package com.github.aytchell.validator;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.exceptions.ValidationException;

/**
 * Base for all validators of classes which behaves "container-like" (List, Set, Map)
 *
 * @param <VALIDATOR> the type of the validator specific for the actual container under test
 */
public interface ContainerValidator<VALIDATOR> {
    /**
     * Checks that the given container under test is not empty
     *
     * @return the same validator so calls can be chained
     * @throws ValidationException in case the container under test is empty
     */
    VALIDATOR notEmpty() throws ValidationException;

    /**
     * Check that the container under test has a given minimum size
     *
     * @param minNumberOfElements the minimum expected size of the container under test
     * @return the same validator so calls can be chained
     * @throws ValidationException in case the size of the container is less than the given minimum
     */
    VALIDATOR sizeAtLeast(int minNumberOfElements) throws ValidationException;

    /**
     * Check that the container under test has a given maximum size
     *
     * @param maxNumberOfElements the maximum expected size of the container under test
     * @return the same validator so calls can be chained
     * @throws ValidationException in case the size of the container is greater than the given maximum
     */
    VALIDATOR sizeAtMost(int maxNumberOfElements) throws ValidationException;
}
