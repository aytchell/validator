package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Specialized validator for {@link String}
 * <p>
 * Each method of this class will check the value under test and if the check fails a {@link ValidationException} will
 * be thrown.
 */
public interface StringValidator {
    /**
     * Checks that the string under test is not empty
     *
     * @return this validator so you can add more tests
     * @throws ValidationException if the string under test is empty
     * @see {@link String#isEmpty()}
     */
    StringValidator notEmpty() throws ValidationException;

    /**
     * Checks that the string under test is not blank
     *
     * @return this validator so you can add more tests
     * @throws ValidationException if the string under test is blank
     * @see {@link String#isBlank()}
     */
    StringValidator notBlank() throws ValidationException;

    /**
     * Checks that the string under test does not exceed a given length
     *
     * @return this validator so you can add more tests
     * @throws ValidationException if the string under test is longer than the given amount
     * @see {@link String#length()} ()}
     */
    StringValidator lengthAtMost(int maxLength) throws ValidationException;

    /**
     * Checks that the given string contains a valid URL
     * <p>
     * This method tries to create a new {@link java.net.URL} and fails if an exception is thrown.
     *
     * @return this validator so you can add more tests
     * @throws ValidationException if the string under test is not accepted by URL's constructor
     */
    StringValidator validUrl() throws ValidationException;

    /**
     * Checks the string under test against a regular expression
     *
     * @param pattern the compiled regular expression to be used for matching
     * @return this validator so you can add more tests
     * @throws ValidationException if the string under test does not match the given regular expression
     */
    StringValidator matches(Pattern pattern) throws ValidationException;

    /**
     * Checks the string under test against a (named) regular expression
     *
     * @param pattern the compiled regular expression to be used for matching
     * @param regexName a name for the regular expression which will be used for the error message
     * @throws ValidationException if the string under test does not match the given regular expression
     */
    StringValidator matches(Pattern pattern, String regexName) throws ValidationException;

    /**
     * Applies a custom predicate to the string under test
     * <p>
     * If there is no other method fitting the needs one can call this method with a custom predicate (e.g. "represents
     * an integer smaller than 1776"). To give the client (the one who provided the value under test) a meaningful error
     * message a custom expectation string has to be provided as well.
     * <p>
     * The given {@code expectation} should fit into this sentence: "Expecting that &lt;string&gt; &lt;expectation&gt;
     * (but does not)" (an example to the above mentioned predicate would be "represents an integer smaller than
     * 1776").
     *
     * @param pred a custom predicate to check the string under test
     * @param expectation a custom expectation string that will be used to build the error message. See the
     *         detailed method description for examples.
     * @return this validator so you can add more tests
     * @throws ValidationException if the string under test fails the given predicate
     */
    StringValidator passes(Predicate<String> pred, String expectation) throws ValidationException;
}
