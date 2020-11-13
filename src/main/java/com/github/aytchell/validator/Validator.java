package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import com.github.aytchell.validator.impl.ValidatorImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.NONE)
public class Validator {
    // fallback for every type that has no 'expect' method
    public static void expectNotNull(Object value, String valueName) throws ValidationException {
        expectNotNull(value, valueName, null);
    }

    // fallback for every type that has no 'expect' method
    public static void expectNotNull(Object value, String valueName, String extraInfo) throws ValidationException {
        if (value == null) {
            throw new ValidationException()
                    .setActualValuesName(valueName)
                    .setValuesExtraInfo(extraInfo)
                    .setExpectation("is not null");
        }
    }

    // --- check Boolean ---
    public static NullableObjectValidator<Boolean, BooleanValidator> expect(Boolean value) {
        return ValidatorImpl.expect(value, null, null);
    }

    public static NullableObjectValidator<Boolean, BooleanValidator> expect(Boolean value, String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    public static NullableObjectValidator<Boolean, BooleanValidator> expect(Boolean value, String name,
            String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    // --- check Short ---
    public static NullableObjectValidator<Short, LongValidator> expect(Short value) {
        return ValidatorImpl.expect(value, null, null);
    }

    public static NullableObjectValidator<Short, LongValidator> expect(Short value, String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    public static NullableObjectValidator<Short, LongValidator> expect(Short value, String name, String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    // --- check Integer ---
    public static NullableObjectValidator<Integer, LongValidator> expect(Integer value) {
        return ValidatorImpl.expect(value, null, null);
    }

    public static NullableObjectValidator<Integer, LongValidator> expect(Integer value, String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    public static NullableObjectValidator<Integer, LongValidator> expect(Integer value, String name,
            String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    // --- check Long ---
    public static NullableObjectValidator<Long, LongValidator> expect(Long value) {
        return ValidatorImpl.expect(value, null, null);
    }

    public static NullableObjectValidator<Long, LongValidator> expect(Long value, String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    public static NullableObjectValidator<Long, LongValidator> expect(Long value, String name, String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    // --- check Double ---
    public static NullableObjectValidator<Double, DoubleValidator> expect(Double value) {
        return ValidatorImpl.expect(value, null, null);
    }

    public static NullableObjectValidator<Double, DoubleValidator> expect(Double value, String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    public static NullableObjectValidator<Double, DoubleValidator> expect(Double value, String name, String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    // --- check String ---
    public static NullableObjectValidator<String, StringValidator> expect(String value) {
        return ValidatorImpl.expect(value, null, null);
    }

    public static NullableObjectValidator<String, StringValidator> expect(String value, String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    public static NullableObjectValidator<String, StringValidator> expect(String value, String name,
            String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    // --- check LocalDate ---
    public static NullableObjectValidator<LocalDate, ZonedDateTimeValidator> expect(LocalDate value) {
        return ValidatorImpl.expect(value, null, null);
    }

    public static NullableObjectValidator<LocalDate, ZonedDateTimeValidator> expect(LocalDate value, String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    public static NullableObjectValidator<LocalDate, ZonedDateTimeValidator> expect(LocalDate value, String name,
            String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    // --- check LocalDateTime ---
    public static NullableObjectValidator<LocalDateTime, ZonedDateTimeValidator> expect(LocalDateTime value) {
        return ValidatorImpl.expect(value, null, null);
    }

    public static NullableObjectValidator<LocalDateTime, ZonedDateTimeValidator> expect(LocalDateTime value,
            String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    public static NullableObjectValidator<LocalDateTime, ZonedDateTimeValidator> expect(LocalDateTime value,
            String name, String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    // --- check ZonedDateTime ---
    public static NullableObjectValidator<ZonedDateTime, ZonedDateTimeValidator> expect(ZonedDateTime value) {
        return ValidatorImpl.expect(value, null, null);
    }

    public static NullableObjectValidator<ZonedDateTime, ZonedDateTimeValidator> expect(ZonedDateTime value,
            String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    public static NullableObjectValidator<ZonedDateTime, ZonedDateTimeValidator> expect(ZonedDateTime value,
            String name, String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    // --- check List ---
    public static <E> NullableObjectValidator<List<E>, ListValidator<E>> expect(List<E> value) {
        return ValidatorImpl.expect(value, null, null);
    }

    public static <E> NullableObjectValidator<List<E>, ListValidator<E>> expect(List<E> value, String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    public static <E> NullableObjectValidator<List<E>, ListValidator<E>> expect(List<E> value, String name,
            String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    // --- check Set ---
    public static <E> NullableObjectValidator<Set<E>, SetValidator<E>> expect(Set<E> value) {
        return ValidatorImpl.expect(value, null, null);
    }

    public static <E> NullableObjectValidator<Set<E>, SetValidator<E>> expect(Set<E> value, String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    public static <E> NullableObjectValidator<Set<E>, SetValidator<E>> expect(Set<E> value, String name,
            String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    // --- check Map ---
    public static <K, V> NullableObjectValidator<Map<K, V>, MapValidator<K, V>> expect(Map<K, V> value) {
        return ValidatorImpl.expect(value, null, null);
    }

    public static <K, V> NullableObjectValidator<Map<K, V>, MapValidator<K, V>> expect(Map<K, V> value, String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    public static <K, V> NullableObjectValidator<Map<K, V>, MapValidator<K, V>> expect(Map<K, V> value, String name,
            String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }
}
