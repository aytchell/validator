package com.github.aytchell.validator;

abstract class DisarmedCollectionValidator<TYPE, VALIDATOR>
        extends DisarmedContainerValidator<TYPE, VALIDATOR>
        implements CollectionValidator<TYPE, VALIDATOR> {

    @Override
    public VALIDATOR anyNumericEntry(LongEntryValidator validator) {
        return getValidator();
    }

    @Override
    public VALIDATOR anyStringEntry(StringEntryValidator validator) {
        return getValidator();
    }
}
