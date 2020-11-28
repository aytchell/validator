package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

/**
 * Specialized validator for {@link java.util.Set}
 * <p>
 * Each method of this class will check the value under test and if the check fails a {@link ValidationException} will
 * be thrown. Currently this class adds no methods to {@link CollectionValidator}.
 */
public interface SetValidator<E> extends CollectionValidator<E, SetValidator<E>> {
}
