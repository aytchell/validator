package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.ContainerValidator;
import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter(value = AccessLevel.PROTECTED)
abstract class ArmedContainerValidator<TYPE, VALIDATOR> implements ContainerValidator<TYPE, VALIDATOR> {
    private final String containerType;
    private final Collection<TYPE> value;
    private final String name;
    private final String extraInfo;

    protected abstract VALIDATOR getValidator();

    @Override
    public VALIDATOR notEmpty() throws ValidationException {
        if (value.isEmpty()) {
            throw newExceptionWithBasics()
                    .setExpectation("is not empty");
        }
        return getValidator();
    }

    public VALIDATOR containsNot(TYPE key) throws ValidationException {
        if (value.contains(key)) {
            throw newExceptionWithBasics()
                    .setExpectation("contains not")
                    .setExpectedValue(key);
        }
        return getValidator();
    }

    public VALIDATOR contains(TYPE key) throws ValidationException {
        if (!value.contains(key)) {
            throw newExceptionWithBasics()
                    .setExpectation("contains")
                    .setExpectedValue(key);
        }
        return getValidator();
    }

    public VALIDATOR sizeAtLeast(int minNumberOfElements)
            throws ValidationException {
        if (value.size() < minNumberOfElements) {
            throw new ValidationException()
                    .setActualValue(value.size())
                    .setActualValuesName("size of " + getName())
                    .setValuesExtraInfo(extraInfo)
                    .setExpectation("is at least")
                    .setExpectedValue(minNumberOfElements);
        }
        return getValidator();
    }

    public VALIDATOR sizeAtMost(int maxNumberOfElements)
            throws ValidationException {
        if (value.size() > maxNumberOfElements) {
            throw new ValidationException()
                    .setActualValue(value.size())
                    .setActualValuesName("size of " + getName())
                    .setValuesExtraInfo(extraInfo)
                    .setExpectation("is at most")
                    .setExpectedValue(maxNumberOfElements);
        }
        return getValidator();
    }

    private ValidationException newExceptionWithBasics() {
        return new ValidationException()
                .setValuesType(containerType)
                .setValuesExtraInfo(extraInfo)
                .setActualValuesName(name);
    }
}
