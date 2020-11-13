package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.BooleanValidator;
import com.github.aytchell.validator.DoubleValidator;
import com.github.aytchell.validator.ListValidator;
import com.github.aytchell.validator.LongValidator;
import com.github.aytchell.validator.MapValidator;
import com.github.aytchell.validator.NullableObjectValidator;
import com.github.aytchell.validator.SetValidator;
import com.github.aytchell.validator.StringValidator;
import com.github.aytchell.validator.ZonedDateTimeValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.NONE)
public class ValidatorImpl {
    // --- boolean values ---
    public static NullableObjectValidator<Boolean, BooleanValidator> expect(Boolean value, String name,
            String extraInfo) {
        return new NullableObjectValidatorImpl<>(value, name, extraInfo, DisarmedBooleanValidator.getINSTANCE()) {
            @Override
            protected BooleanValidator createValidator(Boolean value, String name, String extraInfo) {
                return new ArmedBooleanValidator(value, name, extraInfo);
            }
        };
    }

    // --- numeric values ---
    public static NullableObjectValidator<Short, LongValidator> expect(Short value, String name, String extraInfo) {
        return new NullableObjectValidatorImpl<>(value, name, extraInfo, DisarmedLongValidator.getINSTANCE()) {
            @Override
            protected LongValidator createValidator(Short value, String name, String extraInfo) {
                return new ArmedLongValidator(Long.valueOf(value), name, extraInfo);
            }
        };
    }

    public static NullableObjectValidator<Integer, LongValidator> expect(Integer value, String name,
            String extraInfo) {
        return new NullableObjectValidatorImpl<>(value, name, extraInfo, DisarmedLongValidator.getINSTANCE()) {
            @Override
            protected LongValidator createValidator(Integer value, String name, String extraInfo) {
                return new ArmedLongValidator(Long.valueOf(value), name, extraInfo);
            }
        };
    }

    public static NullableObjectValidator<Long, LongValidator> expect(Long value, String name, String extraInfo) {
        return new NullableObjectValidatorImpl<>(value, name, extraInfo, DisarmedLongValidator.getINSTANCE()) {
            @Override
            protected LongValidator createValidator(Long value, String name, String extraInfo) {
                return new ArmedLongValidator(value, name, extraInfo);
            }
        };
    }

    public static NullableObjectValidator<Double, DoubleValidator> expect(Double value, String name, String extraInfo) {
        return new NullableObjectValidatorImpl<>(value, name, extraInfo, DisarmedDoubleValidator.getINSTANCE()) {
            @Override
            protected DoubleValidator createValidator(Double value, String name, String extraInfo) {
                return new ArmedDoubleValidator(value, name, extraInfo);
            }
        };
    }

    // --- string values ---
    public static NullableObjectValidator<String, StringValidator> expect(String value, String name,
            String extraInfo) {
        return new NullableObjectValidatorImpl<>(value, name, extraInfo, DisarmedStringValidator.getINSTANCE()) {
            @Override
            protected StringValidator createValidator(String value, String name, String extraInfo) {
                return new ArmedStringValidator(value, name, extraInfo);
            }
        };
    }

    // --- time related values ---
    public static NullableObjectValidator<LocalDate, ZonedDateTimeValidator> expect(
            LocalDate value, String name, String extraInfo) {
        return new NullableObjectValidatorImpl<>(value, name, extraInfo,
                DisarmedZonedDateTimeValidator.getINSTANCE()) {
            @Override
            protected ZonedDateTimeValidator createValidator(LocalDate value, String name, String extraInfo) {
                final ZonedDateTime wrappedValue = ZonedDateTime.of(value,
                        LocalTime.of(0, 0), ZoneId.systemDefault());
                return new ArmedZonedDateTimeValidator(wrappedValue, name, extraInfo);
            }
        };
    }

    public static NullableObjectValidator<LocalDateTime, ZonedDateTimeValidator> expect(
            LocalDateTime value, String name, String extraInfo) {
        return new NullableObjectValidatorImpl<>(value, name, extraInfo,
                DisarmedZonedDateTimeValidator.getINSTANCE()) {
            @Override
            protected ZonedDateTimeValidator createValidator(LocalDateTime value, String name, String extraInfo) {
                final ZonedDateTime wrappedValue = ZonedDateTime.of(value, ZoneId.systemDefault());
                return new ArmedZonedDateTimeValidator(wrappedValue, name, extraInfo);
            }
        };
    }

    public static NullableObjectValidator<ZonedDateTime, ZonedDateTimeValidator> expect(
            ZonedDateTime value, String name, String extraInfo) {
        return new NullableObjectValidatorImpl<>(value, name, extraInfo,
                DisarmedZonedDateTimeValidator.getINSTANCE()) {
            @Override
            protected ZonedDateTimeValidator createValidator(ZonedDateTime value, String name, String extraInfo) {
                return new ArmedZonedDateTimeValidator(value, name, extraInfo);
            }
        };
    }

    // --- container values ---
    public static <E> NullableObjectValidator<List<E>, ListValidator<E>> expect(List<E> value, String name,
            String extraInfo) {
        return new NullableObjectValidatorImpl<>(value, name, extraInfo, new DisarmedListValidator<E>()) {
            @Override
            protected ListValidator<E> createValidator(List<E> value, String name, String extraInfo) {
                return new ArmedListValidator<E>(value, name, extraInfo);
            }
        };
    }

    public static <E> NullableObjectValidator<Set<E>, SetValidator<E>> expect(Set<E> value, String name,
            String extraInfo) {
        return new NullableObjectValidatorImpl<>(value, name, extraInfo, new DisarmedSetValidator<E>()) {
            @Override
            protected SetValidator<E> createValidator(Set<E> value, String name, String extraInfo) {
                return new ArmedSetValidator<E>(value, name, extraInfo);
            }
        };
    }

    public static <K, V> NullableObjectValidator<Map<K, V>, MapValidator<K, V>> expect(
            Map<K, V> value, String name, String extraInfo) {
        return new NullableObjectValidatorImpl<>(value, name, extraInfo, new DisarmedMapValidator<K, V>()) {
            @Override
            protected MapValidator<K, V> createValidator(Map<K, V> stringStringMap, String name, String extraInfo) {
                return new ArmedMapValidator<>(value, name, extraInfo);
            }
        };
    }
}
