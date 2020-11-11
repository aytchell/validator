package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExceptionMessageCheck {
    public static void assertThrowsAndMessageContains(Executable executable, String substring) {
        messageContains(
                assertThrows(ValidationException.class, executable),
                substring
        );
    }

    public static void messageContains(ValidationException e, String substring) {
        messageContains(e.getMessage(), substring);
    }

    public static void assertThrowsAndMessageContains(Executable executable, List<String> substringList) {
        messageContains(
                assertThrows(ValidationException.class, executable),
                substringList
        );
    }

    public static void messageContains(ValidationException e, List<String> substringList) {
        final String message = e.getMessage();
        substringList.forEach(
                s -> messageContains(message, s)
        );
    }

    private static void messageContains(String message, String substring) {
        assertTrue(message.contains(substring),
                String.format("Expected '%s' to be contained in '%s'", substring, message));
    }
}
