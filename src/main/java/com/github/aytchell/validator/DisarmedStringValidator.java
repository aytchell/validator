package com.github.aytchell.validator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DisarmedStringValidator implements StringValidator {
    @Getter
    private static final DisarmedStringValidator INSTANCE = new DisarmedStringValidator();

    @Override
    public StringValidator isEmpty() {
        return this;
    }

    @Override
    public StringValidator isBlank() {
        return this;
    }

    @Override
    public StringValidator isLongerThan(int maxLength) {
        return this;
    }
}
