package com.github.aytchell.validator.impl;

import com.github.aytchell.validator.CollectionValidator;
import com.github.aytchell.validator.CustomValidator;
import com.github.aytchell.validator.LongEntryValidator;
import com.github.aytchell.validator.StringEntryValidator;

abstract class DisarmedCollectionValidator<TYPE, VALIDATOR>
        extends DisarmedContainerValidator<TYPE, VALIDATOR>
        implements CollectionValidator<TYPE, VALIDATOR> {

    @Override
    public VALIDATOR eachNumericEntry(LongEntryValidator validator) {
        return getValidator();
    }

    @Override
    public VALIDATOR eachStringEntry(StringEntryValidator validator) {
        return getValidator();
    }

    @Override
    public VALIDATOR eachCustomEntry(CustomValidator<TYPE> entryValidator) {
        return getValidator();
    }
}
