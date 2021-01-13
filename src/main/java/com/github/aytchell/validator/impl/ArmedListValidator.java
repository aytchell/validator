package com.github.aytchell.validator.impl;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.ListValidator;

import java.util.List;

class ArmedListValidator<E> extends ArmedCollectionValidator<E, ListValidator<E>> implements ListValidator<E> {
    ArmedListValidator(List<E> value, String name, String extraInfo) {
        super("List", value, name, extraInfo);
    }

    @Override
    protected ArmedListValidator<E> getValidator() {
        return this;
    }

    @Override
    protected String compileNameOfElement(int index, String entryName) {
        final String listName = getName();
        if (listName != null) {
            return String.format("%s[%d].%s", listName, index, entryName);
        } else {
            return String.format("List[%d].%s", index, entryName);
        }
    }
}
