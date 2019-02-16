package tests;

import business.UserAccountManager;
import junit.framework.TestCase;

public class LoginTests extends TestCase{
	private UserAccountManager userAccountManager;

	protected void setUp() throws Exception {
		super.setUp();
		userAccountManager = new UserAccountManager();
		// Making multiple accounts
		userAccountManager.registerNewUser("admin", "@BSUccp251", "@BSUccp251", "Dianxiang", "Xu", "dianxiangxu@boisestate.edu", "2084265734");
		userAccountManager.registerNewUser("zflei", "myPassw0rd!", "myPassw0rd!", "Zoe", "Fleischman", "zflei76@gmail.com","2088833437");
		userAccountManager.registerNewUser("username", "yourPassw0rd!", "yourPassw0rd!", "Hannah", "Johnson", "hj@gmail.com","2081234567");
	}

	public void testSuccessfulLogin() {
		// testing multiple successful logins
		assertNotNull(userAccountManager.login("admin", "@BSUccp251")); 
		assertNotNull(userAccountManager.login("zflei", "myPassw0rd!"));
		assertNotNull(userAccountManager.login("username", "yourPassw0rd!")); 
		}

	public void testUnsuccessfulLogin() {
		// testing correct username wrong password
		assertNull(userAccountManager.login("admin", "hello"));
		assertNull(userAccountManager.login("username", "myPassw0rd"));
		//testing correct password wrong username
		assertNull(userAccountManager.login("notadmin", "@BSUccp251"));
		assertNull(userAccountManager.login("username", "myPassw0rd!"));
		// testing wrong both
		assertNull(userAccountManager.login("notone", "notapassword"));
	}

}
