package tests;

import business.UserAccount;
import junit.framework.TestCase;

public class UserAccountTests extends TestCase {

	private UserAccount userAccount;
	
	protected void setUp() throws Exception {
		super.setUp();
		userAccount = new UserAccount();
	}

	public void testIsPasswordValid() {
        //correct password
		assertTrue(userAccount.isPasswordValid("Bobby'sPassword1"));
        //incorrect password
        assertFalse(userAccount.isPasswordValid("badpassword"));
        //no number
        assertFalse(userAccount.isPasswordValid("Bobby'sPassword"));
		//no special char
        assertFalse(userAccount.isPasswordValid("BobbysPassword1"));
        //no uppercase
        assertFalse(userAccount.isPasswordValid("bobby'spassword1"));
        //no lowercase
        assertFalse(userAccount.isPasswordValid("BOBBY'SPASSWORD1"));
		//not 6 char long
        assertFalse(userAccount.isPasswordValid("B'sP1"));
        //blank password
        assertFalse(userAccount.isPasswordValid(""));
	}
}
