package com.github.aytchell.validator;

abstract class DisarmedCollectionValidator<TYPE, VALIDATOR>
        extends DisarmedContainerValidator<TYPE, VALIDATOR>
        implements CollectionValidator<TYPE, VALIDATOR> {

    @Override
    public VALIDATOR isAnyNumericEntry(LongEntryValidator validator) {
        return getValidator();
    }

    @Override
    public VALIDATOR isAnyStringEntry(StringEntryValidator validator) {
        return getValidator();
    }
}
