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
    public VALIDATOR notEmpty() throws ValidationException {
        if (value.isEmpty()) {
            throw newExceptionWithNameAndType()
                    .setExpectation("not empty");
        }
        return getValidator();
    }

    public VALIDATOR misses(TYPE key) throws ValidationException {
        if (value.contains(key)) {
            throw newExceptionWithNameAndType()
                    .setExpectation("misses")
                    .setExpectedValue(key.toString());
        }
        return getValidator();
    }

    public VALIDATOR contains(TYPE key) throws ValidationException {
        if (!value.contains(key)) {
            throw newExceptionWithNameAndType()
                    .setExpectation("contains")
                    .setExpectedValue(key.toString());
        }
        return getValidator();
    }

    public VALIDATOR sizeAtLeast(int minNumberOfElements)
            throws ValidationException {
        if (value.size() < minNumberOfElements) {
            throw new ValidationException()
                    .setActualValue(String.valueOf(value.size()))
                    .setActualValuesName("size of " + getName())
                    .setExpectation("is at least")
                    .setExpectedValue(String.valueOf(minNumberOfElements));
        }
        return getValidator();
    }

    public VALIDATOR sizeAtMost(int maxNumberOfElements)
            throws ValidationException {
        if (value.size() > maxNumberOfElements) {
            throw new ValidationException()
                    .setActualValue(String.valueOf(value.size()))
                    .setActualValuesName("size of " + getName())
                    .setExpectation("is at most")
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
