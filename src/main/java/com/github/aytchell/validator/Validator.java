package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.NONE)
public class Validator {
    public static void throwIfNull(Object value, String parameterName) throws ValidationException {
        if (value == null) {
            throw new ValidationException("Parameter '" + parameterName + "' is missing");
        }
    }

    public static NullableObjectValidator<Short, LongValidator> throwIf(Short value, String name) {
        return new NullableObjectValidator<>(value, name, DisarmedLongValidator.getINSTANCE()) {
            @Override
            protected LongValidator createValidator(Short value, String name) {
                return new ArmedLongValidator(Long.valueOf(value), name);
            }
        };
    }

    public static NullableObjectValidator<Integer, LongValidator> throwIf(Integer value, String name) {
        return new NullableObjectValidator<>(value, name, DisarmedLongValidator.getINSTANCE()) {
            @Override
            protected LongValidator createValidator(Integer value, String name) {
                return new ArmedLongValidator(Long.valueOf(value), name);
            }
        };
    }

    public static NullableObjectValidator<Long, LongValidator> throwIf(Long value, String name) {
        return new NullableObjectValidator<>(value, name, DisarmedLongValidator.getINSTANCE()) {
            @Override
            protected LongValidator createValidator(Long value, String name) {
                return new ArmedLongValidator(value, name);
            }
        };
    }

    public static NullableObjectValidator<String, StringValidator> throwIf(String value, String name) {
        return new NullableObjectValidator<>(value, name, DisarmedStringValidator.getINSTANCE()) {
            @Override
            protected StringValidator createValidator(String value, String name) {
                return new ArmedStringValidator(value, name);
            }
        };
    }

    public static <E> NullableObjectValidator<List<E>, ListValidator<E>> throwIf(List<E> value, String name) {
        return new NullableObjectValidator<>(value, name, new DisarmedListValidator<E>()) {
            @Override
            protected ListValidator<E> createValidator(List<E> value, String name) {
                return new ArmedListValidator<E>(value, name);
            }
        };
    }

    public static <E> NullableObjectValidator<Set<E>, SetValidator<E>> throwIf(Set<E> value, String name) {
        return new NullableObjectValidator<>(value, name, new DisarmedSetValidator<E>()) {
            @Override
            protected SetValidator<E> createValidator(Set<E> value, String name) {
                return new ArmedSetValidator<E>(value, name);
            }
        };
    }

    public static <K, V> NullableObjectValidator<Map<K, V>, MapValidator<K, V>> throwIf(
            Map<K, V> value, String name) {
        return new NullableObjectValidator<>(value, name, new DisarmedMapValidator<K, V>()) {
            @Override
            protected MapValidator<K, V> createValidator(Map<K, V> stringStringMap, String name) {
                return new ArmedMapValidator<>(value, name);
            }
        };
    }
}
