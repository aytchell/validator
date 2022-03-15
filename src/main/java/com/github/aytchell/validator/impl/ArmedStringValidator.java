package com.github.aytchell.validator.impl;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.StringValidator;
import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
class ArmedStringValidator implements StringValidator {
    private final String value;
    private final String name;
    private final String extraInfo;

    @Override
    public StringValidator notEmpty() throws ValidationException {
        if (this.value.isEmpty()) {
            throw newExceptionWithBasics()
                    .setExpectation("is not empty");
        }
        return this;
    }

    @Override
    public StringValidator notBlank() throws ValidationException {
        if (this.value.isBlank()) {
            throw newExceptionWithBasics()
                    .setExpectation("is not blank");
        }
        return this;
    }

    @Override
    public StringValidator lengthAtMost(int maxLength) throws ValidationException {
        if (value.length() > maxLength) {
            throw new ValidationException()
                    .setActualValuesName("length of " + name)
                    .setActualValue(value.length())
                    .setValuesExtraInfo(extraInfo)
                    .setExpectation("is at most")
                    .setExpectedValue(maxLength);
        }
        return this;
    }

    @Override
    public StringValidator bytesAtMost(int maxBytes, Encoding encoding) throws ValidationException {
        final long numBytes = countBytes(encoding);
        if (numBytes > maxBytes) {
            final String valuesName = String.format("number of bytes in %s (as %s)", name, encoding.getBeautyName());
            throw new ValidationException()
                    .setActualValuesName(valuesName)
                    .setActualValue(numBytes)
                    .setValuesExtraInfo(extraInfo)
                    .setExpectation("is at most")
                    .setExpectedValue(maxBytes);
        }
        return this;
    }

    @Override
    public StringValidator codePointsAtMost(int maxCodepoints) throws ValidationException {
        final long numCodepoints = countCodepoints();
        if (numCodepoints > maxCodepoints) {
            final String valuesName = String.format("number of code points in %s", name);
            throw new ValidationException()
                    .setActualValuesName(valuesName)
                    .setActualValue(numCodepoints)
                    .setValuesExtraInfo(extraInfo)
                    .setExpectation("is at most")
                    .setExpectedValue(maxCodepoints);
        }
        return this;
    }

    @Override
    public StringValidator validUrl() throws ValidationException {
        try {
            new URL(value);
            return this;
        } catch (MalformedURLException e) {
            throw newExceptionWithBasics()
                    .setExpectation("is valid URL");
        }
    }

    @Override
    public StringValidator validUuid() throws ValidationException {
        try {
            //noinspection ResultOfMethodCallIgnored
            UUID.fromString(value);
            return this;
        } catch (IllegalArgumentException e) {
            throw newExceptionWithBasics()
                    .setExpectation("is valid UUID");
        }
    }

    @Override
    public StringValidator matches(Pattern pattern) throws ValidationException {
        return matches(pattern, null);
    }

    @Override
    public StringValidator matches(Pattern pattern, String otherName) throws ValidationException {
        final Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            throw newExceptionWithBasics()
                    .setExpectation("matches regex")
                    .setExpectedValue(pattern.pattern())
                    .setExpectedValuesName(otherName);
        }
        return this;
    }

    @Override
    public StringValidator passes(Predicate<String> predicate, String expectation) throws ValidationException {
        if (!predicate.test(value)) {
            throw newExceptionWithBasics()
                    .setExpectation(expectation + " (but is not)");
        }
        return this;
    }

    private long countBytes(Encoding encoding) {
        switch (encoding) {
            case UTF_8: return value.getBytes(StandardCharsets.UTF_8).length;
            // take care: when UTF_16 is chosen, 'getBytes' prepends a BOM thus giving the wrong number of bytes
            case UTF_16: return value.getBytes(StandardCharsets.UTF_16BE).length;
            case UTF_32: return countCodepoints() * 4;
        }
        throw new IllegalStateException("Unknown encoding encountered");
    }

    private long countCodepoints() {
        return value.codePoints().count();
    }

    private ValidationException newExceptionWithBasics() {
        return new ValidationException()
                .setActualValuesName(name)
                .setActualValue(value)
                .setValuesExtraInfo(extraInfo);
    }
}
