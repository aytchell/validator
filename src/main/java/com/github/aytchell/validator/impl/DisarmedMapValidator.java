package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.LongEntryValidator;
import com.github.aytchell.validator.MapValidator;
import com.github.aytchell.validator.StringEntryValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class DisarmedMapValidator<K, V> extends DisarmedContainerValidator<K, MapValidator<K, V>>
        implements MapValidator<K, V> {
    @Override
    protected MapValidator<K, V> getValidator() {
        return this;
    }

    @Override
    public MapValidator<K, V> containsKey(K key) {
        return this;
    }

    @Override
    public MapValidator<K, V> containsNotKey(K key) {
        return this;
    }

    @Override
    public MapValidator<K, V> eachNumericValue(LongEntryValidator validator) {
        return this;
    }

    @Override
    public MapValidator<K, V> eachStringValue(StringEntryValidator validator) {
        return this;
    }
}
