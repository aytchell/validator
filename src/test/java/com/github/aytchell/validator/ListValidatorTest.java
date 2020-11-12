package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.aytchell.validator.ExceptionMessageCheck.assertThrowsAndMessageContains;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListValidatorTest {
    @Test
    void isNullGivenNullThrows() {
        final List<String> nullList = null;

        assertThrowsAndMessageContains(
                () -> Validator.expect(nullList, "nullList").notNull(),
                List.of("nullList", "is missing"));
    }

    @Test
    void isEmptyGivenFilledListPasses() throws ValidationException {
        final List<Integer> filledList = List.of(1, 2, 3, 2, 1);
        final List<String> nullList = null;

        Validator.expect(filledList, "filledList").notNull().notEmpty();

        Validator.expect(filledList, "filledList").ifNotNull().notEmpty();
        Validator.expect(nullList, "nullList").ifNotNull().notEmpty();
    }

    @Test
    void isEmptyGivenEmptyListThrows() {
        final List<Long> emptyList = List.of();

        assertThrowsAndMessageContains(
                () -> Validator.expect(emptyList, "emptyList").notNull().notEmpty(),
                List.of("List", "emptyList", "must not be empty"));

        assertThrowsAndMessageContains(
                () -> Validator.expect(emptyList, "emptyList").ifNotNull().notEmpty(),
                List.of("List", "emptyList", "must not be empty"));
    }

    @Test
    void containsLessThanWithManyEntriesPasses() throws ValidationException {
        final List<Integer> bigList = List.of(1, 2, 3, 3, 3, 6, 7);
        final List<Integer> nullList = null;

        Validator.expect(bigList, "bigList").notNull().sizeAtLeast(7);

        Validator.expect(bigList, "bigList").ifNotNull().sizeAtLeast(7);
        Validator.expect(nullList, "nullList").ifNotNull().sizeAtLeast(20);
    }

    @Test
    void containsLessThanWithFewEntriesThrows() {
        final List<String> smallList = List.of("1", "2", "3", "3", "3", "6", "7");
        final String setSize = "7";

        assertEquals(setSize, String.valueOf(smallList.size()));

        assertThrowsAndMessageContains(
                () -> Validator.expect(smallList, "smallList").notNull().sizeAtLeast(20),
                List.of("List", "smallList", "must contain at least", "20", setSize));

        assertThrowsAndMessageContains(
                () -> Validator.expect(smallList, "smallList").ifNotNull().sizeAtLeast(20),
                List.of("List", "smallList", "must contain at least", "20", setSize));
    }

    @Test
    void containsMoreThanWithFewEntriesPasses() throws ValidationException {
        final List<Long> smallList = List.of(1L, 2L, 3L, 4L);
        final List<Long> nullList = null;

        Validator.expect(smallList, "smallList").notNull().sizeAtMost(4);

        Validator.expect(smallList, "smallList").ifNotNull().sizeAtMost(7);
        Validator.expect(nullList, "nullList").ifNotNull().sizeAtMost(2);
    }

    @Test
    void containsMoreThanWithFewEntriesThrows() {
        final List<String> bigList = List.of("1", "2", "3", "4", "5", "6", "7", "8");
        final String setSize = "8";

        assertEquals(setSize, String.valueOf(bigList.size()));

        assertThrowsAndMessageContains(
                () -> Validator.expect(bigList, "bigList").notNull().sizeAtMost(7),
                List.of("List", "bigList", "must not contain more than", "7", setSize));

        assertThrowsAndMessageContains(
                () -> Validator.expect(bigList, "bigList").ifNotNull().sizeAtMost(2),
                List.of("List", "bigList", "must not contain more than", "2", setSize));
    }

    @Test
    void isContainedWhileMissingPasses() throws ValidationException {
        final List<Long> longList = List.of(1L, 2L, 3L, 5L, 8L, 13L, 21L);
        final List<String> stringList = List.of("one", "two", "three", "five", "eight", "and so on");
        final List<Integer> nullList = null;

        Validator.expect(longList, "longList").notNull().containsNot(6L);
        Validator.expect(stringList, "stringList").notNull().containsNot("six");
        Validator.expect(nullList, "nullList").ifNotNull().containsNot(2020);
    }

    @Test
    void isContainedWhileItIsThrows() {
        final List<Long> longList = List.of(1L, 2L, 3L, 5L, 6L, 8L, 13L, 21L);
        final List<String> stringList = List.of("one", "two", "three", "five", "six", "eight", "and so on");

        assertThrowsAndMessageContains(
                () -> Validator.expect(longList, "longList").ifNotNull().containsNot(6L),
                List.of("List", "longList", "must not contain", "6"));

        assertThrowsAndMessageContains(
                () -> Validator.expect(stringList, "stringList").ifNotNull().containsNot("six"),
                List.of("List", "stringList", "must not contain", "six"));
    }

    @Test
    void isMissingWhileContainedPasses() throws ValidationException {
        final List<Long> longList = List.of(1L, 2L, 3L, 5L, 8L, 13L, 21L);
        final List<String> stringList = List.of("one", "two", "three", "five", "eight", "and so on");
        final List<Integer> nullList = null;

        Validator.expect(longList, "longList").notNull().contains(5L);
        Validator.expect(stringList, "stringList").notNull().contains("five");
        Validator.expect(nullList, "nullList").ifNotNull().contains(2020);
    }

    @Test
    void isMissingWhileItIsThrows() {
        final List<Long> longList = List.of(1L, 2L, 3L, 8L, 13L, 21L);
        final List<String> stringList = List.of("one", "two", "three", "eight", "and so on");

        assertThrowsAndMessageContains(
                () -> Validator.expect(longList, "longList").ifNotNull().contains(5L),
                List.of("List", "longList", "must contain", "5"));

        assertThrowsAndMessageContains(
                () -> Validator.expect(stringList, "stringList").ifNotNull().contains("five"),
                List.of("List", "stringList", "must contain", "five"));
    }

    @Test
    void isAnyEntryWithEmptyListGivenPasses() throws ValidationException {
        final List<Integer> integerList = List.of();
        final List<Long> longList = List.of();
        final List<String> stringList = List.of();

        Validator.expect(integerList, "integerList").notNull().eachNumericEntry(
                // this should fail with anything but a non-null empty list
                v -> v.notNull().greaterEqThan(5).lessEqThan(3));

        Validator.expect(longList, "longList").notNull().eachNumericEntry(
                // this should fail with anything but a non-null empty list
                v -> v.notNull().greaterEqThan(5).lessEqThan(3));

        Validator.expect(stringList, "stringList").notNull().eachStringEntry(
                // this should fail with anything but a non-null empty list
                v -> v.notNull().notEmpty().lengthAtMost(0));
    }

    @Test
    void isAnyEntryBelowFiveWithHighEntriesPasses() throws ValidationException {
        final List<Integer> integerList = List.of(67, 56, 45, 34, 23);
        final List<Long> longList = List.of(68L, 69L, 70L, 80L);
        final List<Long> nullList = null;

        Validator.expect(integerList, "integerList").notNull().eachNumericEntry(v -> v.notNull().greaterEqThan(5));
        Validator.expect(longList, "longList").ifNotNull().eachNumericEntry(v -> v.notNull().greaterEqThan(5));
        Validator.expect(nullList, "nullList").ifNotNull().eachNumericEntry(v -> v.notNull().greaterEqThan(5));
    }

    @Test
    void isAnyEntryBelowFiveWithLowEntriesThrows() throws ValidationException {
        final List<Integer> integerList = List.of(67, 56, 45, 34, 23);
        final List<Long> longList = List.of(11L, 12L, 13L, 14L);

        assertThrowsAndMessageContains(
                () -> Validator.expect(integerList, "integerList").notNull()
                        .eachNumericEntry(v -> v.notNull().greaterEqThan(24)),
                List.of("List", "inside", "integerList", "is too small", "24", "23"));

        assertThrowsAndMessageContains(
                () -> Validator.expect(longList, "longList").ifNotNull()
                        .eachNumericEntry(v -> v.notNull().greaterEqThan(12)),
                List.of("List", "inside", "longList", "is too small", "11", "12"));
    }

    @Test
    void isAnyEntryBlankWithFilledStringsGivenPasses() throws ValidationException {
        final List<String> filledList = List.of("hello", "world");

        Validator.expect(filledList, "filledList").notNull().eachStringEntry(
                v -> v.notNull().notBlank());
        Validator.expect(filledList, "filledList").ifNotNull().eachStringEntry(
                v -> v.notNull().lengthAtMost(20));
    }

    @Test
    void isAnyEntryBlankWithBlankStringsGivenThrows() {
        final List<String> blankList = List.of("\t\t\t", "   \n", "\t\n");

        assertThrowsAndMessageContains(
                () -> Validator.expect(blankList, "blankList").notNull().eachStringEntry(
                        v -> v.notNull().notBlank()),
                List.of("List", "inside", "blankList", "must not be blank")
        );

        assertThrowsAndMessageContains(
                () -> Validator.expect(blankList, "blankList").ifNotNull().eachStringEntry(
                        v -> v.notNull().notBlank()),
                List.of("List", "inside", "blankList", "must not be blank")
        );
    }
}
