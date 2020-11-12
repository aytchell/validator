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
                () -> Validator.expect(nullSet, "nullSet").notNull(),
                List.of("nullSet", "is not null"));
    }

    @Test
    void isEmptyGivenFilledSetPasses() throws ValidationException {
        final Set<Integer> filledSet = Set.of(1, 2, 3);
        final Set<String> nullSet = null;

        Validator.expect(filledSet, "filledSet").notNull().notEmpty();

        Validator.expect(filledSet, "filledSet").ifNotNull().notEmpty();
        Validator.expect(nullSet, "nullSet").ifNotNull().notEmpty();
    }

    @Test
    void isEmptyGivenEmptySetThrows() {
        final Set<Long> emptySet = Set.of();

        assertThrowsAndMessageContains(
                () -> Validator.expect(emptySet, "emptySet").notNull().notEmpty(),
                List.of("emptySet", "Set", "is not empty"));

        assertThrowsAndMessageContains(
                () -> Validator.expect(emptySet, "emptySet").ifNotNull().notEmpty(),
                List.of("emptySet", "Set", "is not empty"));
    }

    @Test
    void containsLessThanWithManyEntriesPasses() throws ValidationException {
        final Set<Integer> bigSet = Set.of(1, 2, 3, 4, 5, 6, 7);
        final Set<Integer> nullSet = null;

        Validator.expect(bigSet, "bigSet").notNull().sizeAtLeast(7);

        Validator.expect(bigSet, "bigSet").ifNotNull().sizeAtLeast(7);
        Validator.expect(nullSet, "nullSet").ifNotNull().sizeAtLeast(20);
    }

    @Test
    void containsLessThanWithFewEntriesThrows() {
        final Set<String> smallSet = Set.of("1", "2", "3", "4", "5", "6", "7");
        final String setSize = "7";

        assertEquals(setSize, String.valueOf(smallSet.size()));

        assertThrowsAndMessageContains(
                () -> Validator.expect(smallSet, "smallSet").notNull().sizeAtLeast(20),
                List.of("size of smallSet", setSize, "is at least", "20"));

        assertThrowsAndMessageContains(
                () -> Validator.expect(smallSet, "smallSet").ifNotNull().sizeAtLeast(20),
                List.of("size of smallSet", setSize, "is at least", "20"));
    }

    @Test
    void containsMoreThanWithFewEntriesPasses() throws ValidationException {
        final Set<Long> smallSet = Set.of(1L, 2L, 3L, 4L);
        final Set<Long> nullSet = null;

        Validator.expect(smallSet, "smallSet").notNull().sizeAtMost(4);

        Validator.expect(smallSet, "smallSet").ifNotNull().sizeAtMost(7);
        Validator.expect(nullSet, "nullSet").ifNotNull().sizeAtMost(2);
    }

    @Test
    void containsMoreThanWithFewEntriesThrows() {
        final Set<String> bigSet = Set.of("1", "2", "3", "4", "5", "6", "7", "8");
        final String setSize = "8";

        assertEquals(setSize, String.valueOf(bigSet.size()));

        assertThrowsAndMessageContains(
                () -> Validator.expect(bigSet, "bigSet").notNull().sizeAtMost(7),
                List.of("size of bigSet", setSize, "is at most", "7"));

        assertThrowsAndMessageContains(
                () -> Validator.expect(bigSet, "bigSet").ifNotNull().sizeAtMost(2),
                List.of("size of bigSet", setSize, "is at most", "2"));
    }

    @Test
    void isContainedWhileMissingPasses() throws ValidationException {
        final Set<Long> longSet = Set.of(1L, 2L, 3L, 5L, 8L, 13L, 21L);
        final Set<String> stringSet = Set.of("one", "two", "three", "five", "eight", "and so on");
        final Set<Integer> nullSet = null;

        Validator.expect(longSet, "longSet").notNull().containsNot(6L);
        Validator.expect(stringSet, "stringSet").notNull().containsNot("six");
        Validator.expect(nullSet, "nullSet").ifNotNull().containsNot(2020);
    }

    @Test
    void isContainedWhileItIsThrows() {
        final Set<Long> longSet = Set.of(1L, 2L, 3L, 5L, 6L, 8L, 13L, 21L);
        final Set<String> stringSet = Set.of("one", "two", "three", "five", "six", "eight", "and so on");

        assertThrowsAndMessageContains(
                () -> Validator.expect(longSet, "longSet").ifNotNull().containsNot(6L),
                List.of("longSet", "Set", "contains not", "6"));

        assertThrowsAndMessageContains(
                () -> Validator.expect(stringSet, "stringSet").ifNotNull().containsNot("six"),
                List.of("stringSet", "Set", "contains not", "six"));
    }

    @Test
    void isMissingWhileContainedPasses() throws ValidationException {
        final Set<Long> longSet = Set.of(1L, 2L, 3L, 5L, 8L, 13L, 21L);
        final Set<String> stringSet = Set.of("one", "two", "three", "five", "eight", "and so on");
        final Set<Integer> nullSet = null;

        Validator.expect(longSet, "longSet").notNull().contains(5L);
        Validator.expect(stringSet, "stringSet").notNull().contains("five");
        Validator.expect(nullSet, "nullSet").ifNotNull().contains(2020);
    }

    @Test
    void isMissingWhileItIsThrows() {
        final Set<Long> longSet = Set.of(1L, 2L, 3L, 8L, 13L, 21L);
        final Set<String> stringSet = Set.of("one", "two", "three", "eight", "and so on");

        assertThrowsAndMessageContains(
                () -> Validator.expect(longSet, "longSet").ifNotNull().contains(5L),
                List.of("longSet", "Set", "contains", "5"));

        assertThrowsAndMessageContains(
                () -> Validator.expect(stringSet, "stringSet").ifNotNull().contains("five"),
                List.of("stringSet", "Set", "contains", "five"));
    }

    @Test
    void isAnyEntryWithEmptySetGivenPasses() throws ValidationException {
        final Set<Integer> integerSet = Set.of();
        final Set<Long> longSet = Set.of();
        final Set<String> stringSet = Set.of();

        Validator.expect(integerSet, "integerSet").notNull().eachNumericEntry(
                // this should fail with anything but a non-null empty list
                v -> v.notNull().greaterEqThan(5).lessEqThan(3));

        Validator.expect(longSet, "longSet").notNull().eachNumericEntry(
                // this should fail with anything but a non-null empty list
                v -> v.notNull().greaterEqThan(5).lessEqThan(3));

        Validator.expect(stringSet, "stringSet").notNull().eachStringEntry(
                // this should fail with anything but a non-null empty list
                v -> v.notNull().notEmpty().lengthAtMost(0));
    }

    @Test
    void isAnyEntryBelowFiveWithHighEntriesPasses() throws ValidationException {
        final Set<Integer> integerSet = Set.of(67, 56, 45, 34, 23);
        final Set<Long> longSet = Set.of(68L, 69L, 70L, 80L);
        final Set<Long> nullSet = null;

        Validator.expect(integerSet, "integerSet").notNull().eachNumericEntry(v -> v.notNull().greaterEqThan(5));
        Validator.expect(longSet, "longSet").ifNotNull().eachNumericEntry(v -> v.notNull().greaterEqThan(5));
        Validator.expect(nullSet, "nullSet").ifNotNull().eachNumericEntry(v -> v.notNull().greaterEqThan(5));
    }

    @Test
    void isAnyEntryBelowFiveWithLowEntriesThrows() throws ValidationException {
        final Set<Integer> integerSet = Set.of(67, 56, 45, 34, 23);
        final Set<Long> longSet = Set.of(11L, 12L, 13L, 14L);

        assertThrowsAndMessageContains(
                () -> Validator.expect(integerSet, "integerSet").notNull()
                        .eachNumericEntry(v -> v.notNull().greaterEqThan(24)),
                List.of("entry", "23", "in Set", "integerSet", "is greater or equal", "24"));

        assertThrowsAndMessageContains(
                () -> Validator.expect(longSet, "longSet").ifNotNull()
                        .eachNumericEntry(v -> v.notNull().greaterEqThan(12)),
                List.of("entry", "11", "in Set", "longSet", "is greater or equal", "12"));
    }

    @Test
    void isAnyEntryBlankWithFilledStringsGivenPasses() throws ValidationException {
        final Set<String> filledSet = Set.of("hello", "world");

        Validator.expect(filledSet, "filledSet").notNull().eachStringEntry(
                v -> v.notNull().notBlank());
        Validator.expect(filledSet, "filledSet").ifNotNull().eachStringEntry(
                v -> v.notNull().lengthAtMost(20));
    }

    @Test
    void isAnyEntryBlankWithBlankStringsGivenThrows() {
        final Set<String> blankSet = Set.of("\t\t\t", "   \n", "\t\n");

        assertThrowsAndMessageContains(
                () -> Validator.expect(blankSet, "blankSet").notNull().eachStringEntry(
                        v -> v.notNull().notBlank()),
                List.of("entry", "in Set", "blankSet", "is not blank")
        );

        assertThrowsAndMessageContains(
                () -> Validator.expect(blankSet, "blankSet").ifNotNull().eachStringEntry(
                        v -> v.notNull().notBlank()),
                List.of("entry", "in Set", "blankSet", "is not blank")
        );
    }
}
