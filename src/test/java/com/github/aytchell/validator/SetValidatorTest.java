package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.Value;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static com.github.aytchell.validator.ExceptionMessageCheck.assertThrowsAndMessageReadsLike;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SetValidatorTest {
    @Test
    void isNullGivenNullThrows() {
        final Set<String> nullSet = null;

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(nullSet, "nullSet", "extras").notNull(),
                List.of("nullSet", "extras", "is not null"));
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

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(emptySet, "emptySet").notNull().notEmpty(),
                List.of("emptySet", "Set", "is not empty"));

        assertThrowsAndMessageReadsLike(
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

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(smallSet, "smallSet").notNull().sizeAtLeast(20),
                List.of("size of smallSet", setSize, "is at least", "20"));

        assertThrowsAndMessageReadsLike(
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

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(bigSet, "bigSet").notNull().sizeAtMost(7),
                List.of("size of bigSet", setSize, "is at most", "7"));

        assertThrowsAndMessageReadsLike(
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

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(longSet, "longSet").ifNotNull().containsNot(6L),
                List.of("longSet", "Set", "contains not", "6"));

        assertThrowsAndMessageReadsLike(
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

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(longSet, "longSet").ifNotNull().contains(5L),
                List.of("longSet", "Set", "contains", "5"));

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(stringSet, "stringSet").ifNotNull().contains("five"),
                List.of("stringSet", "Set", "contains", "five"));
    }

    @Test
    void iteratingTestsCanHandleEmptySet() throws ValidationException {
        final Set<Integer> emptySet = Set.of();

        Validator.expect(emptySet).notNull().eachNumericEntry(v -> v.notNull().greaterThan(1));
        Validator.expect(emptySet).notNull().eachStringEntry(v -> v.notNull().notEmpty());
        Validator.expect(emptySet).notNull().eachCustomEntry(v -> {
            throw new ValidationException("boom");
        });
    }

    @Test
    void eachNumericValueAppliedOnStringsWillThrow() {
        final Set<String> stringSet = Set.of("68", "69");

        assertThrows(ClassCastException.class,
                () -> Validator.expect(stringSet).ifNotNull().eachNumericEntry(
                        v -> v.notNull().greaterEqThan(5)));
    }

    @Test
    void eachStringValueAppliedOnLongWillThrow() {
        final Set<Long> longSet = Set.of(68L, 69L);

        assertThrows(ClassCastException.class,
                () -> Validator.expect(longSet).ifNotNull().eachStringEntry(
                        v -> v.notNull().notBlank()));
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
        Validator.expect(nullSet).ifNotNull().eachNumericEntry(v -> v.notNull().greaterEqThan(5));
    }

    @Test
    void isAnyEntryBelowFiveWithLowEntriesThrows() throws ValidationException {
        final Set<Integer> integerSet = Set.of(67, 56, 45, 34, 23);
        final Set<Long> longSet = Set.of(11L, 12L, 13L, 14L);

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(integerSet, "integerSet").notNull()
                        .eachNumericEntry(v -> v.notNull().greaterEqThan(24)),
                List.of("integerSet.<23>", "is greater or equal", "24"));

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(longSet, "longSet").ifNotNull()
                        .eachNumericEntry(v -> v.notNull().greaterEqThan(12)),
                List.of("longSet.<11>", "is greater or equal", "12"));
    }

    @Test
    void eachNumericEntryWhenThrowsCanHandleMissingName() {
        final Set<Integer> integerSet = Set.of(67, 56, 45, 34, 23);

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(integerSet).notNull()
                        .eachNumericEntry(v -> v.notNull().greaterEqThan(24)),
                List.of("Set.<23>", "value: 23", "is greater or equal", "24"));
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

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(blankSet, "blankSet").notNull().eachStringEntry(
                        v -> v.notNull().notBlank()),
                List.of("blankSet.<", "is not blank")
        );

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(blankSet, "blankSet").ifNotNull().eachStringEntry(
                        v -> v.notNull().notBlank()),
                List.of("blankSet.<", "is not blank")
        );
    }

    @Test
    void eachCustomEntryCorrectThingiesGivenPasses() throws ValidationException {
        final Set<Thingy> thingies = Set.of(
                new Thingy("one", 1),
                new Thingy("two", 2),
                new Thingy("three", 3));

        Validator.expect(thingies, "thingies").notNull().eachCustomEntry(
                e -> {
                    Validator.expect(e.getName(), "name").notNull().notBlank();
                    Validator.expect(e.getValue(), "value").notNull().greaterThan(0);
                });
    }

    @Test
    void eacCustomEntryIfnOtGivenPassesAll() throws ValidationException {
        final Set<Long> nullSet = null;

        Validator.expect(nullSet, "nullSet").ifNotNull().eachCustomEntry(
                v -> { throw new ValidationException("boom"); });
    }

    @Test
    void eachCustomEntryIncorrectThingiesGivenThrows() throws ValidationException {
        final Set<Thingy> thingies = Set.of(
                new Thingy("two", 2),
                new Thingy("one", 1),
                new Thingy("three", 3));

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(thingies, "thingies").notNull().eachCustomEntry(
                        e -> {
                            Validator.expect(e.getName(), "name").notNull().notBlank();
                            Validator.expect(e.getValue(), "value").notNull().greaterThan(1);
                        }),
                List.of("that 'thingies.value' ", "value: 1", "is greater than", "1"));
    }

    @Value
    private static class Thingy {
        String name;
        Integer value;
    }
}
