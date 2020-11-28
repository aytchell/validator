package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface MapValidator<K, V> extends ContainerValidator<MapValidator<K, V>> {
    MapValidator<K, V> containsKey(K key) throws ValidationException;

    MapValidator<K, V> containsNotKey(K key) throws ValidationException;

    MapValidator<K, V> eachNumericValue(LongEntryValidator validator) throws ValidationException;

    MapValidator<K, V> eachStringValue(StringEntryValidator validator) throws ValidationException;
}
