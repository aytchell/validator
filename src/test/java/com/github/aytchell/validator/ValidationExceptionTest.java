package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// We're testing the exception class itself. We don't want to throw it
@SuppressWarnings("ThrowableNotThrown")
public class ValidationExceptionTest {
    @Test
    void customMessageIsRespected() {
        final ValidationException exception =
                new ValidationException("Hey Hu (In Scheiben)")
                        .setActualValue(25)
                        .setExpectedValue(42)
                        .setExpectation("be equal");

        assertEquals("Hey Hu (In Scheiben)", exception.getMessage());
    }

    @Test
    void gettersAndSettersAreWorkingAsExpected() {
        final ValidationException exception =
                new ValidationException("customMsg")
                        .setActualValuesName("actualName")
                        .setValuesType("valueType")
                        .setValuesExtraInfo("extraInfo")
                        .setSurroundingContainerInfo("type", "name", "entry")
                        .setExpectation("expectation")
                        .setExpectedValuesName("expectedName");

        assertEquals("customMsg", exception.getMessage());
        assertEquals("customMsg", exception.getCustomMessage());
        assertEquals("actualName", exception.getActualValuesName());
        assertEquals("valueType", exception.getValuesType());
        assertEquals("extraInfo", exception.getValuesExtraInfo());
        assertEquals("type", exception.getSurroundingContainerType());
        assertEquals("name", exception.getSurroundingContainerName());
        assertEquals("entry", exception.getTypeOfContainerEntry());
        assertEquals("expectation", exception.getExpectation());
        assertEquals("expectedName", exception.getExpectedValuesName());
    }

    @Test
    void getActualValue() {
        // this setter has some specialties
        final ValidationException exception = new ValidationException();

        exception.setActualValue(null);
        assertEquals("(null)", exception.getActualValue());

        exception.setActualValue(23L);
        assertEquals("23", exception.getActualValue());

        exception.setActualValue(true);
        assertEquals("true", exception.getActualValue());

        // note the extra single quotation marks when a string is given
        exception.setActualValue("value");
        assertEquals("'value'", exception.getActualValue());
    }

    @Test
    void setExpectedValue() {
        // this setter has some specialties
        final ValidationException exception = new ValidationException();

        exception.setExpectedValue(null);
        assertEquals("(null)", exception.getExpectedValue());

        exception.setExpectedValue(451);
        assertEquals("451", exception.getExpectedValue());

        exception.setExpectedValue(false);
        assertEquals("false", exception.getExpectedValue());

        // note the extra single quotation marks when a string is given
        exception.setExpectedValue("nothing");
        assertEquals("'nothing'", exception.getExpectedValue());
    }

    @Test
    void getMessageOnFreshInstanceDoesntCrash() {
        // Ensure that our exception doesn't throw an exception when getMessage() is called
        final String message = new ValidationException().getMessage();
        assertNotNull(message);
    }

    @Test
    void getMessageWhenAllInformationIsGiven() {
        final ValidationException exception =
                new ValidationException()
                        .setActualValue("actualValue")
                        .setActualValuesName("actualValuesName")
                        .setValuesType("valuesType")
                        .setValuesExtraInfo("valuesExtraInfo")
                        .setSurroundingContainerInfo(
                                "containerType", "containerName", "entryType")
                        .setExpectation("expectation")
                        .setExpectedValue("expectedValue")
                        .setExpectedValuesName("expectedValuesName");

        final String expectedMessage = "Expecting that entryType 'actualValuesName' " +
                "(type: valuesType, value: 'actualValue', info: valuesExtraInfo) " +
                "in containerType 'containerName' expectation " +
                "'expectedValuesName' (value: 'expectedValue')";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getMessageWhenAllButContainerInformationIsGiven() {
        final ValidationException exception =
                new ValidationException()
                        .setActualValue("actualValue")
                        .setActualValuesName("actualValuesName")
                        .setValuesType("valuesType")
                        .setValuesExtraInfo("valuesExtraInfo")
                        .setExpectation("expectation")
                        .setExpectedValue("expectedValue")
                        .setExpectedValuesName("expectedValuesName");

        final String expectedMessage = "Expecting that 'actualValuesName' " +
                "(type: valuesType, value: 'actualValue', info: valuesExtraInfo) " +
                "expectation " +
                "'expectedValuesName' (value: 'expectedValue')";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getMessageWhenBasicInformationIsGiven() {
        final ValidationException exception =
                new ValidationException()
                        .setActualValue("actualValue")
                        .setExpectation("expectation")
                        .setExpectedValue("expectedValue");

        final String expectedMessage = "Expecting that 'actualValue' expectation 'expectedValue'";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getMessageWhenOnlyNamesAreGiven() {
        final ValidationException exception =
                new ValidationException()
                        .setActualValuesName("actualValuesName")
                        .setExpectation("expectation")
                        .setExpectedValuesName("expectedValuesName");

        final String expectedMessage = "Expecting that 'actualValuesName' expectation 'expectedValuesName'";

        assertEquals(expectedMessage, exception.getMessage());
    }
}
