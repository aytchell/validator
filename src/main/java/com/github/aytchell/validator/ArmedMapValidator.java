package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

// This class shall only be instantiated by Validator
class ArmedMapValidator<K, V> extends ArmedContainerValidator<K, MapValidator<K, V>> implements MapValidator<K, V> {

    ArmedMapValidator(Map<K, V> value, String name) {
        super("Map", new MapCollectionWrapper<>(value), name);
    }

    @Override
    public ArmedMapValidator<K, V> anyValueIsLongerThan(int maxLength) throws ValidationException {
        // TODO: Replace with generic method
        return this;
    }

    @Override
    protected MapValidator<K, V> getValidator() {
        return this;
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
