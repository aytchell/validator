package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;

// This class shall only be instantiated by Validator
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter(value = AccessLevel.PROTECTED)
abstract class ArmedContainerValidator<TYPE, VALIDATOR> implements ContainerValidator<TYPE, VALIDATOR> {
    private final String containerType;
    private final Collection<TYPE> value;
    private final String name;

    protected abstract VALIDATOR getValidator();

    @Override
    public VALIDATOR isEmpty() throws ValidationException {
        if (value.isEmpty()) {
            throw newExceptionWithNameAndType()
                    .setExpectation("shall not be empty");
        }
        return getValidator();
    }

    public VALIDATOR isContained(TYPE key) throws ValidationException {
        if (value.contains(key)) {
            throw newExceptionWithNameAndType()
                    .setExpectation("shall not contain")
                    .setExpectedValue(key.toString());
        }
        return getValidator();
    }

    public VALIDATOR isMissing(TYPE key) throws ValidationException {
        if (!value.contains(key)) {
            throw newExceptionWithNameAndType()
                    .setExpectation("shall contain")
                    .setExpectedValue(key.toString());
        }
        return getValidator();
    }

    public VALIDATOR containsLessThan(int minNumberOfElements)
            throws ValidationException {
        if (value.size() < minNumberOfElements) {
            throw newExceptionWithNameAndType()
                    .setExpectation("shall contain more elements than")
                    .setExpectedValue(String.valueOf(minNumberOfElements));
        }
        return getValidator();
    }

    public VALIDATOR containsMoreThan(int maxNumberOfElements)
            throws ValidationException {
        if (value.size() > maxNumberOfElements) {
            throw newExceptionWithNameAndType()
                    .setExpectation("shall not contain more elements than")
                    .setExpectedValue(String.valueOf(maxNumberOfElements));
        }
        return getValidator();
    }

    private ValidationException newExceptionWithNameAndType() {
        return new ValidationException()
                .setValuesType(containerType)
                .setActualValuesName(name);
    }
}
