package com.github.aytchell.validator;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.impl.ValidatorImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The API class of this library
 * <p>
 * his is the entry class for input validation as provided by this library. Every check of an input value starts with
 * {@code Validate.expect(...)}. The return value will be a {@link NullableObjectValidator} that matches the type of the
 * value to be checked.
 * <p>
 * There are a variety of different types supported and each type has a bunch of tests that can b performed. Every time
 * a check fails the methods throws a ValidationException which will carry a meaningful error message.
 */
@NoArgsConstructor(access = AccessLevel.NONE)
public class Validator {
    /**
     * Check if the given Boolean value passes the expectations.
     *
     * @param value the value to be checked
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value and the expectation that failed
     * @see BooleanValidator for the available checks
     */
    public static NullableObjectValidator<Boolean, BooleanValidator> expect(Boolean value) {
        return ValidatorImpl.expect(value, null, null);
    }

    /**
     * Check if the given (named) Boolean value passes the expectations.
     *
     * @param value the value to be checked
     * @param name The name of the value to be checked (e.g. the method parameter or the name in a json object)
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value, the {@code name} and the expectation that failed
     * @see BooleanValidator for the available checks
     */
    public static NullableObjectValidator<Boolean, BooleanValidator> expect(Boolean value, String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    /**
     * Check if the given (named and described) Boolean passes the expectations.
     *
     * @param value the value to be checked
     * @param name The name of the value to be checked (e.g. the method parameter or the name in a json object)
     * @param extraInfo Some extra information about the parameter under test that should go into the error
     *         message
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value, the {@code name}, the expectation that failed and the
     *         {@code extraInfo} given to this method. The extra information could be used to tell the caller (in case
     *         of an error) additional semantics or the deeper meaning of the parameter.
     * @see BooleanValidator for the available checks
     */
    public static NullableObjectValidator<Boolean, BooleanValidator> expect(Boolean value, String name,
            String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    /**
     * Check if the given Short passes the expectations
     *
     * @param value the value to be checked
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value and the expectation that failed
     * @see LongValidator for the available checks
     */
    public static NullableObjectValidator<Short, LongValidator> expect(Short value) {
        return ValidatorImpl.expect(value, null, null);
    }

    /**
     * Check if the given Short passes the expectations
     *
     * @param value the value to be checked
     * @param name The name of the value to be checked (e.g. the method parameter or the name in a json object)
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value, the {@code name} and the expectation that failed
     * @see LongValidator for the available checks
     */
    public static NullableObjectValidator<Short, LongValidator> expect(Short value, String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    /**
     * Check if the given (named and described) Short passes the expectations.
     *
     * @param value the value to be checked
     * @param name The name of the value to be checked (e.g. the method parameter or the name in a json object)
     * @param extraInfo Some extra information about the parameter under test that should go into the error
     *         message
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value, the {@code name}, the expectation that failed and the
     *         {@code extraInfo} given to this method. The extra information could be used to tell the caller (in case
     *         of an error) additional semantics or the deeper meaning of the parameter.
     * @see LongValidator for the available checks
     */
    public static NullableObjectValidator<Short, LongValidator> expect(Short value, String name, String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    /**
     * Check if the given Integer passes the expectations
     *
     * @param value the value to be checked
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value and the expectation that failed
     * @see LongValidator for the available checks
     */
    public static NullableObjectValidator<Integer, LongValidator> expect(Integer value) {
        return ValidatorImpl.expect(value, null, null);
    }

    /**
     * Check if the given (named) Integer passes the expectations
     *
     * @param value the value to be checked
     * @param name The name of the value to be checked (e.g. the method parameter or the name in a json object)
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value, the {@code name} and the expectation that failed
     * @see LongValidator for the available checks
     */
    public static NullableObjectValidator<Integer, LongValidator> expect(Integer value, String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    /**
     * Check if the given (named and described) Integer passes the expectations.
     *
     * @param value the value to be checked
     * @param name The name of the value to be checked (e.g. the method parameter or the name in a json object)
     * @param extraInfo Some extra information about the parameter under test that should go into the error
     *         message
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value, the {@code name}, the expectation that failed and the
     *         {@code extraInfo} given to this method. The extra information could be used to tell the caller (in case
     *         of an error) additional semantics or the deeper meaning of the parameter.
     * @see LongValidator for the available checks
     */
    public static NullableObjectValidator<Integer, LongValidator> expect(Integer value, String name,
            String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    /**
     * Check if the given Long passes the expectations
     *
     * @param value the value to be checked
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value and the expectation that failed
     * @see LongValidator for the available checks
     */
    public static NullableObjectValidator<Long, LongValidator> expect(Long value) {
        return ValidatorImpl.expect(value, null, null);
    }

    /**
     * Check if the given (named) Long passes the expectations
     *
     * @param value the value to be checked
     * @param name The name of the value to be checked (e.g. the method parameter or the name in a json object)
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value, the {@code name} and the expectation that failed
     * @see LongValidator for the available checks
     */
    public static NullableObjectValidator<Long, LongValidator> expect(Long value, String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    /**
     * Check if the given (named and described) Long passes the expectations.
     *
     * @param value the value to be checked
     * @param name The name of the value to be checked (e.g. the method parameter or the name in a json object)
     * @param extraInfo Some extra information about the parameter under test that should go into the error
     *         message
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value, the {@code name}, the expectation that failed and the
     *         {@code extraInfo} given to this method. The extra information could be used to tell the caller (in case
     *         of an error) additional semantics or the deeper meaning of the parameter.
     * @see LongValidator for the available checks
     */
    public static NullableObjectValidator<Long, LongValidator> expect(Long value, String name, String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    /**
     * Check if the given Double passes the expectations
     *
     * @param value the value to be checked
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value and the expectation that failed
     * @see DoubleValidator for the available checks
     */
    public static NullableObjectValidator<Double, DoubleValidator> expect(Double value) {
        return ValidatorImpl.expect(value, null, null);
    }

    /**
     * Check if the given (named) Double passes the expectations
     *
     * @param value the value to be checked
     * @param name The name of the value to be checked (e.g. the method parameter or the name in a json object)
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value, the {@code name} and the expectation that failed
     * @see DoubleValidator for the available checks
     */
    public static NullableObjectValidator<Double, DoubleValidator> expect(Double value, String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    /**
     * Check if the given (named and described) Double passes the expectations.
     *
     * @param value the value to be checked
     * @param name The name of the value to be checked (e.g. the method parameter or the name in a json object)
     * @param extraInfo Some extra information about the parameter under test that should go into the error
     *         message
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value, the {@code name}, the expectation that failed and the
     *         {@code extraInfo} given to this method. The extra information could be used to tell the caller (in case
     *         of an error) additional semantics or the deeper meaning of the parameter.
     * @see DoubleValidator for the available checks
     */
    public static NullableObjectValidator<Double, DoubleValidator> expect(Double value, String name, String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    /**
     * Check if the given String passes the expectations
     *
     * @param value the value to be checked
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value and the expectation that failed
     * @see StringValidator for the available checks
     */
    public static NullableObjectValidator<String, StringValidator> expect(String value) {
        return ValidatorImpl.expect(value, null, null);
    }

    /**
     * Check if the given (named) String passes the expectations
     *
     * @param value the value to be checked
     * @param name The name of the value to be checked (e.g. the method parameter or the name in a json object)
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value, the {@code name} and the expectation that failed
     * @see StringValidator for the available checks
     */
    public static NullableObjectValidator<String, StringValidator> expect(String value, String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    /**
     * Check if the given (named and described) String passes the expectations.
     *
     * @param value the value to be checked
     * @param name The name of the value to be checked (e.g. the method parameter or the name in a json object)
     * @param extraInfo Some extra information about the parameter under test that should go into the error
     *         message
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value, the {@code name}, the expectation that failed and the
     *         {@code extraInfo} given to this method. The extra information could be used to tell the caller (in case
     *         of an error) additional semantics or the deeper meaning of the parameter.
     * @see StringValidator for the available checks
     */
    public static NullableObjectValidator<String, StringValidator> expect(String value, String name,
            String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    /**
     * Check if the given LocalDate passes the expectations
     * <p>
     * To be comparable with other time formats the argument {@code value} is internally expanded to a {@link
     * OffsetDateTime}. The time component is set to 0:00 and the timezone is set to UTC.
     *
     * @param value the value to be checked
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value and the expectation that failed
     * @see OffsetDateTimeValidator for the available checks
     */
    public static NullableObjectValidator<LocalDate, OffsetDateTimeValidator> expect(LocalDate value) {
        return ValidatorImpl.expect(value, null, null);
    }

    /**
     * Check if the given (named) LocalDate passes the expectations
     * <p>
     * To be comparable with other time formats the argument {@code value} is internally expanded to a {@link
     * OffsetDateTime}. The time component is set to 0:00 and the timezone is set to UTC.
     *
     * @param value the value to be checked
     * @param name The name of the value to be checked (e.g. the method parameter or the name in a json object)
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value, the {@code name} and the expectation that failed
     * @see OffsetDateTimeValidator for the available checks
     */
    public static NullableObjectValidator<LocalDate, OffsetDateTimeValidator> expect(LocalDate value, String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    /**
     * Check if the given (named and described) LocalDate passes the expectations.
     * <p>
     * To be comparable with other time formats the argument {@code value} is internally expanded to a {@link
     * OffsetDateTime}. The time component is set to 0:00 and the timezone is set to UTC.
     *
     * @param value the value to be checked
     * @param name The name of the value to be checked (e.g. the method parameter or the name in a json object)
     * @param extraInfo Some extra information about the parameter under test that should go into the error
     *         message
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value, the {@code name}, the expectation that failed and the
     *         {@code extraInfo} given to this method. The extra information could be used to tell the caller (in case
     *         of an error) additional semantics or the deeper meaning of the parameter.
     * @see OffsetDateTimeValidator for the available checks
     */
    public static NullableObjectValidator<LocalDate, OffsetDateTimeValidator> expect(LocalDate value, String name,
                                                                                     String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    /**
     * Check if the given LocalDateTime passes the expectations
     * <p>
     * To be comparable with other time formats the argument {@code value} is internally expanded to a {@link
     * OffsetDateTime}. The timezone is set to UTC.
     *
     * @param value the value to be checked
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value and the expectation that failed
     * @see OffsetDateTimeValidator for the available checks
     */
    public static NullableObjectValidator<LocalDateTime, OffsetDateTimeValidator> expect(LocalDateTime value) {
        return ValidatorImpl.expect(value, null, null);
    }

    /**
     * Check if the given (named) LocalDateTime value passes the expectations.
     * <p>
     * To be comparable with other time formats the argument {@code value} is internally expanded to a {@link
     * OffsetDateTime}. The timezone is set to UTC.
     *
     * @param value the value to be checked
     * @param name The name of the value to be checked (e.g. the method parameter or the name in a json object)
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value, the {@code name} and the expectation that failed
     * @see OffsetDateTimeValidator for the available checks
     */
    public static NullableObjectValidator<LocalDateTime, OffsetDateTimeValidator> expect(LocalDateTime value,
                                                                                         String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    /**
     * Check if the given (named and described) LocalDateTime passes the expectations.
     * <p>
     * To be comparable with other time formats the argument {@code value} is internally expanded to a {@link
     * OffsetDateTime}. The timezone is set to UTC.
     *
     * @param value the value to be checked
     * @param name The name of the value to be checked (e.g. the method parameter or the name in a json object)
     * @param extraInfo Some extra information about the parameter under test that should go into the error
     *         message
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value, the {@code name}, the expectation that failed and the
     *         {@code extraInfo} given to this method. The extra information could be used to tell the caller (in case
     *         of an error) additional semantics or the deeper meaning of the parameter.
     * @see OffsetDateTimeValidator for the available checks
     */
    public static NullableObjectValidator<LocalDateTime, OffsetDateTimeValidator> expect(LocalDateTime value,
                                                                                         String name, String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    /**
     * Check if the given ZonedDateTime passes the expectations
     * <p>
     * To be comparable with other time formats the argument {@code value} is internally converted to an {@link
     * OffsetDateTime}.
     *
     * @param value the value to be checked
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value and the expectation that failed
     * @see OffsetDateTimeValidator for the available checks
     */
    public static NullableObjectValidator<ZonedDateTime, OffsetDateTimeValidator> expect(ZonedDateTime value) {
        return ValidatorImpl.expect(value, null, null);
    }

    /**
     * Check if the given (named) ZonedDateTime value passes the expectations.
     * <p>
     * To be comparable with other time formats the argument {@code value} is internally converted to an {@link
     * OffsetDateTime}.
     *
     * @param value the value to be checked
     * @param name The name of the value to be checked (e.g. the method parameter or the name in a json object)
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value, the {@code name} and the expectation that failed
     * @see OffsetDateTimeValidator for the available checks
     */
    public static NullableObjectValidator<ZonedDateTime, OffsetDateTimeValidator> expect(
            ZonedDateTime value, String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    /**
     * Check if the given (named and described) ZonedDateTime passes the expectations.
     * <p>
     * To be comparable with other time formats the argument {@code value} is internally converted to an {@link
     * OffsetDateTime}.
     *
     * @param value the value to be checked
     * @param name The name of the value to be checked (e.g. the method parameter or the name in a json object)
     * @param extraInfo Some extra information about the parameter under test that should go into the error
     *         message
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value, the {@code name}, the expectation that failed and the
     *         {@code extraInfo} given to this method. The extra information could be used to tell the caller (in case
     *         of an error) additional semantics or the deeper meaning of the parameter.
     * @see OffsetDateTimeValidator for the available checks
     */
    public static NullableObjectValidator<ZonedDateTime, OffsetDateTimeValidator> expect(
            ZonedDateTime value, String name, String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    /**
     * Check if the given OffsetDateTime passes the expectations
     *
     * @param value the value to be checked
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value and the expectation that failed
     * @see OffsetDateTimeValidator for the available checks
     */
    public static NullableObjectValidator<OffsetDateTime, OffsetDateTimeValidator> expect(OffsetDateTime value) {
        return ValidatorImpl.expect(value, null, null);
    }

    /**
     * Check if the given (named) OffsetDateTime value passes the expectations.
     *
     * @param value the value to be checked
     * @param name The name of the value to be checked (e.g. the method parameter or the name in a json object)
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value, the {@code name} and the expectation that failed
     * @see OffsetDateTimeValidator for the available checks
     */
    public static NullableObjectValidator<OffsetDateTime, OffsetDateTimeValidator> expect(
            OffsetDateTime value, String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    /**
     * Check if the given (named and described) OffsetDateTime passes the expectations.
     *
     * @param value the value to be checked
     * @param name The name of the value to be checked (e.g. the method parameter or the name in a json object)
     * @param extraInfo Some extra information about the parameter under test that should go into the error
     *         message
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value, the {@code name}, the expectation that failed and the
     *         {@code extraInfo} given to this method. The extra information could be used to tell the caller (in case
     *         of an error) additional semantics or the deeper meaning of the parameter.
     * @see OffsetDateTimeValidator for the available checks
     */
    public static NullableObjectValidator<OffsetDateTime, OffsetDateTimeValidator> expect(
            OffsetDateTime value, String name, String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    /**
     * Check if the given List passes the expectations
     *
     * @param value the List to be checked
     * @param <E> the type of the instances stored in {@code value}
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     * @see ListValidator for the available checks
     */
    public static <E> NullableObjectValidator<List<E>, ListValidator<E>> expect(List<E> value) {
        return ValidatorImpl.expect(value, null, null);
    }

    /**
     * Check if the given (named) List passes the expectations.
     *
     * @param <E> the type of the instances stored in {@code value}
     * @param value the List to be checked
     * @param name The name of the List to be checked (e.g. the method parameter or the name in a json object)
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the List under test does not pass the expectations, the error message of the thrown exception will
     *         contain the {@code name} and the expectation that failed
     * @see ListValidator for the available checks
     */
    public static <E> NullableObjectValidator<List<E>, ListValidator<E>> expect(List<E> value, String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    /**
     * Check if the given (named and described) List passes the expectations.
     *
     * @param value the List to be checked
     * @param name The name of the List to be checked (e.g. the method parameter or the name in a json object)
     * @param extraInfo Some extra information about the List under test that should go into the error message
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the List under test does not pass the expectations, the error message of the thrown exception will
     *         contain the {@code name}, the expectation that failed and the {@code extraInfo} given to this method. The
     *         extra information could be used to tell the caller (in case of an error) additional semantics or the
     *         deeper meaning of the List.
     * @see ListValidator for the available checks
     */
    public static <E> NullableObjectValidator<List<E>, ListValidator<E>> expect(List<E> value, String name,
            String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    /**
     * Check if the given Set passes the expectations
     *
     * @param value the Set to be checked
     * @param <E> the type of the instances stored in {@code value}
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     * @see SetValidator for the available checks
     */
    public static <E> NullableObjectValidator<Set<E>, SetValidator<E>> expect(Set<E> value) {
        return ValidatorImpl.expect(value, null, null);
    }

    /**
     * Check if the given (named) Set passes the expectations.
     *
     * @param <E> the type of the instances stored in {@code value}
     * @param value the Set to be checked
     * @param name The name of the Set to be checked (e.g. the method parameter or the name in a json object)
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the Set under test does not pass the expectations, the error message of the thrown exception will
     *         contain the {@code name} and the expectation that failed
     * @see SetValidator for the available checks
     */
    public static <E> NullableObjectValidator<Set<E>, SetValidator<E>> expect(Set<E> value, String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    /**
     * Check if the given (named and described) Set passes the expectations.
     *
     * @param value the Set to be checked
     * @param name The name of the Set to be checked (e.g. the method parameter or the name in a json object)
     * @param extraInfo Some extra information about the Set under test that should go into the error message
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the Set under test does not pass the expectations, the error message of the thrown exception will
     *         contain the {@code name}, the expectation that failed and the {@code extraInfo} given to this method. The
     *         extra information could be used to tell the caller (in case of an error) additional semantics or the
     *         deeper meaning of the Set.
     * @see SetValidator for the available checks
     */
    public static <E> NullableObjectValidator<Set<E>, SetValidator<E>> expect(Set<E> value, String name,
            String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    /**
     * Check if the given Map passes the expectations
     *
     * @param <K> the type of the Map's keys used in {@code value}
     * @param <V> the type of the Map's values used in {@code value}
     * @param value the Map to be checked
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     * @see MapValidator for the available checks
     */
    public static <K, V> NullableObjectValidator<Map<K, V>, MapValidator<K, V>> expect(Map<K, V> value) {
        return ValidatorImpl.expect(value, null, null);
    }

    /**
     * Check if the given (named) Map passes the expectations.
     *
     * @param <K> the type of the Map's keys used in {@code value}
     * @param <V> the type of the Map's values used in {@code value}
     * @param value the Map to be checked
     * @param name The name of the Map to be checked (e.g. the method parameter or the name in a json object)
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the Map under test does not pass the expectations, the error message of the thrown exception will
     *         contain the {@code name} and the expectation that failed
     * @see MapValidator for the available checks
     */
    public static <K, V> NullableObjectValidator<Map<K, V>, MapValidator<K, V>> expect(Map<K, V> value, String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    /**
     * Check if the given (named and described) Map passes the expectations.
     *
     * @param value the Map to be checked
     * @param name The name of the Map to be checked (e.g. the method parameter or the name in a json object)
     * @param extraInfo Some extra information about the Map under test that should go into the error message
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the Map under test does not pass the expectations, the error message of the thrown exception will
     *         contain the {@code name}, the expectation that failed and the {@code extraInfo} given to this method. The
     *         extra information could be used to tell the caller (in case of an error) additional semantics or the
     *         deeper meaning of the Map.
     * @see MapValidator for the available checks
     */
    public static <K, V> NullableObjectValidator<Map<K, V>, MapValidator<K, V>> expect(Map<K, V> value, String name,
            String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }

    /**
     * Check if the given (generic) Object instance passes the expectations
     * <p>
     * In case there is no specialized validator for a given type (e.g. all self-defined classes) you can use this
     * method. It will give you the possibility to write custom checks based on the other validators.
     *
     * @param <E> the type of the parameter to be checked. This wll be figured out by the compiler.
     * @param value the value to be checked
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value and the expectation that failed
     * @see CustomValidator for the available checks
     */
    public static <E> NullableObjectValidator<E, CustomValidator<E>> expect(E value) {
        return ValidatorImpl.expect(value, null, null);
    }

    /**
     * Check if the given (named) generic Object passes the expectations
     * <p>
     * In case there is no specialized validator for a given type (e.g. all self-defined classes) you can use this
     * method. It will give you the possibility to write custom checks based on the other validators.
     *
     * @param value the value to be checked
     * @param name The name of the value to be checked (e.g. the method parameter or the name in a json object)
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value, the {@code name} and the expectation that failed
     * @see CustomValidator for the available checks
     */
    public static <E> NullableObjectValidator<E, CustomValidator<E>> expect(E value, String name) {
        return ValidatorImpl.expect(value, name, null);
    }

    /**
     * Check if the given (named and described) generic Object passes the expectations.
     * <p>
     * In case there is no specialized validator for a given type (e.g. all self-defined classes) you can use this
     * method. It will give you the possibility to write custom checks based on the other validators.
     *
     * @param value the value to be checked
     * @param name The name of the value to be checked (e.g. the method parameter or the name in a json object)
     * @param extraInfo Some extra information about the parameter under test that should go into the error
     *         message
     * @return An object instance which can be used to perform initial tests before "the real tests" start
     *         <p>
     *         If the actual value of the parameter under test does not pass the expectations, the error message of the
     *         thrown exception will contain the actual value, the {@code name}, the expectation that failed and the
     *         {@code extraInfo} given to this method. The extra information could be used to tell the caller (in case
     *         of an error) additional semantics or the deeper meaning of the parameter.
     * @see CustomValidator for the available checks
     */
    public static <E> NullableObjectValidator<E, CustomValidator<E>> expect(E value, String name,
            String extraInfo) {
        return ValidatorImpl.expect(value, name, extraInfo);
    }
}
