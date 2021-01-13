package com.github.aytchell.validator;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.exceptions.ValidationException;

/**
 * Specialized validator for {@link java.util.Map}
 * <p>
 * Each method of this class will check the value under test and if the check fails a {@link ValidationException} will
 * be thrown.
 */
public interface MapValidator<K, V> extends ContainerValidator<MapValidator<K, V>> {
    /**
     * Check that a given key is contained in the Map under test
     *
     * @param key this key is expected to be contained in the Map
     * @return this validator so calls can be chained
     * @throws ValidationException in case the Map under test does <i>not</i> contain {@code key}
     */
    MapValidator<K, V> containsKey(K key) throws ValidationException;

    /**
     * Check that a given key is <i>not</i> contained in the Map under test
     *
     * @param key this key is expected to be absent from the Map
     * @return this validator so calls can be chained
     * @throws ValidationException in case the Map under test <i>does</i> contain {@code key}
     */
    MapValidator<K, V> containsNotKey(K key) throws ValidationException;

    /**
     * Check all values of the Map while treating them as numerical values
     * <p>
     * Unfortunately this feature hits the borders of Java generic's possibilities. There is no way to find the correct
     * validator for the type {@code V} of the values at compile-time. So there's a bit help needed from the user of this
     * library:
     * <ul>
     *     <li>if the Map under test contains numerical values (i.e. Short, Integer or Long) then one should use
     *     this method.</li>
     *     <li>if the Map contains strings, see {@link MapValidator#eachStringValue}</li>
     * </ul>
     * <p>
     * This method will visit all values of the Map and apply a given consumer to it (that should validate the
     * value).
     * <p>
     * Usage example:
     * <pre>
     Validator.expect(integerMap, "integerMap")
            .notNull()
            .eachNumericValue(
                    v -&gt; v.notNull().greaterEqThan(5));
     * </pre>
     *
     * @param validator a function similar to a Consumer which is applied to each value of the Map and
     *         throws if a value somehow fails the check
     * @return the MapValidator itself so calls can be chained
     * @throws ValidationException in case the given validator fails for one of the values
     * @throws ClassCastException in case the Map does not contain numerical values
     * @see LongValidator
     */
    MapValidator<K, V> eachNumericValue(LongEntryValidator validator) throws ValidationException;

    /**
     * Check all values of the Map while treating them as {@link String}s
     * <p>
     * Unfortunately this feature hits the borders of Java generic's possibilities. There is no way to find the correct
     * validator for the {@code TYPE} of the values at compile-time. So there's a bit help needed from the user of this
     * library:
     * <ul>
     *     <li>if the Map contains Strings then one should use this method
     *     <li>if the Map under test contains numerical values (i.e. Short, Integer or Long) then
     *     see {@link MapValidator#eachNumericValue}</li>
     * </ul>
     * <p>
     * This method will visit all values of the Map and apply a given consumer to it (that should validate the
     * value).
     * <p>
     * Usage example:
     * <pre>
     Validator.expect(filledMap, "filledMap")
            .ifNotNull()
            .eachStringValue(
                    v -&gt; v.notNull().lengthAtMost(20));
     * </pre>
     *
     * @param validator a function similar to a Consumer which is applied to each value of the Map and
     *         throws if a value somehow fails the check
     * @return the MapValidator itself so calls can be chained
     * @throws ValidationException in case the given validator fails for one of the values
     * @throws ClassCastException in case the Map does not contain Strings
     * @see StringValidator
     */
    MapValidator<K, V> eachStringValue(StringEntryValidator validator) throws ValidationException;
}
