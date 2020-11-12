package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface MapValidator<K, V> extends ContainerValidator<K, MapValidator<K, V>> {
    MapValidator<K, V> eachNumericValue(LongEntryValidator validator) throws ValidationException;

    MapValidator<K, V> eachStringValue(StringEntryValidator validator) throws ValidationException;
}
