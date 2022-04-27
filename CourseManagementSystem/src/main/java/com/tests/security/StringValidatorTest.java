package com.tests.security;

import com.security.StringValidator;
import org.junit.Assert;
import org.junit.Test;

public class StringValidatorTest {
    @Test
    public void testWhenPasswordContainsNoWhitespaceUpperAndLowerCaseCharactersAndADigitAndItIsBetween8And64CharactersLongItIsValid() {
        boolean isValid = StringValidator.isValidPassword("passWord1");
        Assert.assertTrue(isValid);
    }

    @Test
    public void testWhenPasswordContainsWhitespaceItIsInvalid() {
        boolean isValid = StringValidator.isValidPassword("passW ord1");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testWhenPasswordIsMissingALowerCaseCharacterItIsInvalid() {
        boolean isValid = StringValidator.isValidPassword("PASSWORD1");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testWhenPasswordIsMissingAnUpperCaseCharacterItIsInvalid() {
        boolean isValid = StringValidator.isValidPassword("password1");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testWhenPasswordIsMissingADigitItIsInvalid() {
        boolean isValid = StringValidator.isValidPassword("Password");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testWhenPasswordIsLessThan8CharactersLongItIsInvalid() {
        boolean isValid = StringValidator.isValidPassword("Brick2");
        Assert.assertFalse(isValid);
    }

    @Test
    public void testWhenPasswordIsMoreThan64CharactersLongItIsInvalid() {
        boolean isValid = StringValidator.isValidPassword("thisIsASuperLongPasswordThatIsLongerThanSixtyFourCharactersLongSoItIsGoingToFail123");
        Assert.assertFalse(isValid);
    }
}
