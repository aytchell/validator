package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

/**
 * The first step in a chain of checks
 * <p>
 * After calling one of the {@code expect} methods of {@link Validator} you'll get an instance of this class. It
 * represents the first stage of testing the value. By choosing the method you call you control if further checks are
 * always executed or only if specific conditions are met.
 *
 * @param <TYPE> the type of the value which will be checked by this class
 * @param <VALIDATOR> the specialized validator corresponding to the value's type
 */
public interface NullableObjectValidator<TYPE, VALIDATOR> {
    /**
     * Perform all following checks only if the given {@code condition} is {@code true}
     * <p>
     * Note that you have to append a call to {@link NullableObjectValidator#notNull()} or {@link
     * NullableObjectValidator#ifNotNull()} to perform "real" checks.
     *
     * @param condition this condition says whether the following tests are executed or skipped
     * @return this same instance so you can do further checks
     */
    NullableObjectValidator<TYPE, VALIDATOR> ifTrue(boolean condition);

    /**
     * Perform all following checks only if the given {@code condition} is {@code false}
     * <p>
     * Note that you have to append a call to {@link NullableObjectValidator#notNull()} or {@link
     * NullableObjectValidator#ifNotNull()} to perform "real" checks.
     *
     * @param condition this condition says whether the following tests are skipped or executed
     * @return this same instance so you can do further checks
     */
    NullableObjectValidator<TYPE, VALIDATOR> ifFalse(boolean condition);

    /**
     * Perform all following checks only if the given {@code condition} is not null and {@code true}
     * <p>
     * Note that you have to append a call to {@link NullableObjectValidator#notNull()} or {@link
     * NullableObjectValidator#ifNotNull()} to perform "real" checks.
     *
     * @param condition this condition says whether the following tests are executed or skipped
     * @return this same instance so you can do further checks
     */
    NullableObjectValidator<TYPE, VALIDATOR> ifGivenAndTrue(Boolean condition);

    /**
     * Perform all following checks only if the given {@code condition} is either null or (if given) {@code false}
     * <p>
     * Note that you have to append a call to {@link NullableObjectValidator#notNull()} or {@link
     * NullableObjectValidator#ifNotNull()} to perform "real" checks.
     *
     * @param condition this condition says whether the following tests are skipped or executed
     * @return this same instance so you can do further checks
     */
    NullableObjectValidator<TYPE, VALIDATOR> ifNotGivenOrFalse(Boolean condition);

    /**
     * Ensure that the value under test is not null; otherwise throw an exception
     *
     * @return a specialized validator to check values of type {@code TYPE} (see the appropriate {@code expect} method
     *         of {@link Validator}
     * @throws ValidationException if the value under test is null
     */
    VALIDATOR notNull() throws ValidationException;

    /**
     * Perform all following checks only if the value under test is not null; otherwise skip them
     *
     * @return a specialized validator to check values of type {@code TYPE} (see the appropriate {@code expect} method
     *         of {@link Validator}
     */
    VALIDATOR ifNotNull();
}
