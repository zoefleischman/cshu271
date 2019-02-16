package tests;

import business.*;
import junit.framework.TestCase;

public class RegisterNewUserTests extends TestCase{

	private UserAccountManager userAccountManager;
	private UserAccount userAccount;

	protected void setUp() throws Exception {
		super.setUp();
		userAccountManager = new UserAccountManager();
		userAccount = new UserAccount();
	}

	public void testRegisterNewUser() {
		String registrationResult = userAccountManager.registerNewUser("jake","J@kepass1","J@kepass1","Jake","Halopoff","jakehalopoff@gmail.com","2087213233");

		assertEquals(registrationResult, business.UserAccountManager.NOINPUTERROR);
		userAccount = userAccountManager.login("jake","J@kepass1");
		assertNotNull(userAccount);
		userAccount.getUserName().equalsIgnoreCase("jake");
		userAccount.getEmail().equalsIgnoreCase("jakehalopoff@gmail.com");
		userAccount.getFirstName().equalsIgnoreCase("Jake");
		userAccount.getLastName().equalsIgnoreCase("Halopoff");
		userAccount.getPassword().equalsIgnoreCase("J@kepass1");
		userAccount.getPhoneNumber().equalsIgnoreCase("2087213233");
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
		assertFalse(UserAccount.isFirstNameValid("")); //Blank field.
		assertFalse(UserAccount.isFirstNameValid("@jake")); //Must start with a letter, no special characters.
		assertFalse(UserAccount.isFirstNameValid("ja ke")); //No space character.
		assertFalse(UserAccount.isFirstNameValid("Jake1")); //Letters only.
	}

	public void testValidLastName() {
		assertTrue(UserAccount.isLastNameValid("Halopoff"));
		assertFalse(UserAccount.isLastNameValid("")); //Blank field.
		assertFalse(UserAccount.isLastNameValid("@Halopoff" )); //Must start with a letter.
		assertFalse(UserAccount.isLastNameValid("Halo poff")); //No space character.
		assertFalse(UserAccount.isLastNameValid("Halopoff1")); //Letters only.
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

	public void testIsPasswordValid() {
        //correct password
		assertTrue(UserAccount.isPasswordValid("Bobby'sPassword1"));
        //incorrect password
        assertFalse(UserAccount.isPasswordValid("badpassword"));
        //no number
        assertFalse(UserAccount.isPasswordValid("Bobby'sPassword"));
		//no special char
        assertFalse(UserAccount.isPasswordValid("BobbysPassword1"));
        //no uppercase
        assertFalse(UserAccount.isPasswordValid("bobby'spassword1"));
        //no lowercase
        assertFalse(UserAccount.isPasswordValid("BOBBY'SPASSWORD1"));
		//not 6 char long
        assertFalse(UserAccount.isPasswordValid("B'sP1"));
        //blank password
        assertFalse(UserAccount.isPasswordValid(""));
	}

}
