package com.github.aytchell.validator;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.Getter;

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
     * @see String#isEmpty()
     */
    StringValidator notEmpty() throws ValidationException;

    /**
     * Checks that the string under test is not blank
     *
     * @return this validator so you can add more tests
     * @throws ValidationException if the string under test is blank
     * @see String#isBlank()
     */
    StringValidator notBlank() throws ValidationException;

    /**
     * Checks that the string under test does not exceed a given length
     * <p>
     * Note that this method is a bit sloppy (as 'length' for strings is a bit ambiguous). This method checks the number
     * of UTF-16 code units which might differ from the number of "characters" (better: grapheme clusters) and might as
     * well differ from the number of bytes when the string is stored somewhere.
     * <p>
     * See <a href="https://unicode.org/faq/char_combmark.html#7">How are characters counted when measuring the length
     * [of] a string?</a>
     * <p>
     * Note also that the given string is not normalized in any way.
     *
     * @return this validator so you can add more tests
     * @throws ValidationException if the string under test is longer than the given amount
     * @see String#length()
     * @see StringValidator#bytesAtMost
     * @see StringValidator#codePointsAtMost
     */
    StringValidator lengthAtMost(int maxLength) throws ValidationException;

    /**
     * Checks that the string under test takes a max amount of bytes in a certain encoding
     * <p>
     * Depending on the encoding a string might take a different amount of bytes when stored to a file or database. This
     * method can be used to check for a maximum amount of used bytes.
     * <p>
     * Note that this method does not normalize the string in any way.
     *
     * @param maxBytes the maximum allowed number of bytes in the given {@code encoding}
     * @param encoding the encoding to serialize the string
     * @throws ValidationException if the string under test exceeds the given number of bytes
     * @see StringValidator#codePointsAtMost
     */
    StringValidator bytesAtMost(int maxBytes, Encoding encoding) throws ValidationException;

    /**
     * Checks that the string under test containsKey a max amount of code points
     * <p>
     * Each grapheme with its own entry in the unicode table represents one code point. So it can be roughly set equal
     * with what users would recognize as "a character" (although this gets complicated with asian fonts). The main
     * difference to {@link String#length()} is the handling of characters in the supplementary planes (e.g. emoticons,
     * ancient fonts, playing tiles): each of them is one code point but consists of two UTF-16 code units.
     * <p>
     * Note that this method does not normalize the string in any way.
     *
     * @param maxCodepoints the maximum allowed number of code points in the string under test
     * @throws ValidationException if the string under test exceeds the given number of code points
     * @see StringValidator#bytesAtMost
     */
    StringValidator codePointsAtMost(int maxCodepoints) throws ValidationException;

    /**
     * Checks that the given string containsKey a valid URL
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
     * @param predicate a custom predicate to check the string under test
     * @param expectation a custom expectation string that will be used to build the error message. See the
     *         detailed method description for examples.
     * @return this validator so you can add more tests
     * @throws ValidationException if the string under test fails the given predicate
     */
    StringValidator passes(Predicate<String> predicate, String expectation) throws ValidationException;

    enum Encoding {
        UTF_8("UTF-8"),
        UTF_16("UTF-16"),
        UTF_32("UTF-32");

        @Getter
        private final String beautyName;

        Encoding(String name) {
            beautyName = name;
        }
    }
}
