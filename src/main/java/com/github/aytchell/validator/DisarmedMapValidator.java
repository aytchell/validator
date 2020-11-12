package com.github.aytchell.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

// This class shall only be instantiated by Validator
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class DisarmedMapValidator<K, V> extends DisarmedContainerValidator<K, MapValidator<K, V>>
        implements MapValidator<K, V> {
    @Override
    public MapValidator<K, V> anyValueIsLongerThan(int maxLength) {
        return this;
    }

    @Override
    protected MapValidator<K, V> getValidator() {
        return this;
    }
}
