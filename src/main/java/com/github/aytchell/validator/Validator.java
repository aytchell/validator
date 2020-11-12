package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import com.github.aytchell.validator.impl.ValidatorImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.NONE)
public class Validator {
    public static void throwIfNull(Object value, String valueName) throws ValidationException {
        if (value == null) {
            throw new ValidationException()
                    .setActualValuesName(valueName)
                    .setExpectation("is not null");
        }
    }

    public static NullableObjectValidator<Boolean, BooleanValidator> expect(Boolean value) {
        return ValidatorImpl.expect(value, null);
    }

    public static NullableObjectValidator<Boolean, BooleanValidator> expect(Boolean value, String name) {
        return ValidatorImpl.expect(value, name);
    }

    public static NullableObjectValidator<Short, LongValidator> expect(Short value) {
        return ValidatorImpl.expect(value, null);
    }

    public static NullableObjectValidator<Short, LongValidator> expect(Short value, String name) {
        return ValidatorImpl.expect(value, name);
    }

    public static NullableObjectValidator<Integer, LongValidator> expect(Integer value) {
        return ValidatorImpl.expect(value, null);
    }

    public static NullableObjectValidator<Integer, LongValidator> expect(Integer value, String name) {
        return ValidatorImpl.expect(value, name);
    }

    public static NullableObjectValidator<Long, LongValidator> expect(Long value) {
        return ValidatorImpl.expect(value, null);
    }

    public static NullableObjectValidator<Long, LongValidator> expect(Long value, String name) {
        return ValidatorImpl.expect(value, name);
    }

    public static NullableObjectValidator<String, StringValidator> expect(String value) {
        return ValidatorImpl.expect(value, null);
    }

    public static NullableObjectValidator<String, StringValidator> expect(String value, String name) {
        return ValidatorImpl.expect(value, name);
    }

    public static <E> NullableObjectValidator<List<E>, ListValidator<E>> expect(List<E> value) {
        return ValidatorImpl.expect(value, null);
    }

    public static <E> NullableObjectValidator<List<E>, ListValidator<E>> expect(List<E> value, String name) {
        return ValidatorImpl.expect(value, name);
    }

    public static <E> NullableObjectValidator<Set<E>, SetValidator<E>> expect(Set<E> value) {
        return ValidatorImpl.expect(value, null);
    }

    public static <E> NullableObjectValidator<Set<E>, SetValidator<E>> expect(Set<E> value, String name) {
        return ValidatorImpl.expect(value, name);
    }

    public static <K, V> NullableObjectValidator<Map<K, V>, MapValidator<K, V>> expect(Map<K, V> value) {
        return ValidatorImpl.expect(value, null);
    }

    public static <K, V> NullableObjectValidator<Map<K, V>, MapValidator<K, V>> expect(Map<K, V> value, String name) {
        return ValidatorImpl.expect(value, name);
    }
}
