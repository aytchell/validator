package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.NONE)
public class Validator {
    public static void throwIfNull(Object value, String parameterName) throws ValidationException {
        if (value == null) {
            throw new ValidationException("Parameter '" + parameterName + "' is missing");
        }
    }

    public static NullableObjectValidator<Integer, LongValidator> throwIf(Integer value, String name) {
        return new NullableObjectValidator<>(value, name, DisarmedLongValidator.getINSTANCE()) {
            @Override
            protected LongValidator createValidator(Integer value, String name) {
                return new ArmedLongValidator(Long.valueOf(value), name);
            }
        };
    }

    public static NullableObjectValidator<Long, LongValidator> throwIf(Long value, String name) {
        return new NullableObjectValidator<>(value, name, DisarmedLongValidator.getINSTANCE()) {
            @Override
            protected LongValidator createValidator(Long value, String name) {
                return new ArmedLongValidator(value, name);
            }
        };
    }

    public static NullableObjectValidator<String, StringValidator> throwIf(String value, String name) {
        return new NullableObjectValidator<>(value, name, DisarmedStringValidator.getINSTANCE()) {
            @Override
            protected StringValidator createValidator(String value, String name) {
                return new ArmedStringValidator(value, name);
            }
        };
    }

    public static NullableNameValueMapValidator throwIfNameValueMap(
        Map<String, String> value, String name) {
        return new NullableNameValueMapValidator(value, name);
    }
}
