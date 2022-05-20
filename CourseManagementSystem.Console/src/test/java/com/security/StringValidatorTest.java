package com.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringValidatorTest {
    @Test
    public void testWhenPasswordContainsNoWhitespaceUpperAndLowerCaseCharactersAndADigitAndItIsBetween8And64CharactersLongItIsValid() {
        boolean isValid = StringValidator.isValidPassword("passWord1");
        assertTrue(isValid);
    }

    @Test
    public void testWhenPasswordContainsWhitespaceItIsInvalid() {
        boolean isValid = StringValidator.isValidPassword("passW ord1");
        assertFalse(isValid);
    }

    @Test
    public void testWhenPasswordIsMissingALowerCaseCharacterItIsInvalid() {
        boolean isValid = StringValidator.isValidPassword("PASSWORD1");
        assertFalse(isValid);
    }

    @Test
    public void testWhenPasswordIsMissingAnUpperCaseCharacterItIsInvalid() {
        boolean isValid = StringValidator.isValidPassword("password1");
        assertFalse(isValid);
    }

    @Test
    public void testWhenPasswordIsMissingADigitItIsInvalid() {
        boolean isValid = StringValidator.isValidPassword("Password");
        assertFalse(isValid);
    }

    @Test
    public void testWhenPasswordIsLessThan8CharactersLongItIsInvalid() {
        boolean isValid = StringValidator.isValidPassword("Brick2");
        assertFalse(isValid);
    }

    @Test
    public void testWhenPasswordIsMoreThan64CharactersLongItIsInvalid() {
        boolean isValid = StringValidator.isValidPassword("thisIsASuperLongPasswordThatIsLongerThanSixtyFourCharactersLongSoItIsGoingToFail123");
        assertFalse(isValid);
    }

    @Test
    public void testWhenEmailDoesNotContainAnAtCharacter() {
        boolean isValid = StringValidator.isValidEmail("hello world");
        assertFalse(isValid);
    }

    @Test
    public void testWhenAnEmailIsValid() {
        boolean isValid = StringValidator.isValidEmail("foobar@gmail.com");
        assertTrue(isValid);
    }
}
