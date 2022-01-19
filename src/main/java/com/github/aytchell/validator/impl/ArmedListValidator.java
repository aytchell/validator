package com.github.aytchell.validator.impl;

// Copyright (c) 2020 Hannes Lerchl <hannes.lerchl@aytchell.de>
// SPDX-License-Identifier: Apache-2.0

import com.github.aytchell.validator.ListValidator;
import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.Getter;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;

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
            if (entryName != null) {
                return String.format("%s[%d].%s", listName, index, entryName);
            } else {
                return String.format("%s[%d]", listName, index);
            }
        } else {
            if (entryName != null) {
                return String.format("List[%d].%s", index, entryName);
            } else {
                return String.format("List[%d]", index);
            }
        }
    }

    @Override
    public ListValidator<E> allEntriesAreUnique(BiPredicate<E, E> compare, String entryName,
                                                Function<E, String> toString) throws ValidationException {
        final Collection<E> values = getValue();
        final LinkedList<E> list = new LinkedList<>(values);

        for (int outer = 0; outer < list.size() - 1; ++outer) {
            final E element = list.get(outer);
            final Counter<E> counter = new Counter<>(element, compare);
            for (int inner = outer + 1; inner < list.size(); ++inner) {
                counter.increaseIfEqual(list.get(inner));
            }
            if (counter.getCounter() > 0) {
                throw new ValidationException()
                        .setActualValue(toString.apply(element))
                        .setActualValuesName(compileNameOfElement(outer, entryName))
                        .setExpectation("is unique");
            }
        }
        return this;
    }

    @Override
    public ListValidator<E> allEntriesAreUnique() throws ValidationException {
        return allEntriesAreUnique(Objects::equals, null, Objects::toString);
    }

    private static class Counter<E> {
        @Getter
        int counter = 0;
        final E element;
        final BiPredicate<E, E> compare;

        private Counter(E element, BiPredicate<E, E> compare) {
            this.element = element;
            this.compare = compare;
        }

        void increaseIfEqual(E other) {
            if (compare.test(element, other)) {
                ++counter;
            }
        }
    }
}
