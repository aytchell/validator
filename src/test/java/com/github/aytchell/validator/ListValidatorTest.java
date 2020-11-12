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
                () -> Validator.expect(nullList, "nullList").isNull(),
                List.of("nullList", "is missing"));
    }

    @Test
    void isEmptyGivenFilledListPasses() throws ValidationException {
        final List<Integer> filledList = List.of(1, 2, 3, 2, 1);
        final List<String> nullList = null;

        Validator.expect(filledList, "filledList").isNull().isEmpty();

        Validator.expect(filledList, "filledList").isNotNullAnd().isEmpty();
        Validator.expect(nullList, "nullList").isNotNullAnd().isEmpty();
    }

    @Test
    void isEmptyGivenEmptyListThrows() {
        final List<Long> emptyList = List.of();

        assertThrowsAndMessageContains(
                () -> Validator.expect(emptyList, "emptyList").isNull().isEmpty(),
                List.of("List", "emptyList", "must not be empty"));

        assertThrowsAndMessageContains(
                () -> Validator.expect(emptyList, "emptyList").isNotNullAnd().isEmpty(),
                List.of("List", "emptyList", "must not be empty"));
    }

    @Test
    void containsLessThanWithManyEntriesPasses() throws ValidationException {
        final List<Integer> bigList = List.of(1, 2, 3, 3, 3, 6, 7);
        final List<Integer> nullList = null;

        Validator.expect(bigList, "bigList").isNull().containsLessThan(7);

        Validator.expect(bigList, "bigList").isNotNullAnd().containsLessThan(7);
        Validator.expect(nullList, "nullList").isNotNullAnd().containsLessThan(20);
    }

    @Test
    void containsLessThanWithFewEntriesThrows() {
        final List<String> smallList = List.of("1", "2", "3", "3", "3", "6", "7");
        final String setSize = "7";

        assertEquals(setSize, String.valueOf(smallList.size()));

        assertThrowsAndMessageContains(
                () -> Validator.expect(smallList, "smallList").isNull().containsLessThan(20),
                List.of("List", "smallList", "must contain at least", "20", setSize));

        assertThrowsAndMessageContains(
                () -> Validator.expect(smallList, "smallList").isNotNullAnd().containsLessThan(20),
                List.of("List", "smallList", "must contain at least", "20", setSize));
    }

    @Test
    void containsMoreThanWithFewEntriesPasses() throws ValidationException {
        final List<Long> smallList = List.of(1L, 2L, 3L, 4L);
        final List<Long> nullList = null;

        Validator.expect(smallList, "smallList").isNull().containsMoreThan(4);

        Validator.expect(smallList, "smallList").isNotNullAnd().containsMoreThan(7);
        Validator.expect(nullList, "nullList").isNotNullAnd().containsMoreThan(2);
    }

    @Test
    void containsMoreThanWithFewEntriesThrows() {
        final List<String> bigList = List.of("1", "2", "3", "4", "5", "6", "7", "8");
        final String setSize = "8";

        assertEquals(setSize, String.valueOf(bigList.size()));

        assertThrowsAndMessageContains(
                () -> Validator.expect(bigList, "bigList").isNull().containsMoreThan(7),
                List.of("List", "bigList", "must not contain more than", "7", setSize));

        assertThrowsAndMessageContains(
                () -> Validator.expect(bigList, "bigList").isNotNullAnd().containsMoreThan(2),
                List.of("List", "bigList", "must not contain more than", "2", setSize));
    }

    @Test
    void isContainedWhileMissingPasses() throws ValidationException {
        final List<Long> longList = List.of(1L, 2L, 3L, 5L, 8L, 13L, 21L);
        final List<String> stringList = List.of("one", "two", "three", "five", "eight", "and so on");
        final List<Integer> nullList = null;

        Validator.expect(longList, "longList").isNull().isContained(6L);
        Validator.expect(stringList, "stringList").isNull().isContained("six");
        Validator.expect(nullList, "nullList").isNotNullAnd().isContained(2020);
    }

    @Test
    void isContainedWhileItIsThrows() {
        final List<Long> longList = List.of(1L, 2L, 3L, 5L, 6L, 8L, 13L, 21L);
        final List<String> stringList = List.of("one", "two", "three", "five", "six", "eight", "and so on");

        assertThrowsAndMessageContains(
                () -> Validator.expect(longList, "longList").isNotNullAnd().isContained(6L),
                List.of("List", "longList", "must not contain", "6"));

        assertThrowsAndMessageContains(
                () -> Validator.expect(stringList, "stringList").isNotNullAnd().isContained("six"),
                List.of("List", "stringList", "must not contain", "six"));
    }

    @Test
    void isMissingWhileContainedPasses() throws ValidationException {
        final List<Long> longList = List.of(1L, 2L, 3L, 5L, 8L, 13L, 21L);
        final List<String> stringList = List.of("one", "two", "three", "five", "eight", "and so on");
        final List<Integer> nullList = null;

        Validator.expect(longList, "longList").isNull().isMissing(5L);
        Validator.expect(stringList, "stringList").isNull().isMissing("five");
        Validator.expect(nullList, "nullList").isNotNullAnd().isMissing(2020);
    }

    @Test
    void isMissingWhileItIsThrows() {
        final List<Long> longList = List.of(1L, 2L, 3L, 8L, 13L, 21L);
        final List<String> stringList = List.of("one", "two", "three", "eight", "and so on");

        assertThrowsAndMessageContains(
                () -> Validator.expect(longList, "longList").isNotNullAnd().isMissing(5L),
                List.of("List", "longList", "must contain", "5"));

        assertThrowsAndMessageContains(
                () -> Validator.expect(stringList, "stringList").isNotNullAnd().isMissing("five"),
                List.of("List", "stringList", "must contain", "five"));
    }

    @Test
    void isAnyEntryWithEmptyListGivenPasses() throws ValidationException {
        final List<Integer> integerList = List.of();
        final List<Long> longList = List.of();
        final List<String> stringList = List.of();

        Validator.expect(integerList, "integerList").isNull().anyNumericEntry(
                // this should fail with anything but a non-null empty list
                v -> v.isNull().isSmallerThan(5).isGreaterThan(3));

        Validator.expect(longList, "longList").isNull().anyNumericEntry(
                // this should fail with anything but a non-null empty list
                v -> v.isNull().isSmallerThan(5).isGreaterThan(3));

        Validator.expect(stringList, "stringList").isNull().anyStringEntry(
                // this should fail with anything but a non-null empty list
                v -> v.isNull().isEmpty().isLongerThan(0));
    }

    @Test
    void isAnyEntryBelowFiveWithHighEntriesPasses() throws ValidationException {
        final List<Integer> integerList = List.of(67, 56, 45, 34, 23);
        final List<Long> longList = List.of(68L, 69L, 70L, 80L);
        final List<Long> nullList = null;

        Validator.expect(integerList, "integerList").isNull().anyNumericEntry(v -> v.isNull().isSmallerThan(5));
        Validator.expect(longList, "longList").isNotNullAnd().anyNumericEntry(v -> v.isNull().isSmallerThan(5));
        Validator.expect(nullList, "nullList").isNotNullAnd().anyNumericEntry(v -> v.isNull().isSmallerThan(5));
    }

    @Test
    void isAnyEntryBelowFiveWithLowEntriesThrows() throws ValidationException {
        final List<Integer> integerList = List.of(67, 56, 45, 34, 23);
        final List<Long> longList = List.of(11L, 12L, 13L, 14L);

        assertThrowsAndMessageContains(
                () -> Validator.expect(integerList, "integerList").isNull()
                        .anyNumericEntry(v -> v.isNull().isSmallerThan(24)),
                List.of("List", "inside", "integerList", "is too small", "24", "23"));

        assertThrowsAndMessageContains(
                () -> Validator.expect(longList, "longList").isNotNullAnd()
                        .anyNumericEntry(v -> v.isNull().isSmallerThan(12)),
                List.of("List", "inside", "longList", "is too small", "11", "12"));
    }

    @Test
    void isAnyEntryBlankWithFilledStringsGivenPasses() throws ValidationException {
        final List<String> filledList = List.of("hello", "world");

        Validator.expect(filledList, "filledList").isNull().anyStringEntry(
                v -> v.isNull().isBlank());
        Validator.expect(filledList, "filledList").isNotNullAnd().anyStringEntry(
                v -> v.isNull().isLongerThan(20));
    }

    @Test
    void isAnyEntryBlankWithBlankStringsGivenThrows() {
        final List<String> blankList = List.of("\t\t\t", "   \n", "\t\n");

        assertThrowsAndMessageContains(
                () -> Validator.expect(blankList, "blankList").isNull().anyStringEntry(
                        v -> v.isNull().isBlank()),
                List.of("List", "inside", "blankList", "must not be blank")
        );

        assertThrowsAndMessageContains(
                () -> Validator.expect(blankList, "blankList").isNotNullAnd().anyStringEntry(
                        v -> v.isNull().isBlank()),
                List.of("List", "inside", "blankList", "must not be blank")
        );
    }
}
