package tests;

import business.*;
import junit.framework.TestCase;

public class ForgotUserNameTests extends TestCase{

	private UserAccountManager userAccountManager;
	
	protected void setUp() throws Exception {
		super.setUp();
		userAccountManager = new UserAccountManager();
		userAccountManager.registerNewUser("jake","j@kepass1","j@kepass1","Jake","Halopoff","jakehalopoff@gmail.com","2087213233");
	}	
		
	public void forgotUserNameTest() {
		assertEquals("This email either does not exist, or was entered incorrectly. Please try again.", userAccountManager.forgotUserName("Bob88@gmail.com"));
		assertEquals(UserAccountManager.NOINPUTERROR, userAccountManager.forgotUserName("jakehalopoff@gmail.com"));			
	}
	
}
