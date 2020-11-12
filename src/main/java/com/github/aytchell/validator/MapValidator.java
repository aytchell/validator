package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

public interface MapValidator<K, V> extends ContainerValidator<K, MapValidator<K, V>> {
    MapValidator<K, V> anyValueIsLongerThan(int maxLength) throws ValidationException;
}
