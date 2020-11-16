package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public interface StringValidator {
    StringValidator notEmpty() throws ValidationException;

    StringValidator notBlank() throws ValidationException;

    StringValidator lengthAtMost(int maxLength) throws ValidationException;

    StringValidator validUrl() throws ValidationException;

    StringValidator matches(Pattern pattern) throws ValidationException;

    StringValidator matches(Pattern pattern, String regexName) throws ValidationException;

    StringValidator passes(Predicate<String> pred, String expectation) throws ValidationException;
}
