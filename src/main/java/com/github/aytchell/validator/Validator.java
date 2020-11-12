package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
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
                    .setExpectation("shall not be null");
        }
    }

    public static NullableObjectValidator<Short, LongValidator> expect(Short value) {
        return expect(value, null);
    }

    public static NullableObjectValidator<Short, LongValidator> expect(Short value, String name) {
        return new NullableObjectValidator<>(value, name, DisarmedLongValidator.getINSTANCE()) {
            @Override
            protected LongValidator createValidator(Short value, String name) {
                return new ArmedLongValidator(Long.valueOf(value), name);
            }
        };
    }

    public static NullableObjectValidator<Integer, LongValidator> expect(Integer value) {
        return expect(value, null);
    }

    public static NullableObjectValidator<Integer, LongValidator> expect(Integer value, String name) {
        return new NullableObjectValidator<>(value, name, DisarmedLongValidator.getINSTANCE()) {
            @Override
            protected LongValidator createValidator(Integer value, String name) {
                return new ArmedLongValidator(Long.valueOf(value), name);
            }
        };
    }

    public static NullableObjectValidator<Long, LongValidator> expect(Long value) {
        return expect(value, null);
    }

    public static NullableObjectValidator<Long, LongValidator> expect(Long value, String name) {
        return new NullableObjectValidator<>(value, name, DisarmedLongValidator.getINSTANCE()) {
            @Override
            protected LongValidator createValidator(Long value, String name) {
                return new ArmedLongValidator(value, name);
            }
        };
    }

    public static NullableObjectValidator<String, StringValidator> expect(String value) {
        return expect(value, null);
    }

    public static NullableObjectValidator<String, StringValidator> expect(String value, String name) {
        return new NullableObjectValidator<>(value, name, DisarmedStringValidator.getINSTANCE()) {
            @Override
            protected StringValidator createValidator(String value, String name) {
                return new ArmedStringValidator(value, name);
            }
        };
    }

    public static <E> NullableObjectValidator<List<E>, ListValidator<E>> expect(List<E> value) {
        return expect(value, null);
    }

    public static <E> NullableObjectValidator<List<E>, ListValidator<E>> expect(List<E> value, String name) {
        return new NullableObjectValidator<>(value, name, new DisarmedListValidator<E>()) {
            @Override
            protected ListValidator<E> createValidator(List<E> value, String name) {
                return new ArmedListValidator<E>(value, name);
            }
        };
    }

    public static <E> NullableObjectValidator<Set<E>, SetValidator<E>> expect(Set<E> value) {
        return expect(value, null);
    }

    public static <E> NullableObjectValidator<Set<E>, SetValidator<E>> expect(Set<E> value, String name) {
        return new NullableObjectValidator<>(value, name, new DisarmedSetValidator<E>()) {
            @Override
            protected SetValidator<E> createValidator(Set<E> value, String name) {
                return new ArmedSetValidator<E>(value, name);
            }
        };
    }

    public static <K, V> NullableObjectValidator<Map<K, V>, MapValidator<K, V>> expect(Map<K, V> value) {
        return expect(value, null);
    }

    public static <K, V> NullableObjectValidator<Map<K, V>, MapValidator<K, V>> expect(
            Map<K, V> value, String name) {
        return new NullableObjectValidator<>(value, name, new DisarmedMapValidator<K, V>()) {
            @Override
            protected MapValidator<K, V> createValidator(Map<K, V> stringStringMap, String name) {
                return new ArmedMapValidator<>(value, name);
            }
        };
    }
}
