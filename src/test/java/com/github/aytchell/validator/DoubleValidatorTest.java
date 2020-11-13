package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.aytchell.validator.ExceptionMessageCheck.assertThrowsAndMessageReadsLike;

public class DoubleValidatorTest {
    @Test
    void isNullGivenNullThrows() {
        final Double nullDouble = null;

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(nullDouble, "nullDouble", "important stuff").notNull(),
                List.of("nullDouble", "important stuff", "is not null"));
    }

    @Test
    void greaterThanWithValidExamplesPass() throws ValidationException {
        Validator.expect(Math.PI, "pi").notNull().greaterThan(
                3, "approximation according to the bible");
        Validator.expect(Math.PI, "pi").notNull().greaterThan(-12.34);
    }

    @Test
    void greaterThanWithWrongExampleThrows() {
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(Math.sqrt(2), "sqrt(2)", "thingy").notNull()
                .greaterThan(Math.PI, "pi"),
                List.of("sqrt(2)", "1.41421", "thingy", "is greater than", "pi", "3.14159"));
    }

    @Test
    void lessThanWithValidExamplesPass() throws ValidationException {
        Validator.expect(Math.PI, "pi").notNull().lessThan(
                22.0 / 7.0, "approximation according to ancient egypts");
        Validator.expect(Math.E, "e").notNull().lessThan(3);
    }

    @Test
    void lessThanWithWrongExampleThrows() {
        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(2.4).notNull().lessThan(2.3),
                List.of("2.4", "is smaller than", "2.3"));

        assertThrowsAndMessageReadsLike(
                () -> Validator.expect(Math.sqrt(20), "sqrt(20)", "foo").ifNotNull()
                        .lessThan(Math.PI, "pi"),
                List.of("sqrt(20)", "4.472135", "foo", "is smaller than", "pi", "3.14159"));
    }

    @Test
    void ifNotNullPasseAllTests() throws ValidationException {
        final Double nullDouble = null;

        Validator.expect(nullDouble).ifNotNull().lessThan(4).greaterThan(5);
    }
}
