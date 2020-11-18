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
                    Validator.expect(v.name, "name").notNull().notBlank();
                    Validator.expect(v.value, "value").notNull().greaterThan(12).lessThan(2);
                }
        );
    }

    @Test
    void customValidatorCanCheckEnums() throws ValidationException {
        final Status status = Status.SUCCESS;

        Validator.expect(status, "status").notNull();
    }

    @Test
    void passesWithValidObjectWorks() throws ValidationException {
        final Thingy thing = new Thingy("name", 4711);

        Validator.expect(thing, "thing", "some extra info").notNull().passes(
                v -> {
                    Validator.expect(v.name, "name").notNull().notBlank();
                    Validator.expect(v.value, "value").notNull().greaterThan(12);
                }
        );
    }

    @Test
    void passesWithWrongObjectThrows() {
        final Thingy thing = new Thingy("name", 3);

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(thing, "thing").notNull().passes(
                        v -> {
                            Validator.expect(v.name, "name").notNull().notBlank();
                            Validator.expect(v.value, "value").notNull().greaterThan(12);
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
                            Validator.expect(v.name, "name").notNull().notBlank();
                            Validator.expect(v.value, "value").notNull().greaterThan(12);
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
                            Validator.expect(v.name).notNull().notBlank();
                            Validator.expect(v.value).notNull().greaterThan(12);
                        }
                ),
                List.of("'thing.<3>", "value: 3", "info: (about thing) named thing no 3", "is greater than 12"));
    }

    @Test
    void passesCanHandleMultipleLayeredObjects() {
        final Wrapper wrapper = new Wrapper("wrapper", List.of(
                new Thingy("seven", 7),
                new Thingy("five", 5),
                new Thingy("three", 3)
        ));

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(wrapper, "wrapper").notNull().passes(
                        w -> {
                            Validator.expect(w.name, "name").notNull().notBlank();
                            Validator.expect(w.thing, "thing").notNull().eachCustomEntry(
                                    t -> {
                                        Validator.expect(t.name, "name").notNull().notBlank();
                                        Validator.expect(t.value, "value").notNull().greaterThan(5);
                                    }
                            );
                        }
                ),
                List.of("'wrapper.thing[1].value'", "value: 5", "is greater than 5")
        );
    }

    @Test
    void passesCanHandleRecursiveChecks() {
        final Node root = new Node(1, List.of(
                new Node(2, List.of(
                        new Node(3, List.of()),
                        new Node(4, List.of(
                                new Node(5, null),
                                new Node(6, null)
                        ))
                )),
                new Node(7, List.of(
                        new Node(8, List.of()),
                        new Node(9, List.of(
                                new Node(10, List.of(
                                        new Node(6546, null)
                                )),
                                new Node(12, null)
                        ))
                ))
        ));

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(root, "root").notNull().passes(CustomValidatorTest::isValidTree),
                List.of("'root.childNodes[1].childNodes[1].childNodes[0].childNodes[0].value'", "value: 6546",
                        "is less than 1000"));
    }

    private static void isValidTree(Node root) throws ValidationException {
        Validator.expect(root.value, "value").notNull().lessThan(1000);
        Validator.expect(root.childNodes, "childNodes").ifNotNull().eachCustomEntry(
                CustomValidatorTest::isValidTree);
    }

    private enum Status {
        SUCCESS,
        CLIENT_ERROR,
        INTERNAL_ERROR
    }

    @Value
    private static class Thingy {
        String name;
        Integer value;
    }

    @Value
    private static class Wrapper {
        String name;
        List<Thingy> thing;
    }

    @Value
    private static class Node {
        Integer value;
        List<Node> childNodes;
    }
}
