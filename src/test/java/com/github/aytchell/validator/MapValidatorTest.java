package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.github.aytchell.validator.ExceptionMessageCheck.assertThrowsAndMessageContains;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapValidatorTest {
    @Test
    void isNullGivenNullThrows() {
        final Map<Integer, String> nullMap = null;

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(nullMap, "nullMap").isNull(),
                List.of("nullMap", "is missing"));
    }

    @Test
    void isEmptyGivenFilledMapPasses() throws ValidationException {
        final Map<Integer, String> filledMap = Map.of(1, "one", 2, "two", 3, "three");
        final Map<String, Integer> nullMap = null;

        Validator.throwIf(filledMap, "filledMap").isNull().isEmpty();

        Validator.throwIf(filledMap, "filledMap").isNotNullAnd().isEmpty();
        Validator.throwIf(nullMap, "nullMap").isNotNullAnd().isEmpty();
    }

    @Test
    void isEmptyGivenEmptyMapThrows() {
        final Map<Long, Integer> emptyMap = Map.of();

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(emptyMap, "emptyMap").isNull().isEmpty(),
                List.of("Map", "emptyMap", "must not be empty"));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(emptyMap, "emptyMap").isNotNullAnd().isEmpty(),
                List.of("Map", "emptyMap", "must not be empty"));
    }

    @Test
    void containsLessThanWithManyEntriesPasses() throws ValidationException {
        final Map<Integer, Integer> bigMap = Map.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5);
        final Map<Integer, Integer> nullMap = null;

        Validator.throwIf(bigMap, "bigMap").isNull().containsLessThan(5);

        Validator.throwIf(bigMap, "bigMap").isNotNullAnd().containsLessThan(5);
        Validator.throwIf(nullMap, "nullMap").isNotNullAnd().containsLessThan(20);
    }

    @Test
    void containsLessThanWithFewEntriesThrows() {
        final Map<Integer, String> smallMap = Map.of(1, "1", 2, "2", 3, "3", 4, "3",
                5, "3", 6, "6", 7, "7");
        final String mapSize = "7";

        assertEquals(mapSize, String.valueOf(smallMap.size()));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(smallMap, "smallMap").isNull().containsLessThan(8),
                List.of("Map", "smallMap", "must contain at least", "8", mapSize));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(smallMap, "smallMap").isNotNullAnd().containsLessThan(8),
                List.of("Map", "smallMap", "must contain at least", "8", mapSize));
    }

    @Test
    void containsMoreThanWithFewEntriesPasses() throws ValidationException {
        final Map<Integer, Long> smallMap = Map.of(1, 1L, 2, 2L, 3, 3L, 4, 4L);
        final Map<Long, Long> nullMap = null;

        Validator.throwIf(smallMap, "smallMap").isNull().containsMoreThan(4);

        Validator.throwIf(smallMap, "smallMap").isNotNullAnd().containsMoreThan(7);
        Validator.throwIf(nullMap, "nullMap").isNotNullAnd().containsMoreThan(2);
    }

    @Test
    void containsMoreThanWithFewEntriesThrows() {
        final Map<String, Integer> bigMap = Map.of("1", 1, "2", 2, "3", 3, "4", 4,
                "5", 5, "6", 6, "7", 7, "8", 8);
        final String setSize = "8";

        assertEquals(setSize, String.valueOf(bigMap.size()));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(bigMap, "bigMap").isNull().containsMoreThan(7),
                List.of("Map", "bigMap", "must not contain more than", "7", setSize));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(bigMap, "bigMap").isNotNullAnd().containsMoreThan(2),
                List.of("Map", "bigMap", "must not contain more than", "2", setSize));
    }

    @Test
    void isContainedWhileMissingPasses() throws ValidationException {
        final Map<Long, Integer> longMap = Map.of(1L, 1, 2L, 2, 3L, 3, 5L, 4,
                8L, 5, 13L, 6, 21L, 7);
        final Map<String, Integer> stringMap = Map.of("one", 1, "two", 2, "three", 3,
                "five", 4, "eight", 5, "and so on", 6);
        final Map<Integer, Integer> nullMap = null;

        Validator.throwIf(longMap, "longMap").isNull().isContained(6L);
        Validator.throwIf(stringMap, "stringMap").isNull().isContained("six");
        Validator.throwIf(nullMap, "nullMap").isNotNullAnd().isContained(2020);
    }

    @Test
    void isContainedWhileItIsThrows() {
        final Map<Long, Integer> longMap = Map.of(1L, 1, 2L, 2, 3L, 3, 5L, 4,
                6L, 5, 8L, 6, 13L, 7, 21L, 8);
        final Map<String, Integer> stringMap = Map.of("one", 1, "two", 2, "three", 3,
                "five", 4, "six", 5, "eight", 6, "and so on", 7);

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(longMap, "longMap").isNotNullAnd().isContained(6L),
                List.of("Map", "longMap", "must not contain", "6"));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(stringMap, "stringMap").isNotNullAnd().isContained("six"),
                List.of("Map", "stringMap", "must not contain", "six"));
    }

    @Test
    void isMissingWhileContainedPasses() throws ValidationException {
        final Map<Long, Integer> longMap = Map.of(1L, 1, 2L, 2, 3L, 3, 5L, 4,
                8L, 5, 13L, 6, 21L, 7);
        final Map<String, Integer> stringMap = Map.of("one", 1, "two", 2, "three", 3,
                "five", 4, "eight", 5, "and so on", 6);
        final Map<Integer, Integer> nullMap = null;

        Validator.throwIf(longMap, "longMap").isNull().isMissing(5L);
        Validator.throwIf(stringMap, "stringMap").isNull().isMissing("five");
        Validator.throwIf(nullMap, "nullMap").isNotNullAnd().isMissing(2020);
    }

    @Test
    void isMissingWhileItIsThrows() {
        final Map<Long, Integer> longMap = Map.of(1L, 1, 2L, 2, 3L, 3,
                8L, 4, 13L, 5, 21L, 6);
        final Map<String, Integer> stringMap = Map.of("one", 1, "two", 2,
                "three", 3, "eight", 4, "and so on", 5);

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(longMap, "longMap").isNotNullAnd().isMissing(5L),
                List.of("Map", "longMap", "must contain", "5"));

        assertThrowsAndMessageContains(
                () -> Validator.throwIf(stringMap, "stringMap").isNotNullAnd().isMissing("five"),
                List.of("Map", "stringMap", "must contain", "five"));
    }
}
