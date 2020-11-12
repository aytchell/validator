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
                List.of("nullSet", "is missing"));
    }

    @Test
    void isEmptyGivenFilledSetPasses() throws ValidationException {
        final Set<Integer> filledSet = Set.of(1, 2, 3);
        final Set<String> nullSet = null;

        Validator.expect(filledSet, "filledSet").notNull().isEmpty();

        Validator.expect(filledSet, "filledSet").ifNotNull().isEmpty();
        Validator.expect(nullSet, "nullSet").ifNotNull().isEmpty();
    }

    @Test
    void isEmptyGivenEmptySetThrows() {
        final Set<Long> emptySet = Set.of();

        assertThrowsAndMessageContains(
                () -> Validator.expect(emptySet, "emptySet").notNull().isEmpty(),
                List.of("Set", "emptySet", "must not be empty"));

        assertThrowsAndMessageContains(
                () -> Validator.expect(emptySet, "emptySet").ifNotNull().isEmpty(),
                List.of("Set", "emptySet", "must not be empty"));
    }

    @Test
    void containsLessThanWithManyEntriesPasses() throws ValidationException {
        final Set<Integer> bigSet = Set.of(1, 2, 3, 4, 5, 6, 7);
        final Set<Integer> nullSet = null;

        Validator.expect(bigSet, "bigSet").notNull().containsLessThan(7);

        Validator.expect(bigSet, "bigSet").ifNotNull().containsLessThan(7);
        Validator.expect(nullSet, "nullSet").ifNotNull().containsLessThan(20);
    }

    @Test
    void containsLessThanWithFewEntriesThrows() {
        final Set<String> smallSet = Set.of("1", "2", "3", "4", "5", "6", "7");
        final String setSize = "7";

        assertEquals(setSize, String.valueOf(smallSet.size()));

        assertThrowsAndMessageContains(
                () -> Validator.expect(smallSet, "smallSet").notNull().containsLessThan(20),
                List.of("Set", "smallSet", "must contain at least", "20", setSize));

        assertThrowsAndMessageContains(
                () -> Validator.expect(smallSet, "smallSet").ifNotNull().containsLessThan(20),
                List.of("Set", "smallSet", "must contain at least", "20", setSize));
    }

    @Test
    void containsMoreThanWithFewEntriesPasses() throws ValidationException {
        final Set<Long> smallSet = Set.of(1L, 2L, 3L, 4L);
        final Set<Long> nullSet = null;

        Validator.expect(smallSet, "smallSet").notNull().containsMoreThan(4);

        Validator.expect(smallSet, "smallSet").ifNotNull().containsMoreThan(7);
        Validator.expect(nullSet, "nullSet").ifNotNull().containsMoreThan(2);
    }

    @Test
    void containsMoreThanWithFewEntriesThrows() {
        final Set<String> bigSet = Set.of("1", "2", "3", "4", "5", "6", "7", "8");
        final String setSize = "8";

        assertEquals(setSize, String.valueOf(bigSet.size()));

        assertThrowsAndMessageContains(
                () -> Validator.expect(bigSet, "bigSet").notNull().containsMoreThan(7),
                List.of("Set", "bigSet", "must not contain more than", "7", setSize));

        assertThrowsAndMessageContains(
                () -> Validator.expect(bigSet, "bigSet").ifNotNull().containsMoreThan(2),
                List.of("Set", "bigSet", "must not contain more than", "2", setSize));
    }

    @Test
    void isContainedWhileMissingPasses() throws ValidationException {
        final Set<Long> longSet = Set.of(1L, 2L, 3L, 5L, 8L, 13L, 21L);
        final Set<String> stringSet = Set.of("one", "two", "three", "five", "eight", "and so on");
        final Set<Integer> nullSet = null;

        Validator.expect(longSet, "longSet").notNull().isContained(6L);
        Validator.expect(stringSet, "stringSet").notNull().isContained("six");
        Validator.expect(nullSet, "nullSet").ifNotNull().isContained(2020);
    }

    @Test
    void isContainedWhileItIsThrows() {
        final Set<Long> longSet = Set.of(1L, 2L, 3L, 5L, 6L, 8L, 13L, 21L);
        final Set<String> stringSet = Set.of("one", "two", "three", "five", "six", "eight", "and so on");

        assertThrowsAndMessageContains(
                () -> Validator.expect(longSet, "longSet").ifNotNull().isContained(6L),
                List.of("Set", "longSet", "must not contain", "6"));

        assertThrowsAndMessageContains(
                () -> Validator.expect(stringSet, "stringSet").ifNotNull().isContained("six"),
                List.of("Set", "stringSet", "must not contain", "six"));
    }

    @Test
    void isMissingWhileContainedPasses() throws ValidationException {
        final Set<Long> longSet = Set.of(1L, 2L, 3L, 5L, 8L, 13L, 21L);
        final Set<String> stringSet = Set.of("one", "two", "three", "five", "eight", "and so on");
        final Set<Integer> nullSet = null;

        Validator.expect(longSet, "longSet").notNull().isMissing(5L);
        Validator.expect(stringSet, "stringSet").notNull().isMissing("five");
        Validator.expect(nullSet, "nullSet").ifNotNull().isMissing(2020);
    }

    @Test
    void isMissingWhileItIsThrows() {
        final Set<Long> longSet = Set.of(1L, 2L, 3L, 8L, 13L, 21L);
        final Set<String> stringSet = Set.of("one", "two", "three", "eight", "and so on");

        assertThrowsAndMessageContains(
                () -> Validator.expect(longSet, "longSet").ifNotNull().isMissing(5L),
                List.of("Set", "longSet", "must contain", "5"));

        assertThrowsAndMessageContains(
                () -> Validator.expect(stringSet, "stringSet").ifNotNull().isMissing("five"),
                List.of("Set", "stringSet", "must contain", "five"));
    }

    @Test
    void isAnyEntryWithEmptySetGivenPasses() throws ValidationException {
        final Set<Integer> integerSet = Set.of();
        final Set<Long> longSet = Set.of();
        final Set<String> stringSet = Set.of();

        Validator.expect(integerSet, "integerSet").notNull().anyNumericEntry(
                // this should fail with anything but a non-null empty list
                v -> v.notNull().gtEqThan(5).ltEqThan(3));

        Validator.expect(longSet, "longSet").notNull().anyNumericEntry(
                // this should fail with anything but a non-null empty list
                v -> v.notNull().gtEqThan(5).ltEqThan(3));

        Validator.expect(stringSet, "stringSet").notNull().anyStringEntry(
                // this should fail with anything but a non-null empty list
                v -> v.notNull().isEmpty().isLongerThan(0));
    }

    @Test
    void isAnyEntryBelowFiveWithHighEntriesPasses() throws ValidationException {
        final Set<Integer> integerSet = Set.of(67, 56, 45, 34, 23);
        final Set<Long> longSet = Set.of(68L, 69L, 70L, 80L);
        final Set<Long> nullSet = null;

        Validator.expect(integerSet, "integerSet").notNull().anyNumericEntry(v -> v.notNull().gtEqThan(5));
        Validator.expect(longSet, "longSet").ifNotNull().anyNumericEntry(v -> v.notNull().gtEqThan(5));
        Validator.expect(nullSet, "nullSet").ifNotNull().anyNumericEntry(v -> v.notNull().gtEqThan(5));
    }

    @Test
    void isAnyEntryBelowFiveWithLowEntriesThrows() throws ValidationException {
        final Set<Integer> integerSet = Set.of(67, 56, 45, 34, 23);
        final Set<Long> longSet = Set.of(11L, 12L, 13L, 14L);

        assertThrowsAndMessageContains(
                () -> Validator.expect(integerSet, "integerSet").notNull()
                        .anyNumericEntry(v -> v.notNull().gtEqThan(24)),
                List.of("Set", "inside", "integerSet", "is too small", "24", "23"));

        assertThrowsAndMessageContains(
                () -> Validator.expect(longSet, "longSet").ifNotNull()
                        .anyNumericEntry(v -> v.notNull().gtEqThan(12)),
                List.of("Set", "inside", "longSet", "is too small", "11", "12"));
    }

    @Test
    void isAnyEntryBlankWithFilledStringsGivenPasses() throws ValidationException {
        final Set<String> filledSet = Set.of("hello", "world");

        Validator.expect(filledSet, "filledSet").notNull().anyStringEntry(
                v -> v.notNull().isBlank());
        Validator.expect(filledSet, "filledSet").ifNotNull().anyStringEntry(
                v -> v.notNull().isLongerThan(20));
    }

    @Test
    void isAnyEntryBlankWithBlankStringsGivenThrows() {
        final Set<String> blankSet = Set.of("\t\t\t", "   \n", "\t\n");

        assertThrowsAndMessageContains(
                () -> Validator.expect(blankSet, "blankSet").notNull().anyStringEntry(
                        v -> v.notNull().isBlank()),
                List.of("Set", "inside", "blankSet", "must not be blank")
        );

        assertThrowsAndMessageContains(
                () -> Validator.expect(blankSet, "blankSet").ifNotNull().anyStringEntry(
                        v -> v.notNull().isBlank()),
                List.of("Set", "inside", "blankSet", "must not be blank")
        );
    }
}
