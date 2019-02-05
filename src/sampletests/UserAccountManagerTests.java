package sampletests;

import business.UserAccountManager;
import junit.framework.TestCase;

public class UserAccountManagerTests extends TestCase {

	private UserAccountManager userAccountManager;
	
	protected void setUp() throws Exception {
		super.setUp();
		userAccountManager = new UserAccountManager();
		userAccountManager.registerNewUser("admin", "@BSUccp251", "@BSUccp251", "Dianxiang", "Xu", "dianxiangxu@boisestate.edu", "2084265734");
	}

	public void testExistingUserAccount() {
		assertTrue(userAccountManager.doesUserNameExist("admin"));
		assertNotNull(userAccountManager.login("admin", "@BSUccp251"));
	}

	public void testNonExistantUserAccount() {
		assertFalse(userAccountManager.doesUserNameExist("BSU"));
		assertNull(userAccountManager.login("admin", "hello"));
	}

}
