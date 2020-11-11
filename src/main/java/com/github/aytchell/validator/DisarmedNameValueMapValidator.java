package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DisarmedNameValueMapValidator implements NameValueMapValidator {
    @Getter
    public static final NameValueMapValidator INSTANCE = new DisarmedNameValueMapValidator();

    @Override
    public NameValueMapValidator isKeyMissing(String key) {
        return this;
    }

    @Override
    public NameValueMapValidator anyValueIsLongerThan(int maxLength) {
        return this;
    }
}
