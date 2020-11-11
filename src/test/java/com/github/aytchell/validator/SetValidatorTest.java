package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static com.github.aytchell.validator.ExceptionMessageCheck.assertThrowsAndMessageContains;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SetValidatorTest {
    @Test
    void isNullGivenNullThrows() {
        final Set<String> nullSet = null;

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(nullSet, "nullSet").isNull(),
                List.of("nullSet", "is missing"));
    }

    @Test
    void isEmptyGivenFilledSetPasses() throws ValidationException {
        final Set<Integer> filledSet = Set.of(1, 2, 3);
        final Set<String> nullSet = null;

        Validator.throwIf(filledSet, "filledSet").isNull().isEmpty();

        Validator.throwIf(filledSet, "filledSet").isNotNullAnd().isEmpty();
        Validator.throwIf(nullSet, "nullSet").isNotNullAnd().isEmpty();
    }

    @Test
    void isEmptyGivenEmptySetThrows() {
        final Set<Long> emptySet = Set.of();

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(emptySet, "emptySet").isNull().isEmpty(),
                List.of("Set", "emptySet", "must not be empty"));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(emptySet, "emptySet").isNotNullAnd().isEmpty(),
                List.of("Set", "emptySet", "must not be empty"));
    }

    @Test
    void containsLessThanWithManyEntriesPasses() throws ValidationException {
        final Set<Integer> bigSet = Set.of(1, 2, 3, 4, 5, 6, 7);
        final Set<Integer> nullSet = null;

        Validator.throwIf(bigSet, "bigSet").isNull().containsLessThan(7);

        Validator.throwIf(bigSet, "bigSet").isNotNullAnd().containsLessThan(7);
        Validator.throwIf(nullSet, "nullSet").isNotNullAnd().containsLessThan(20);
    }

    @Test
    void containsLessThanWithFewEntriesThrows() {
        final Set<String> smallSet = Set.of("1", "2", "3", "4", "5", "6", "7");
        final String setSize = "7";

        assertEquals(setSize, String.valueOf(smallSet.size()));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(smallSet, "smallSet").isNull().containsLessThan(20),
                List.of("Set", "smallSet", "must contain at least", "20", setSize));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(smallSet, "smallSet").isNotNullAnd().containsLessThan(20),
                List.of("Set", "smallSet", "must contain at least", "20", setSize));
    }

    @Test
    void containsMoreThanWithFewEntriesPasses() throws ValidationException {
        final Set<Long> smallSet = Set.of(1L, 2L, 3L, 4L);
        final Set<Long> nullSet = null;

        Validator.throwIf(smallSet, "smallSet").isNull().containsMoreThan(4);

        Validator.throwIf(smallSet, "smallSet").isNotNullAnd().containsMoreThan(7);
        Validator.throwIf(nullSet, "nullSet").isNotNullAnd().containsMoreThan(2);
    }

    @Test
    void containsMoreThanWithFewEntriesThrows() {
        final Set<String> bigSet = Set.of("1", "2", "3", "4", "5", "6", "7", "8");
        final String setSize = "8";

        assertEquals(setSize, String.valueOf(bigSet.size()));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(bigSet, "bigSet").isNull().containsMoreThan(7),
                List.of("Set", "bigSet", "must not contain more than", "7", setSize));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(bigSet, "bigSet").isNotNullAnd().containsMoreThan(2),
                List.of("Set", "bigSet", "must not contain more than", "2", setSize));
    }

    @Test
    void isKeyContainedWhileMissingPasses() throws ValidationException {
        final Set<Long> longSet = Set.of(1L, 2L, 3L, 5L, 8L, 13L, 21L);
        final Set<String> stringSet = Set.of("one", "two", "three", "five", "eight", "and so on");
        final Set<Integer> nullSet = null;

        Validator.throwIf(longSet, "longSet").isNull().isKeyContained(6L);
        Validator.throwIf(stringSet, "stringSet").isNull().isKeyContained("six");
        Validator.throwIf(nullSet, "nullSet").isNotNullAnd().isKeyContained(2020);
    }

    @Test
    void isKeyContainedWhileItIsThrows() {
        final Set<Long> longSet = Set.of(1L, 2L, 3L, 5L, 6L, 8L, 13L, 21L);
        final Set<String> stringSet = Set.of("one", "two", "three", "five", "six", "eight", "and so on");

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(longSet, "longSet").isNotNullAnd().isKeyContained(6L),
                List.of("Set", "longSet", "must not contain", "6"));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(stringSet, "stringSet").isNotNullAnd().isKeyContained("six"),
                List.of("Set", "stringSet", "must not contain", "six"));
    }

    @Test
    void isKeyMissingWhileContainedPasses() throws ValidationException {
        final Set<Long> longSet = Set.of(1L, 2L, 3L, 5L, 8L, 13L, 21L);
        final Set<String> stringSet = Set.of("one", "two", "three", "five", "eight", "and so on");
        final Set<Integer> nullSet = null;

        Validator.throwIf(longSet, "longSet").isNull().isKeyMissing(5L);
        Validator.throwIf(stringSet, "stringSet").isNull().isKeyMissing("five");
        Validator.throwIf(nullSet, "nullSet").isNotNullAnd().isKeyMissing(2020);
    }

    @Test
    void isKeyMissingWhileItIsThrows() {
        final Set<Long> longSet = Set.of(1L, 2L, 3L, 8L, 13L, 21L);
        final Set<String> stringSet = Set.of("one", "two", "three", "eight", "and so on");

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(longSet, "longSet").isNotNullAnd().isKeyMissing(5L),
                List.of("Set", "longSet", "must contain", "5"));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(stringSet, "stringSet").isNotNullAnd().isKeyMissing("five"),
                List.of("Set", "stringSet", "must contain", "five"));
    }
}
