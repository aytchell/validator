package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import lombok.Value;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.aytchell.validator.ExceptionMessageCheck.assertThrowsAndMessageReadsLike;

public class CustomValidatorTest {
    @Test
    void passesIfNotNullWithNullWorks() throws ValidationException {
        final Thingy noThing = null;

        Validator.expect(noThing, "noThing").ifNotNull().passes(
                v -> {
                    Validator.expect(noThing.name, "name").notNull().notBlank();
                    Validator.expect(noThing.value, "value").notNull().greaterThan(12).lessThan(2);
                }
        );
    }

    @Test
    void passesWithValidObjectWorks() throws ValidationException {
        final Thingy thing = new Thingy("name", 4711);

        Validator.expect(thing, "thing").notNull().passes(
                v -> {
                    Validator.expect(thing.name, "name").notNull().notBlank();
                    Validator.expect(thing.value, "value").notNull().greaterThan(12);
                }
        );
    }

    @Test
    void passesWithWrongObjectThrows() {
        final Thingy thing = new Thingy("name", 3);

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(thing, "thing").notNull().passes(
                        v -> {
                            Validator.expect(thing.name, "name").notNull().notBlank();
                            Validator.expect(thing.value, "value").notNull().greaterThan(12);
                        }
                ),
                List.of("'thing.value", "value: 3", "is greater than 12"));
    }

    @Test
    void passesWithWrongAnonymousObjectThrows() {
        final Thingy thing = new Thingy("name", 3);

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(thing).notNull().passes(
                        v -> {
                            Validator.expect(thing.name, "name").notNull().notBlank();
                            Validator.expect(thing.value, "value").notNull().greaterThan(12);
                        }
                ),
                List.of("'Object.value", "value: 3", "is greater than 12"));
    }

    @Test
    void passesWithWrongExtraInfoObjectThrows() {
        final Thingy thing = new Thingy("name", 3);

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(thing, "thing", "named thing no 3").notNull().passes(
                        v -> {
                            Validator.expect(thing.name).notNull().notBlank();
                            Validator.expect(thing.value).notNull().greaterThan(12);
                        }
                ),
                List.of("'thing.<3>", "value: 3", "info: (about thing) named thing no 3", "is greater than 12"));
    }

    @Value
    private static class Thingy {
        String name;
        Integer value;
    }
}
