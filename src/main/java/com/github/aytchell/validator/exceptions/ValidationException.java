package com.github.aytchell.validator.exceptions;

import lombok.Getter;
import lombok.NonNull;

@Getter
// unfortunately lombok can't create "fluent setters" so we have to roll our own
public class ValidationException extends Exception {
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

    @Override
    public String getMessage() {
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
        //      '<actualValues>'
        return "<actualValueCoreString>";
    }

    private Object buildExpectedValueString() {
        return "<expected value>";
    }

    public ValidationException setValuesType(String valuesType) {
        this.valuesType = valuesType;
        return this;
    }

    public ValidationException setExpectedValue(String expectedValue) {
        this.expectedValue = expectedValue;
        return this;
    }

    public ValidationException setExpectedValuesName(String expectedValuesName) {
        this.expectedValuesName = expectedValuesName;
        return this;
    }

    public ValidationException setActualValue(String actualValue) {
        this.actualValue = actualValue;
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
