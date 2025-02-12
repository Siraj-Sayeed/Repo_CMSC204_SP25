
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Siraj Sayeed
 *
 */
public class PasswordCheckerTest_STUDENT {
	
	PasswordCheckerUtility passCheck;

	@Before
	public void setUp() throws Exception {
		passCheck = new PasswordCheckerUtility();
	}

	@After
	public void tearDown() throws Exception {
		passCheck = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		
		assertTrue(passCheck.isValidLength("Hello@1234"));
		
		LengthException e = assertThrows(LengthException.class, () -> passCheck.isValidLength("c"));
		assertEquals("The password must be at least 6 characters long", e.getMessage());
		
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		
		assertTrue(passCheck.hasUpperAlpha("Hello@1234"));
		
		NoUpperAlphaException e = assertThrows(NoUpperAlphaException.class, () -> passCheck.hasUpperAlpha("c"));
		assertEquals("The password must contain at least one uppercase alphabetic character", e.getMessage());
		
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{

		assertTrue(passCheck.hasLowerAlpha("Hello@1234"));
		
		NoLowerAlphaException e = assertThrows(NoLowerAlphaException.class, () -> passCheck.hasLowerAlpha("C"));
		assertEquals("The password must contain at least one lowercase alphabetic character", e.getMessage());
		
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a WeakPasswordException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		assertFalse(passCheck.isWeakPassword("Hello@1234"));
		
		WeakPasswordException e =  assertThrows(WeakPasswordException.class, () -> passCheck.isWeakPassword("Hello@123"));
		assertEquals("The password is OK but weak - it contains fewer than 10 characters", e.getMessage());
		
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		assertTrue(passCheck.NoSameCharInSequence("Hello@1234"));
		
		InvalidSequenceException e = assertThrows(InvalidSequenceException.class, () -> passCheck.NoSameCharInSequence("ccc"));
		assertEquals("The password cannot contain more than two of the same character in sequence", e.getMessage());
		
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		
		assertTrue(passCheck.hasDigit("Hello@1234"));
		
		NoDigitException e = assertThrows(NoDigitException.class, () -> passCheck.hasDigit("c"));
		assertEquals("The password must contain at least one digit", e.getMessage());
		
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		assertTrue(passCheck.isValidPassword("Hello@1234"));
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		
		ArrayList<String> passwords = new ArrayList<String>();
		passwords.add("c");
		passwords.add("cCCCCCc");
		
		ArrayList<String> invalid = passCheck.getInvalidPasswords(passwords);
		
		
		assertEquals((invalid.get(0) + ", " + invalid.get(1)), "c The password must be at least 6 characters long, cCCCCCc The password must contain at least one digit");		
		
		
	}
	
}