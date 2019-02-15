package tests;

import business.UserAccount;
import business.UserAccountManager;
import junit.framework.TestCase;

public class UpdateProfileTests extends TestCase{

	private UserAccountManager userAccountManager;
	private UserAccount	existingUser;

	protected void setUp() throws Exception {
		super.setUp();
		userAccountManager = new UserAccountManager();
		// Making accounts
		userAccountManager.registerNewUser("admin", "@BSUccp251", "@BSUccp251", "Dianxiang", "Xu", "dianxiangxu@boisestate.edu", "2084265734");
		userAccountManager.registerNewUser("username", "yourPassw0rd!", "yourPassw0rd!", "Hannah", "Johnson", "hj@gmail.com","2081234567");
		existingUser = userAccountManager.login("admin", "@BSUccp251");
	}
	
	//testing what should be a successful update
	public void testValidUpdate() {
		// testing successful update
		assertEquals((userAccountManager.updateAccountProfile(existingUser, "update", "@NewPassword123", "@NewPassword123", "Name", "Lastname", "zoefleischman@boisestate.edu", "2088833437") ),"");
	}

	// testing all cases of invalid inputs
	public void testInvalidUpdate() {
		//testing existing username
		assertEquals((userAccountManager.updateAccountProfile(existingUser, "username", "@NewPassword123", "@NewPassword123", "Name", "Lastname", "zoefleischman@boisestate.edu", "2088833437") ),"Username already taken.\n");
		
		//testing password matching error
		assertEquals((userAccountManager.updateAccountProfile(existingUser, "update", "@NewPassword123", "@NewPassword124", "Name", "Lastname", "zoefleischman@boisestate.edu", "2088833437") ),"Password and reentered password do not match.\n");
		
		//testing invalid inputs
		assertEquals((userAccountManager.updateAccountProfile(existingUser, "update", "badpassword", "badpassword", "Name", "Lastname", "zoefleischman@boisestate.edu", "2088833437") ),"Invalid password.\n");
		assertEquals((userAccountManager.updateAccountProfile(existingUser, "@jakehalopoff", "@NewPassword123", "@NewPassword123", "Name", "Lastname", "zoefleischman@boisestate.edu", "2088833437") ),"Invalid user name.\n");
		assertEquals((userAccountManager.updateAccountProfile(existingUser, "update", "@NewPassword123", "@NewPassword123", "Name1", "Lastname", "zoefleischman@boisestate.edu", "2088833437") ),"Invalid first name.\n");
		assertEquals((userAccountManager.updateAccountProfile(existingUser, "update", "@NewPassword123", "@NewPassword123", "Name", "Lastname1", "zoefleischman@boisestate.edu", "2088833437") ),"Invalid last name.\n");
		assertEquals((userAccountManager.updateAccountProfile(existingUser, "update", "@NewPassword123", "@NewPassword123", "Name", "Lastname", "zoefleischman.boisestate.edu", "2088833437") ),"Invalid email.\n");
		assertEquals((userAccountManager.updateAccountProfile(existingUser, "update", "@NewPassword123", "@NewPassword123", "Name", "Lastname", "zoefleischman@boisestate.edu", "800abc5000") ),"Invalid phone number.\n");
	}
	
}
