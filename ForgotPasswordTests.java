package tests;

import business.UserAccountManager;
import junit.framework.TestCase;

public class ForgotPasswordTests extends TestCase{

	private UserAccountManager userAccountManager;
	
	protected void setUp() throws Exception {
		super.setUp();
		userAccountManager = new UserAccountManager();
		userAccountManager.registerNewUser("jake","J@kepass1","J@kepass1","Jake","Halopoff","jakehalopoff@gmail.com","2087213233");
	}	
	
	public void testForgotUserPassword() {
		assertEquals("That username is invalid. Please try again.", userAccountManager.forgotPassword("bobiscool"));
		assertEquals("That username does not exist. Please try again.", userAccountManager.forgotPassword("Bob88!!"));
		assertEquals(UserAccountManager.NOINPUTERROR, userAccountManager.forgotPassword("jake"));			
	}	
}
