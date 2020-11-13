package com.github.aytchell.validator.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
class MapCollectionWrapper<K, V> implements Collection<K> {
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