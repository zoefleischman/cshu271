package tests;

import business.*;
import junit.framework.TestCase;

public class RegisterNewUserTests extends TestCase{
	
	private UserAccount userAccount;
	
	protected void setUp() throws Exception {
		super.setUp();
		userAccount = new UserAccount();
	}
	
	public void testRegisterNewUser() {
		assertTrue(userAccount.re)
	}
	
	public void testValidUserName() {
		assertTrue(UserAccount.isUserNameValid("jakehalopoff"));
		assertFalse(UserAccount.isUserNameValid("")); //Blank field.
		assertFalse(UserAccount.isUserNameValid("@jakehalopoff")); //Must start with a letter.
		assertFalse(UserAccount.isUserNameValid("jakehalopoff!")); //Must consist of only letters and digits. 
		assertFalse(UserAccount.isUserNameValid("jake halopoff")); //No space character.
	}
	
	public void testValidFirstName() {
		assertTrue(UserAccount.isFirstNameValid("Jake"));
		assertFalse(UserAccount.isFirstNameValid("@jake"));
		assertFalse(UserAccount.isFirstNameValid("ja ke"));
		assertFalse(UserAccount.isFirstNameValid("Jake1"));
	}
	
	public void testValidLastName() {
		assertTrue(UserAccount.isLastNameValid("Halopoff"));
		assertFalse(UserAccount.isLastNameValid("@Halopoff" ));
		assertFalse(UserAccount.isLastNameValid("Halo poff"));
		assertFalse(UserAccount.isLastNameValid("Halopoff1"));
	}
	
	/**
	 * Tests the validity checker for email addresses on valid values (checks syntax only)
	 */
	public void testValidEmail() {
		assertTrue(UserAccount.isEmailValid("accountmanager@example.com"));		
		assertTrue(UserAccount.isEmailValid("username@boisestate.edu"));
	}
	
	/**
	 * Tests the validity checker for email addresses on invalid values (checks syntax only)
	 */
	public void testInvalidEmail() {
		assertFalse(UserAccount.isEmailValid("")); //field left blank
		assertFalse(UserAccount.isEmailValid("geoffreymeier.u.boisestate.edu")); //no '@' symbol
		assertFalse(UserAccount.isEmailValid("this.address^is not'valid@example.com")); //contains spaces and invalid characters
	}
	
	/**
	 * Tests the validity checker for phone numbers on valid values (checks syntax, digits only)
	 */
	public void testValidPhone() {
		assertTrue(UserAccount.isPhoneNumberValid("8005555555"));
		assertTrue(UserAccount.isPhoneNumberValid("2084261000"));
	}
	
	/**
	 * Tests the validity checker for phone numbers on invalid values (checks syntax, digits only)
	 */
	public void testInvalidPhone() {
		assertFalse(UserAccount.isPhoneNumberValid("")); //field left blank
		assertFalse(UserAccount.isPhoneNumberValid("208426100")); //number too short
		assertFalse(UserAccount.isPhoneNumberValid("80055555555")); //number too long
		assertFalse(UserAccount.isPhoneNumberValid("800abc5000")); //contains letters
		assertFalse(UserAccount.isPhoneNumberValid("(800) 555-5555")); //contains parentheses, space, and dash
		assertFalse(UserAccount.isPhoneNumberValid("208!426%1*%$")); //contains symbols
	}
	
}
