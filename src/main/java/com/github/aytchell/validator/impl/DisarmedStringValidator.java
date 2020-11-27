package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.StringValidator;
import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DisarmedStringValidator implements StringValidator {
    @Getter
    private static final StringValidator INSTANCE = new DisarmedStringValidator();

    @Override
    public StringValidator notEmpty() {
        return this;
    }

    @Override
    public StringValidator notBlank() {
        return this;
    }

    @Override
    public StringValidator lengthAtMost(int maxLength) {
        return this;
    }

    @Override
    public StringValidator bytesAtMost(int maxBytes, Encoding encoding) {
        return this;
    }

    @Override
    public StringValidator codePointsAtMost(int maxCodepoints) {
        return this;
    }

    @Override
    public StringValidator validUrl() {
        return this;
    }

    @Override
    public StringValidator matches(Pattern pattern) {
        return this;
    }

    @Override
    public StringValidator matches(Pattern pattern, String regexName) {
        return this;
    }

    @Override
    public StringValidator passes(Predicate<String> pred, String expectation) {
        return this;
    }
}
