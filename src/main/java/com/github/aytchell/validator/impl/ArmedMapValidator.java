package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.LongEntryValidator;
import com.github.aytchell.validator.MapValidator;
import com.github.aytchell.validator.StringEntryValidator;
import com.github.aytchell.validator.Validator;
import com.github.aytchell.validator.exceptions.ValidationException;

import java.util.Map;

class ArmedMapValidator<K, V> extends ArmedContainerValidator<K, MapValidator<K, V>> implements MapValidator<K, V> {
    private final Map<K, V> theMap;

    ArmedMapValidator(Map<K, V> value, String name, String extraInfo) {
        super("Map", new MapCollectionWrapper<>(value), name, extraInfo);
        this.theMap = value;
    }

    @Override
    protected MapValidator<K, V> getValidator() {
        return this;
    }

    @Override
    public MapValidator<K, V> containsKey(K key) throws ValidationException {
        if (!getValue().contains(key)) {
            throw newExceptionWithBasics()
                    .setExpectation("contains key")
                    .setExpectedValue(key);
        }
        return getValidator();
    }

    @Override
    public MapValidator<K, V> containsNotKey(K key) throws ValidationException {
        if (getValue().contains(key)) {
            throw newExceptionWithBasics()
                    .setExpectation("contains not key")
                    .setExpectedValue(key);
        }
        return getValidator();
    }

    @Override
    public MapValidator<K, V> eachNumericValue(LongEntryValidator entryValidator) throws ValidationException {
        try {
            for (V value : theMap.values()) {
                if (value == null) {
                    final Long nullLong = null;
                    entryValidator.apply(Validator.expect(nullLong));
                } else if (value instanceof Short) {
                    entryValidator.apply(Validator.expect(Long.valueOf((Short) value)));
                } else if (value instanceof Integer) {
                    entryValidator.apply(Validator.expect(Long.valueOf((Integer) value)));
                } else if (value instanceof Long) {
                    entryValidator.apply(Validator.expect((Long) value));
                } else {
                    throw new ClassCastException(String.format(
                            "Tried to validate values in Map '%s' as numeric values (but are '%s')",
                            getName(), value.getClass().getSimpleName()));
                }
            }
            return this;
        } catch (ValidationException exception) {
            throw exception.setSurroundingContainerInfo(
                    getContainerType(), getName(), getExtraInfo(), "value");
        }
    }

    @Override
    public MapValidator<K, V> eachStringValue(StringEntryValidator entryValidator) throws ValidationException {
        try {
            for (V value : theMap.values()) {
                if (value == null) {
                    final String nullString = null;
                    entryValidator.apply(Validator.expect(nullString));
                } else if (value instanceof String) {
                    entryValidator.apply(Validator.expect((String) value));
                } else {
                    throw new ClassCastException(
                            String.format("Tried to validate values in Map '%s' as Strings (but are '%s')",
                                    getName(), value.getClass().getSimpleName()));
                }
            }
            return this;
        } catch (ValidationException exception) {
            throw exception.setSurroundingContainerInfo(
                    getContainerType(), getName(), getExtraInfo(), "value");
        }
    }
}
