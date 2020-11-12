package com.github.aytchell.validator.exceptions;

import lombok.Getter;
import lombok.NonNull;

@Getter
// unfortunately lombok can't create "fluent setters" so we have to roll our own
public class ValidationException extends Exception {
    private String customMessage;

    private String typeOfContainerEntry;
    private String actualValuesName;
    private String valuesType;
    private String actualValue;

    private String surroundingContainerType;
    private String surroundingContainerName;

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
        //      <typeOfContainerEntry> '<actualValuesName>' (type: <valuesType>, value: <actualValue>)
        //          in <surroundingContainerType> '<surroundingContainerName>'
        //      <expectation> '<expectedValuesName>' (value: <expectedValue>)
        return String.format("Expecting that %s %s%s",
                buildActualValueString(),
                expectation,
                buildExpectedValueString());
    }

    private String buildActualValueString() {
        // build this kind of string depending on what information is available:
        //      <typeOfContainerEntry> '<actualValuesName>' (type: <valuesType>, value: <actualValue>)
        //          in <surroundingContainerType> '<surroundingContainerName>'
        final String actualValueCoreString = buildActualValueCoreString();

        if (surroundingContainerName != null) {
            // is the surrounding container's name is set, then the other two container infos are set, too
            return String.format("%s %s in %s '%s'", typeOfContainerEntry, actualValueCoreString,
                    surroundingContainerType, surroundingContainerName);
        } else {
            return actualValueCoreString;
        }
    }

    private String buildActualValueCoreString() {
        // build one of these strings depending on what information is available:
        //      '<actualValuesName>' (type: <valuesType>, value: <actualValue>) or
        //      '<actualValuesName>' (type: <valuesType>) or
        //      '<actualValuesName>' (value: <actualValue>) or
        //      '<actualValuesName>' or
        //      '<actualValue>'
        if (actualValuesName != null) {
            if (valuesType != null) {
                if (actualValue != null) {
                    return String.format("'%s' (type: %s, value: %s)", actualValuesName, valuesType, actualValue);
                } else {
                    return String.format("'%s' (type: %s)", actualValuesName, valuesType);
                }
            } else if (actualValue != null) {
                return String.format("'%s' (value: %s)", actualValuesName, actualValue);
            } else {
                return String.format("'%s'", actualValuesName);
            }
        } else {
            // if the name and the value are null ... then we don't provide any hint
            return (actualValue != null) ? actualValue : "(null)";
        }
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

    public ValidationException setExpectedValue(Object expectedValue) {
        if (expectedValue instanceof String) {
            this.expectedValue = String.format("'%s'", expectedValue);
        } else {
            if (expectedValue == null) {
                this.expectedValue = "(null)";
            } else {
                this.expectedValue = String.valueOf(expectedValue);
            }
        }
        return this;
    }

    public ValidationException setExpectedValuesName(String expectedValuesName) {
        this.expectedValuesName = expectedValuesName;
        return this;
    }

    public ValidationException setActualValue(Object actualValue) {
        if (actualValue instanceof String) {
            this.actualValue = String.format("'%s'", actualValue);
        } else {
            if (actualValue == null) {
                this.actualValue = "(null)";
            } else {
                this.actualValue = String.valueOf(actualValue);
            }
        }
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
            @NonNull String containerType, @NonNull String containerName, @NonNull String entryType) {
        this.surroundingContainerType = containerType;
        this.surroundingContainerName = containerName;
        this.typeOfContainerEntry = entryType;
        return this;
    }
}
