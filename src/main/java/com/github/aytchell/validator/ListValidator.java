package com.github.aytchell.validator;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.exceptions.ValidationException;

import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * Specialized validator for {@link java.util.List}
 * <p>
 * Each method of this class will check the value under test and if the check fails a {@link ValidationException} will
 * be thrown.
 */
public interface ListValidator<T> extends CollectionValidator<T, ListValidator<T>> {
    /**
     * Check whether all entries in the list are unique.
     * <p>
     * This method will compare all elements of the list by using a user-provided comparison operator.
     * If there is a single pair of equal elements (according to {@code compare}) this method will throw
     * a {@link ValidationException}. The exception's message will contain the given {@code entryName}
     * and a representation of the duplicate entry (created by using the given {@code toString} function).
     *
     * @param compare a predicate with two arguments which is used to compare all the possible pairs of entries
     * @param entryName a description what was compared when throwing an exception
     * @param toString a function which compiles a string representation of the duplicate entry
     * @return this validator so you can add more tests
     * @throws ValidationException thrown if there is at least one pair of equal entries according to
     *                              the given {@code compare} predicate
     */
    ListValidator<T> allEntriesAreUnique(BiPredicate<T, T> compare, String entryName, Function<T, String> toString)
            throws ValidationException;

    /**
     * Check whether all entries in the list are unique.
     * <p>
     * Convenience version of {@link #allEntriesAreUnique(BiPredicate, String, Function)} with {@code compare}
     * being {@code Object::equals}, {@code entryName} being {@code null} (so no name is shown) and
     * {@code toString} being {@Object::toString}.
     *
     * @return this validator so you can add more tests
     * @throws ValidationException thrown if there is at least one pair of equal entries according to
     *                              {@code Object::equals}
     */
    ListValidator<T> allEntriesAreUnique() throws ValidationException;
}
