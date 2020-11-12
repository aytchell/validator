package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class ExceptionMessageCheck {
    public static void assertThrowsAndMessageReadsLike(Executable executable, String substring) {
        assertThrowsAndMessageReadsLike(executable, List.of(substring));
    }

    public static void assertThrowsAndMessageReadsLike(Executable executable, List<String> substringList) {
        messageContains(
                assertThrows(ValidationException.class, executable),
                substringList
        );
    }

    public static void messageContains(ValidationException e, List<String> substringList) {
        final String message = e.getMessage();
        final IndexKeeper startIndex = new IndexKeeper();

        substringList.forEach(
                substring -> {
                    final int newIndex = message.indexOf(substring, startIndex.get());
                    if (newIndex == -1) {
                        if (message.contains(substring)) {
                            fail(String.format("'%s' is contained in '%s' but at the wrong position",
                                    substring, message));
                        } else {
                            fail(String.format("Expected '%s' to be contained in '%s'", substring, message));
                        }
                    }
                    startIndex.set(newIndex + 1);
                }
        );
    }

    private static class IndexKeeper {
        private int index = 0;

        int get() {
            return index;
        }

        void set(int newIndex) {
            this.index = newIndex;
        }
    }
}
