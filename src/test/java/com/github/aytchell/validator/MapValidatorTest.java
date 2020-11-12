package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.aytchell.validator.ExceptionMessageCheck.assertThrowsAndMessageContains;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MapValidatorTest {
    @Test
    void isNullGivenNullThrows() {
        final Map<Integer, String> nullMap = null;

        assertThrowsAndMessageContains(
                () -> Validator.expect(nullMap, "nullMap").isNull(),
                List.of("nullMap", "is missing"));
    }

    @Test
    void isEmptyGivenFilledMapPasses() throws ValidationException {
        final Map<Integer, String> filledMap = Map.of(1, "one", 2, "two", 3, "three");
        final Map<String, Integer> nullMap = null;

        Validator.expect(filledMap, "filledMap").isNull().isEmpty();

        Validator.expect(filledMap, "filledMap").isNotNullAnd().isEmpty();
        Validator.expect(nullMap, "nullMap").isNotNullAnd().isEmpty();
    }

    @Test
    void isEmptyGivenEmptyMapThrows() {
        final Map<Long, Integer> emptyMap = Map.of();

        assertThrowsAndMessageContains(
                () -> Validator.expect(emptyMap, "emptyMap").isNull().isEmpty(),
                List.of("Map", "emptyMap", "must not be empty"));

        assertThrowsAndMessageContains(
                () -> Validator.expect(emptyMap, "emptyMap").isNotNullAnd().isEmpty(),
                List.of("Map", "emptyMap", "must not be empty"));
    }

    @Test
    void containsLessThanWithManyEntriesPasses() throws ValidationException {
        final Map<Integer, Integer> bigMap = Map.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5);
        final Map<Integer, Integer> nullMap = null;

        Validator.expect(bigMap, "bigMap").isNull().containsLessThan(5);

        Validator.expect(bigMap, "bigMap").isNotNullAnd().containsLessThan(5);
        Validator.expect(nullMap, "nullMap").isNotNullAnd().containsLessThan(20);
    }

    @Test
    void containsLessThanWithFewEntriesThrows() {
        final Map<Integer, String> smallMap = Map.of(1, "1", 2, "2", 3, "3", 4, "3",
                5, "3", 6, "6", 7, "7");
        final String mapSize = "7";

        assertEquals(mapSize, String.valueOf(smallMap.size()));

        assertThrowsAndMessageContains(
                () -> Validator.expect(smallMap, "smallMap").isNull().containsLessThan(8),
                List.of("Map", "smallMap", "must contain at least", "8", mapSize));

        assertThrowsAndMessageContains(
                () -> Validator.expect(smallMap, "smallMap").isNotNullAnd().containsLessThan(8),
                List.of("Map", "smallMap", "must contain at least", "8", mapSize));
    }

    @Test
    void containsMoreThanWithFewEntriesPasses() throws ValidationException {
        final Map<Integer, Long> smallMap = Map.of(1, 1L, 2, 2L, 3, 3L, 4, 4L);
        final Map<Long, Long> nullMap = null;

        Validator.expect(smallMap, "smallMap").isNull().containsMoreThan(4);

        Validator.expect(smallMap, "smallMap").isNotNullAnd().containsMoreThan(7);
        Validator.expect(nullMap, "nullMap").isNotNullAnd().containsMoreThan(2);
    }

    @Test
    void containsMoreThanWithFewEntriesThrows() {
        final Map<String, Integer> bigMap = Map.of("1", 1, "2", 2, "3", 3, "4", 4,
                "5", 5, "6", 6, "7", 7, "8", 8);
        final String setSize = "8";

        assertEquals(setSize, String.valueOf(bigMap.size()));

        assertThrowsAndMessageContains(
                () -> Validator.expect(bigMap, "bigMap").isNull().containsMoreThan(7),
                List.of("Map", "bigMap", "must not contain more than", "7", setSize));

        assertThrowsAndMessageContains(
                () -> Validator.expect(bigMap, "bigMap").isNotNullAnd().containsMoreThan(2),
                List.of("Map", "bigMap", "must not contain more than", "2", setSize));
    }

    @Test
    void isContainedWhileMissingPasses() throws ValidationException {
        final Map<Long, Integer> longMap = Map.of(1L, 1, 2L, 2, 3L, 3, 5L, 4,
                8L, 5, 13L, 6, 21L, 7);
        final Map<String, Integer> stringMap = Map.of("one", 1, "two", 2, "three", 3,
                "five", 4, "eight", 5, "and so on", 6);
        final Map<Integer, Integer> nullMap = null;

        Validator.expect(longMap, "longMap").isNull().isContained(6L);
        Validator.expect(stringMap, "stringMap").isNull().isContained("six");
        Validator.expect(nullMap, "nullMap").isNotNullAnd().isContained(2020);
    }

    @Test
    void isContainedWhileItIsThrows() {
        final Map<Long, Integer> longMap = Map.of(1L, 1, 2L, 2, 3L, 3, 5L, 4,
                6L, 5, 8L, 6, 13L, 7, 21L, 8);
        final Map<String, Integer> stringMap = Map.of("one", 1, "two", 2, "three", 3,
                "five", 4, "six", 5, "eight", 6, "and so on", 7);

        assertThrowsAndMessageContains(
                () -> Validator.expect(longMap, "longMap").isNotNullAnd().isContained(6L),
                List.of("Map", "longMap", "must not contain", "6"));

        assertThrowsAndMessageContains(
                () -> Validator.expect(stringMap, "stringMap").isNotNullAnd().isContained("six"),
                List.of("Map", "stringMap", "must not contain", "six"));
    }

    @Test
    void isMissingWhileContainedPasses() throws ValidationException {
        final Map<Long, Integer> longMap = Map.of(1L, 1, 2L, 2, 3L, 3, 5L, 4,
                8L, 5, 13L, 6, 21L, 7);
        final Map<String, Integer> stringMap = Map.of("one", 1, "two", 2, "three", 3,
                "five", 4, "eight", 5, "and so on", 6);
        final Map<Integer, Integer> nullMap = null;

        Validator.expect(longMap, "longMap").isNull().isMissing(5L);
        Validator.expect(stringMap, "stringMap").isNull().isMissing("five");
        Validator.expect(nullMap, "nullMap").isNotNullAnd().isMissing(2020);
    }

    @Test
    void isMissingWhileItIsThrows() {
        final Map<Long, Integer> longMap = Map.of(1L, 1, 2L, 2, 3L, 3,
                8L, 4, 13L, 5, 21L, 6);
        final Map<String, Integer> stringMap = Map.of("one", 1, "two", 2,
                "three", 3, "eight", 4, "and so on", 5);

        assertThrowsAndMessageContains(
                () -> Validator.expect(longMap, "longMap").isNotNullAnd().isMissing(5L),
                List.of("Map", "longMap", "must contain", "5"));

        assertThrowsAndMessageContains(
                () -> Validator.expect(stringMap, "stringMap").isNotNullAnd().isMissing("five"),
                List.of("Map", "stringMap", "must contain", "five"));
    }

    @Test
    void isAnyValueWithEmptyMapGivenPasses() throws ValidationException {
        final Map<Integer, Integer> integerMap = Map.of();
        final Map<Long, Integer> longMap = Map.of();
        final Map<String, Integer> stringMap = Map.of();

        Validator.expect(integerMap, "integerMap").isNull().anyNumericValue(
                // this should fail with anything but a non-null empty list
                v -> v.isNull().isSmallerThan(5).isGreaterThan(3));

        Validator.expect(longMap, "longMap").isNull().anyNumericValue(
                // this should fail with anything but a non-null empty list
                v -> v.isNull().isSmallerThan(5).isGreaterThan(3));

        Validator.expect(stringMap, "stringMap").isNull().anyStringValue(
                // this should fail with anything but a non-null empty list
                v -> v.isNull().isEmpty().isLongerThan(0));
    }

    @Test
    void isAnyValueBelowFiveWithHighEntriesPasses() throws ValidationException {
        final Map<Integer, Integer> integerMap = Map.of(1, 67, 2, 56, 3, 45,
                4, 34, 5, 23);
        final Map<Integer, Long> longMap = Map.of(1, 68L, 2, 69L, 3, 70L, 4, 80L);
        final Map<Integer, Long> nullMap = null;

        Validator.expect(integerMap, "integerMap").isNull().anyNumericValue(v -> v.isNull().isSmallerThan(5));
        Validator.expect(longMap, "longMap").isNotNullAnd().anyNumericValue(v -> v.isNull().isSmallerThan(5));
        Validator.expect(nullMap, "nullMap").isNotNullAnd().anyNumericValue(v -> v.isNull().isSmallerThan(5));
    }

    @Test
    void isAnyValueBelowFiveWithLowEntriesThrows() throws ValidationException {
        final Map<Integer, Integer> integerMap = Map.of(1, 67, 2, 56, 3, 45, 4, 34, 5, 23);
        final Map<Integer, Long> longMap = Map.of(1, 11L, 2, 12L, 3, 13L, 4, 14L);

        assertThrowsAndMessageContains(
                () -> Validator.expect(integerMap, "integerMap").isNull()
                        .anyNumericValue(v -> v.isNull().isSmallerThan(24)),
                List.of("Map", "inside", "integerMap", "is too small", "24", "23"));

        assertThrowsAndMessageContains(
                () -> Validator.expect(longMap, "longMap").isNotNullAnd()
                        .anyNumericValue(v -> v.isNull().isSmallerThan(12)),
                List.of("Map", "inside", "longMap", "is too small", "11", "12"));
    }

    @Test
    void isAnyValueBlankWithFilledStringsGivenPasses() throws ValidationException {
        final Map<Integer, String> filledMap = Map.of(1, "hello", 2, "world");

        Validator.expect(filledMap, "filledMap").isNull().anyStringValue(
                v -> v.isNull().isBlank());
        Validator.expect(filledMap, "filledMap").isNotNullAnd().anyStringValue(
                v -> v.isNull().isLongerThan(20));
    }

    @Test
    void isAnyValueBlankWithBlankStringsGivenThrows() {
        final Map<Integer, String> blankMap = Map.of(1, "\t\t\t", 2, "   \n", 3, "\t\n");

        assertThrowsAndMessageContains(
                () -> Validator.expect(blankMap, "blankMap").isNull().anyStringValue(
                        v -> v.isNull().isBlank()),
                List.of("Map", "inside", "blankMap", "must not be blank")
        );

        assertThrowsAndMessageContains(
                () -> Validator.expect(blankMap, "blankMap").isNotNullAnd().anyStringValue(
                        v -> v.isNull().isBlank()),
                List.of("Map", "inside", "blankMap", "must not be blank")
        );
    }

    @Test
    void isAnyValueCanHandleNull() throws ValidationException {
        final Map<Integer, String> stringMap = new HashMap<>(Map.of(1, "one", 2, "two"));
        stringMap.put(3, null);

        assertEquals(3, stringMap.size());
        assertNull(stringMap.get(3));

        Validator.expect(stringMap, "stringMap").isNull().anyStringValue(
                v -> v.isNotNullAnd().isBlank());
    }

    @Test
    void isAnyValueWillThrowOnNullEntries() throws ValidationException {
        final Map<Integer, Long> longMap = new HashMap<>(Map.of(1, 1001L, 2, 1002L));
        longMap.put(3, null);

        assertEquals(3, longMap.size());
        assertNull(longMap.get(3));

        assertThrowsAndMessageContains(
                () -> Validator.expect(longMap, "longMap").isNull().anyNumericValue(
                        v -> v.isNull().isSmallerThan(500L)),
                List.of("Map", "inside", "longMap", "is missing"));
    }
}
