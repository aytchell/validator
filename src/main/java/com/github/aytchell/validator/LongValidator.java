package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

import java.util.function.LongPredicate;

/**
 * Specialized validator for {@link Short}, {@link Integer} and {@link Long}
 * <p>
 * Each method of this class will check the value under test and if the check fails a {@link ValidationException} will
 * be thrown.
 */
public interface LongValidator {
    /**
     * Check that the value under test is greater than a given value
     *
     * @param lowerBound the 'other value' which is expected to be less than the value under test
     * @return this validator so you can add more tests
     * @throws ValidationException if the value under test is less or equal to the given value
     */
    LongValidator greaterThan(long lowerBound) throws ValidationException;

    /**
     * Check that the value under test is greater than a given (named) value
     *
     * @param lowerBound the 'other value' which is expected to be less than the value under test
     * @param otherName the name of the given value. This name will appear in the error message.
     * @return this validator so you can add more tests
     * @throws ValidationException if the value under test is less or equal to the given value
     */
    LongValidator greaterThan(long lowerBound, String otherName) throws ValidationException;

    /**
     * Check that the value under test is greater or equal to a given value
     *
     * @param lowerBound the 'other value' which is expected to be less or equal to the value under test
     * @return this validator so you can add more tests
     * @throws ValidationException if the value under test is less than the given value
     */
    LongValidator greaterEqThan(long lowerBound) throws ValidationException;

    /**
     * Check that the value under test is greater or equal to a given (named) value
     *
     * @param lowerBound the 'other value' which is expected to be less or equal to the value under test
     * @param otherName the name of the given value. This name will appear in the error message.
     * @return this validator so you can add more tests
     * @throws ValidationException if the value under test is less than the given value
     */
    LongValidator greaterEqThan(long lowerBound, String otherName) throws ValidationException;

    /**
     * Check that the value under test is less than a given value
     *
     * @param upperBound the 'other value' which is expected to be greater than the value under test
     * @return this validator so you can add more tests
     * @throws ValidationException if the value under test is greater or equal to the given value
     */
    LongValidator lessThan(long upperBound) throws ValidationException;

    /**
     * Check that the value under test is greater than a given (named) value
     *
     * @param upperBound the 'other value' which is expected to be greater than the value under test
     * @param otherName the name of the given value. This name will appear in the error message.
     * @return this validator so you can add more tests
     * @throws ValidationException if the value under test is greater or equal to the given value
     */
    LongValidator lessThan(long upperBound, String otherName) throws ValidationException;

    /**
     * Check that the value under test is less or equal to a given value
     *
     * @param upperBound the 'other value' which is expected to be greater or equal to the value under test
     * @return this validator so you can add more tests
     * @throws ValidationException if the value under test is greater than the given value
     */
    LongValidator lessEqThan(long upperBound) throws ValidationException;

    /**
     * Check that the value under test is less or equal to a given (named) value
     *
     * @param upperBound the 'other value' which is expected to be greater or equal to the value under test
     * @param otherName the name of the given value. This name will appear in the error message.
     * @return this validator so you can add more tests
     * @throws ValidationException if the value under test is greater than the given value
     */
    LongValidator lessEqThan(long upperBound, String otherName) throws ValidationException;

    /**
     * Check that the value under test is a valid (TCP or UDP) port number
     * <p>
     * Basically this is a convenience method for {@code .greaterThan(0).lessThan(65536)}. The main advantage of using
     * this method is that the produced error message is 'nicer' in that it tells the caller that we're expecting a
     * valid port number.
     *
     * @return this validator so you can add more tests
     * @throws ValidationException if the value under test is no valid (TCP or UDP) port number
     */
    LongValidator validPortNumber() throws ValidationException;

    /**
     * Applies a custom predicate to the value under test
     * <p>
     * If there is no other method fitting the needs one can call this method with a custom predicate (things like
     * {@code isFibonacci(x)}, {@code isEven(x)} or {@code isTransactionId(x)}). To give the client (the one who
     * provided the value under test) a meaningful error message a custom expectation string has to be provided as
     * well.
     * <p>
     * The given {@code expectation} should fit into this sentence: "Expecting that &lt;value&gt; &lt;expectation&gt;
     * (but is not)" (examples to the above mentioned predicates would be "is a fibonacci number", "is even" or "is a
     * transaction id")
     *
     * @param predicate a custom predicate to check the value under test
     * @param expectation a custom expectation string that will be used to build the error message. See the
     *         detailed method description for examples.
     * @return this validator so you can add more tests
     * @throws ValidationException if the value under test fails the given predicate
     */
    LongValidator passes(LongPredicate predicate, String expectation) throws ValidationException;
}
