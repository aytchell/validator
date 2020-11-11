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
                () -> Validator.throwIf(nullList, "nullList").isNull(),
                List.of("nullList", "is missing"));
    }

    @Test
    void isEmptyGivenFilledListPasses() throws ValidationException {
        final List<Integer> filledList = List.of(1, 2, 3, 2, 1);
        final List<String> nullList = null;

        Validator.throwIf(filledList, "filledList").isNull().isEmpty();

        Validator.throwIf(filledList, "filledList").isNotNullAnd().isEmpty();
        Validator.throwIf(nullList, "nullList").isNotNullAnd().isEmpty();
    }

    @Test
    void isEmptyGivenEmptyListThrows() {
        final List<Long> emptyList = List.of();

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(emptyList, "emptyList").isNull().isEmpty(),
                List.of("List", "emptyList", "must not be empty"));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(emptyList, "emptyList").isNotNullAnd().isEmpty(),
                List.of("List", "emptyList", "must not be empty"));
    }

    @Test
    void containsLessThanWithManyEntriesPasses() throws ValidationException {
        final List<Integer> bigList = List.of(1, 2, 3, 3, 3, 6, 7);
        final List<Integer> nullList = null;

        Validator.throwIf(bigList, "bigList").isNull().containsLessThan(7);

        Validator.throwIf(bigList, "bigList").isNotNullAnd().containsLessThan(7);
        Validator.throwIf(nullList, "nullList").isNotNullAnd().containsLessThan(20);
    }

    @Test
    void containsLessThanWithFewEntriesThrows() {
        final List<String> smallList = List.of("1", "2", "3", "3", "3", "6", "7");
        final String setSize = "7";

        assertEquals(setSize, String.valueOf(smallList.size()));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(smallList, "smallList").isNull().containsLessThan(20),
                List.of("List", "smallList", "must contain at least", "20", setSize));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(smallList, "smallList").isNotNullAnd().containsLessThan(20),
                List.of("List", "smallList", "must contain at least", "20", setSize));
    }

    @Test
    void containsMoreThanWithFewEntriesPasses() throws ValidationException {
        final List<Long> smallList = List.of(1L, 2L, 3L, 4L);
        final List<Long> nullList = null;

        Validator.throwIf(smallList, "smallList").isNull().containsMoreThan(4);

        Validator.throwIf(smallList, "smallList").isNotNullAnd().containsMoreThan(7);
        Validator.throwIf(nullList, "nullList").isNotNullAnd().containsMoreThan(2);
    }

    @Test
    void containsMoreThanWithFewEntriesThrows() {
        final List<String> bigList = List.of("1", "2", "3", "4", "5", "6", "7", "8");
        final String setSize = "8";

        assertEquals(setSize, String.valueOf(bigList.size()));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(bigList, "bigList").isNull().containsMoreThan(7),
                List.of("List", "bigList", "must not contain more than", "7", setSize));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(bigList, "bigList").isNotNullAnd().containsMoreThan(2),
                List.of("List", "bigList", "must not contain more than", "2", setSize));
    }

    @Test
    void isContainedWhileMissingPasses() throws ValidationException {
        final List<Long> longList = List.of(1L, 2L, 3L, 5L, 8L, 13L, 21L);
        final List<String> stringList = List.of("one", "two", "three", "five", "eight", "and so on");
        final List<Integer> nullList = null;

        Validator.throwIf(longList, "longList").isNull().isContained(6L);
        Validator.throwIf(stringList, "stringList").isNull().isContained("six");
        Validator.throwIf(nullList, "nullList").isNotNullAnd().isContained(2020);
    }

    @Test
    void isContainedWhileItIsThrows() {
        final List<Long> longList = List.of(1L, 2L, 3L, 5L, 6L, 8L, 13L, 21L);
        final List<String> stringList = List.of("one", "two", "three", "five", "six", "eight", "and so on");

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(longList, "longList").isNotNullAnd().isContained(6L),
                List.of("List", "longList", "must not contain", "6"));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(stringList, "stringList").isNotNullAnd().isContained("six"),
                List.of("List", "stringList", "must not contain", "six"));
    }

    @Test
    void isMissingWhileContainedPasses() throws ValidationException {
        final List<Long> longList = List.of(1L, 2L, 3L, 5L, 8L, 13L, 21L);
        final List<String> stringList = List.of("one", "two", "three", "five", "eight", "and so on");
        final List<Integer> nullList = null;

        Validator.throwIf(longList, "longList").isNull().isMissing(5L);
        Validator.throwIf(stringList, "stringList").isNull().isMissing("five");
        Validator.throwIf(nullList, "nullList").isNotNullAnd().isMissing(2020);
    }

    @Test
    void isMissingWhileItIsThrows() {
        final List<Long> longList = List.of(1L, 2L, 3L, 8L, 13L, 21L);
        final List<String> stringList = List.of("one", "two", "three", "eight", "and so on");

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(longList, "longList").isNotNullAnd().isMissing(5L),
                List.of("List", "longList", "must contain", "5"));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(stringList, "stringList").isNotNullAnd().isMissing("five"),
                List.of("List", "stringList", "must contain", "five"));
    }

    @Test
    void isAnyEntryWithEmptyListGivenPasses() throws ValidationException {
        final List<Integer> integerList = List.of();
        final List<Long> longList = List.of();
        final List<String> stringList = List.of();

        Validator.throwIf(integerList, "integerList").isNull().isAnyNumericEntry(
                // this should fail with anything but a non-null empty list
                v -> v.isNull().isLowerThan(5).isGreaterThan(3));

        Validator.throwIf(longList, "longList").isNull().isAnyNumericEntry(
                // this should fail with anything but a non-null empty list
                v -> v.isNull().isLowerThan(5).isGreaterThan(3));

        Validator.throwIf(stringList, "stringList").isNull().isAnyStringEntry(
                // this should fail with anything but a non-null empty list
                v -> v.isNull().isEmpty().isLongerThan(0));
    }

    @Test
    void isAnyEntryBelowFiveWithHighEntriesPasses() throws ValidationException {
        final List<Integer> integerList = List.of(67, 56, 45, 34, 23);
        final List<Long> longList = List.of(68L, 69L, 70L, 80L);
        final List<Long> nullList = null;

        Validator.throwIf(integerList, "integerList").isNull().isAnyNumericEntry(v -> v.isNull().isLowerThan(5));
        Validator.throwIf(longList, "longList").isNotNullAnd().isAnyNumericEntry(v -> v.isNull().isLowerThan(5));
        Validator.throwIf(nullList, "nullList").isNotNullAnd().isAnyNumericEntry(v -> v.isNull().isLowerThan(5));
    }

    @Test
    void isAnyEntryBelowFiveWithLowEntriesThrows() throws ValidationException {
        final List<Integer> integerList = List.of(67, 56, 45, 34, 23);
        final List<Long> longList = List.of(11L, 12L, 13L, 14L);

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(integerList, "integerList").isNull()
                        .isAnyNumericEntry(v -> v.isNull().isLowerThan(24)),
                List.of("List", "inside", "integerList", "is too small", "24", "23"));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(longList, "longList").isNotNullAnd()
                        .isAnyNumericEntry(v -> v.isNull().isLowerThan(12)),
                List.of("List", "inside", "longList", "is too small", "11", "12"));
    }

    @Test
    void isAnyEntryBlankWithFilledStringsGivenPasses() throws ValidationException {
        final List<String> filledList = List.of("hello", "world");

        Validator.throwIf(filledList, "filledList").isNull().isAnyStringEntry(
                v -> v.isNull().isBlank());
        Validator.throwIf(filledList, "filledList").isNotNullAnd().isAnyStringEntry(
                v -> v.isNull().isLongerThan(20));
    }

    @Test
    void isAnyEntryBlankWithBlankStringsGivenThrows() {
        final List<String> blankList = List.of("\t\t\t", "   \n", "\t\n");

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(blankList, "blankList").isNull().isAnyStringEntry(
                        v -> v.isNull().isBlank()),
                List.of("List", "inside", "blankList", "must not be blank")
        );

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(blankList, "blankList").isNotNullAnd().isAnyStringEntry(
                        v -> v.isNull().isBlank()),
                List.of("List", "inside", "blankList", "must not be blank")
        );
    }
}
