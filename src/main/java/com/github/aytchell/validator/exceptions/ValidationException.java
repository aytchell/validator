package com.github.aytchell.validator.exceptions;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
// unfortunately lombok can't create "fluent setters" so we have to roll our own
public class ValidationException extends Exception {
    private String customMessage;

    private String typeOfContainerEntry;
    private String actualValuesName;
    private String valuesType;
    private String valuesExtraInfo;
    private String actualValue;

    private String surroundingContainerType;
    private String surroundingContainerName;
    private String surroundingContainersInfo;

    private String expectation;

    private String expectedValue;
    private String expectedValuesName;

    public ValidationException() {
        super();
    }

    public ValidationException(String customMessage) {
        super();
        this.customMessage = customMessage;
    }

    @Override
    public String getMessage() {
        if (customMessage != null) {
            return customMessage;
        }

        // build this kind of string depending on what information is available:
        //      <typeOfContainerEntry> '<actualValuesName>' (type: <valuesType>, value: <actualValue>,
        //          info: <extraInfo>) in <surroundingContainerType> '<surroundingContainerName>'
        //              <expectation> '<expectedValuesName>' (value: <expectedValue>)
        return String.format("Expecting that %s %s%s",
                buildActualValueString(),
                expectation,
                buildExpectedValueString());
    }

    private String buildActualValueString() {
        // build this kind of string depending on what information is available:
        //      <typeOfContainerEntry> '<actualValuesName>'
        //          (type: <valuesType>, value: <actualValue>, info: <extraInfo>)
        //              in <surroundingContainerType> '<surroundingContainerName>'
        final String actualValueCoreString = buildActualValueCoreString();

        if (surroundingContainerName != null) {
            // if the surrounding container's name is set, then container's type and type of entry are set, too
            return String.format("%s %s in '%s' (type: %s%s)",
                    typeOfContainerEntry, actualValueCoreString,
                    surroundingContainerName, surroundingContainerType,
                    (surroundingContainersInfo != null) ? (", info: " + surroundingContainersInfo) : "");
        } else {
            return actualValueCoreString;
        }
    }

    private String buildActualValueCoreString() {
        // build one of these strings depending on what information is available:
        //      '<actualValuesName>' ([type: <valuesType>], [value: <actualValue>], [info: <valuesExtraInfo>]) or
        //      '<actualValuesName>' or
        //      '<actualValue>'
        if (actualValuesName != null) {
            final String info = buildValuesInfo();
            if (info.isEmpty()) {
                return String.format("'%s'", actualValuesName);
            } else {
                return String.format("'%s' (%s)", actualValuesName, info);
            }
        } else {
            // if the name and the value are null ... then we don't provide any hint
            return (actualValue != null) ? actualValue : "(null)";
        }
    }

    private String buildValuesInfo() {
        // build this string depending on what information is available:
        //      ([type: <valuesType>], [value: <actualValue>], [info: <valuesExtraInfo>])
        final List<String> information = new ArrayList<>();
        if (valuesType != null) {
            information.add(String.format("type: %s", valuesType));
        }
        if (actualValue != null) {
            information.add(String.format("value: %s", actualValue));
        }
        if (valuesExtraInfo != null) {
            information.add(String.format("info: %s", valuesExtraInfo));
        }
        return String.join(", ", information);
    }

    private String buildExpectedValueString() {
        // build one of these strings depending on what information is available:
        //      '<expectedValuesName>' (value: <expectedValue>) or
        //      '<expectedValuesName>'
        //      <expectedValue>
        if (expectedValuesName != null) {
            if (expectedValue != null) {
                return String.format(" '%s' (value: %s)", expectedValuesName, expectedValue);
            } else {
                return String.format(" '%s'", expectedValuesName);
            }
        } else {
            // if the name and the value are null ... then we don't provide any hint
            return (expectedValue != null) ? (" " + expectedValue) : "";
        }
    }

    public ValidationException setValuesType(String valuesType) {
        this.valuesType = valuesType;
        return this;
    }

    public ValidationException setValuesExtraInfo(String valuesExtraInfo) {
        this.valuesExtraInfo = valuesExtraInfo;
        return this;
    }

    public ValidationException setExpectedValue(Object expectedValue) {
        this.expectedValue = mangleActualOrExpectedValue(expectedValue);
        return this;
    }

    public ValidationException setExpectedValuesName(String expectedValuesName) {
        this.expectedValuesName = expectedValuesName;
        return this;
    }

    public ValidationException setActualValue(Object actualValue) {
        this.actualValue = mangleActualOrExpectedValue(actualValue);
        return this;
    }

    public ValidationException setActualValuesName(String actualValuesName) {
        this.actualValuesName = actualValuesName;
        return this;
    }

    public ValidationException setExpectation(String expectation) {
        this.expectation = expectation;
        return this;
    }

    public ValidationException setSurroundingContainerInfo(
            @NonNull String containerType, @NonNull String containerName,
            String containerInfo, @NonNull String entryType) {
        this.surroundingContainerType = containerType;
        this.surroundingContainerName = containerName;
        this.surroundingContainersInfo = containerInfo;
        this.typeOfContainerEntry = entryType;
        return this;
    }

    private String mangleActualOrExpectedValue(Object givenValue) {
        if (givenValue == null) {
            return "(null)";
        } else if (givenValue instanceof String) {
            return String.format("'%s'", givenValue);
        } else {
                return String.valueOf(givenValue);
        }
    }
}
