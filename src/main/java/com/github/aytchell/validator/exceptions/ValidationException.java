package com.github.aytchell.validator.exceptions;

import lombok.Getter;

@Getter
// unfortunately lombok can't create "fluent setters" so we have to roll our own
public class ValidationException extends Exception {
    private String valuesType;
    private String expectedValue;
    private String expectedValuesName;
    private String actualValue;
    private String actualValuesName;

    private String expectation;

    private String surroundingContainerType;
    private String surroundingContainerName;
    private String typeOfContainerEntry;
    private String nameOfContainerEntry;

    public ValidationException() {
        super();
    }

    @Override
    public String getMessage() {
        return "dummy message";
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

    public ValidationException setSurroundingContainerType(String surroundingContainerType) {
        this.surroundingContainerType = surroundingContainerType;
        return this;
    }

    public ValidationException setSurroundingContainerName(String surroundingContainerName) {
        this.surroundingContainerName = surroundingContainerName;
        return this;
    }

    public ValidationException setTypeOfContainerEntry(String typeOfContainerEntry) {
        this.typeOfContainerEntry = typeOfContainerEntry;
        return this;
    }

    public ValidationException setNameOfContainerEntry(String nameOfContainerEntry) {
        this.nameOfContainerEntry = nameOfContainerEntry;
        return this;
    }

}
