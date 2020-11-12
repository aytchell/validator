package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

// This class shall only be instantiated by Validator
class ArmedMapValidator<K, V> extends ArmedContainerValidator<K, MapValidator<K, V>> implements MapValidator<K, V> {
    private final Map<K, V> theMap;

    ArmedMapValidator(Map<K, V> value, String name) {
        super("Map", new MapCollectionWrapper<>(value), name);
        this.theMap = value;
    }

    @Override
    protected MapValidator<K, V> getValidator() {
        return this;
    }

    @Override
    public MapValidator<K, V> anyNumericValue(LongEntryValidator entryValidator) throws ValidationException {
        try {
            for (V value : theMap.values()) {
                if (value == null) {
                    final Long nullLong = null;
                    entryValidator.apply(Validator.throwIf(nullLong));
                } else if (value instanceof Short) {
                    entryValidator.apply(Validator.throwIf(Long.valueOf((Short) value)));
                } else if (value instanceof Integer) {
                    entryValidator.apply(Validator.throwIf(Long.valueOf((Integer) value)));
                } else if (value instanceof Long) {
                    entryValidator.apply(Validator.throwIf((Long) value));
                } else {
                    throw new ClassCastException(String.format(
                            "Tried to validate entries in Map '%s' as numeric values (but are '%s')",
                            getName(), value.getClass().getSimpleName()));
                }
            }
            return this;
        } catch (ValidationException exception) {
            throw exception
                    .setSurroundingContainerName(getName())
                    .setSurroundingContainerType(getContainerType())
                    .setTypeOfContainerEntry("value");
        }
    }

    @Override
    public MapValidator<K, V> anyStringValue(StringEntryValidator entryValidator) throws ValidationException {
        try {
            for (V value : theMap.values()) {
                if (value == null) {
                    final String nullString = null;
                    entryValidator.apply(Validator.throwIf(nullString));
                } else if (value instanceof String) {
                    entryValidator.apply(Validator.throwIf((String) value));
                } else {
                    throw new ClassCastException(
                            String.format("Tried to validate values in Map '%s' as Strings (but are '%s')",
                                    getName(), value.getClass().getSimpleName()));
                }
            }
            return this;
        } catch (ValidationException exception) {
            throw exception
                    .setSurroundingContainerName(getName())
                    .setSurroundingContainerType(getContainerType())
                    .setTypeOfContainerEntry("value");
        }
    }

    @AllArgsConstructor(access = AccessLevel.PACKAGE)
    private static class MapCollectionWrapper<K, V> implements Collection<K> {
        private final Map<K, V> delegate;

        @Override
        public int size() {
            return delegate.size();
        }

        @Override
        public boolean isEmpty() {
            return delegate.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return delegate.containsKey(o);
        }

        @Override
        public Iterator<K> iterator() {
            return delegate.keySet().iterator();
        }

        @Override
        public Object[] toArray() {
            return delegate.keySet().toArray();
        }

        @Override
        public <T> T[] toArray(T[] ts) {
            return delegate.keySet().toArray(ts);
        }

        @Override
        public boolean add(K k) {
            throw new UnsupportedOperationException("We're validating. Though shalt not modify");
        }

        @Override
        public boolean remove(Object o) {
            throw new UnsupportedOperationException("We're validating. Though shalt not modify");
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            return collection.stream().allMatch(delegate::containsKey);
        }

        @Override
        public boolean addAll(Collection<? extends K> collection) {
            throw new UnsupportedOperationException("We're validating. Though shalt not modify");
        }

        @Override
        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException("We're validating. Though shalt not modify");
        }

        @Override
        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException("We're validating. Though shalt not modify");
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("We're validating. Though shalt not modify");
        }
    }
}
