package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

// This class shall only be instantiated by Validator
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class DisarmedMapValidator<K, V> extends DisarmedContainerValidator<K, MapValidator<K, V>>
        implements MapValidator<K, V> {
    @Override
    protected MapValidator<K, V> getValidator() {
        return this;
    }

    @Override
    public MapValidator<K, V> anyNumericValue(LongEntryValidator validator) throws ValidationException {
        return this;
    }

    @Override
    public MapValidator<K, V> anyStringValue(StringEntryValidator validator) throws ValidationException {
        return this;
    }
}
