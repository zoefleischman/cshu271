package business;
import java.util.ArrayList;
import java.util.Date;

public class UserAccountManager {
	
	public static final String NOINPUTERROR ="";
	
    private ArrayList<UserAccount> userAccounts;
    
    public UserAccountManager() {
        userAccounts = new ArrayList<UserAccount>();
    }
    
    public String registerNewUser(String userName, String password, String reenteredPassword, 
    		String firstName, String lastName, String email, String phone){
    		String returnMessage = "";
    		boolean duplicate = false;
    		for(UserAccount a : userAccounts) {
    			if(a.getUserName().equalsIgnoreCase(userName)) {
    				duplicate=true;
    			}
    		}
    		if(duplicate) {
    			returnMessage = "Username already taken.\n";
    		}
	    	if(!password.equals(reenteredPassword)){
			returnMessage+="Password and reentered password do not match.\n"
		}
    		returnMessage+=UserAccount.checkInputError(userName, password, firstName, lastName, email, phone);
    	
    		// check if userName, password, firstName, lastName, email, or phone is invalid 
    		// if invalid, return error message
    		// if reenteredPassword does not match password
    		// return an error message;
    		// if userName already exists
    		// return an error message;
    		if(returnMessage.equals("")) {
    			UserAccount newAccount = new UserAccount();
    			setAccountProfile(newAccount, userName, password, firstName, lastName, email, phone);
    			newAccount.setRegistrationDate(new Date());
    			userAccounts.add(newAccount);
    			returnMessage = NOINPUTERROR;
    		}
    	    return returnMessage;
    }
 
    public String updateAccountProfile(UserAccount existingAccount, 
    		String userName, String password, String reenteredPassword, 
    		String firstName, String lastName, String email, String phone){
		
	    	String returnMessage = "";
    		boolean duplicate = false;
    		for(UserAccount a : userAccounts) {
    			if(a.getUserName().equalsIgnoreCase(userName)) {
    				duplicate=true;
    			}
    		}
    		if(duplicate) {
    			returnMessage = "Username already taken.\n";
    		}
	    	if(!password.equals(reenteredPassword)){
			returnMessage+="Password and reentered password do not match.\n"
		}
    		returnMessage+=UserAccount.checkInputError(userName, password, firstName, lastName, email, phone);
    	
	    // check if userName, password, firstName, lastName, email, or phone is invalid 
		// if invalid, return error message
		// if reenteredPassword does not match password
		// return an error message;
    		// if there is no profile change (refer to method hasProfileChanges below), return a message 
		// if userName is changed and the new userName already exists
		// return an error message;
	    if(hasProfileChanges(UserAccount existingAccount, String userName, String password, 
    		String firstName, String lastName, String email, String phone)&&returnMessage==""){
		    
  		setAccountProfile(existingAccount, userName, password, firstName, lastName, email, phone);
  		existingAccount.setLastUpdateDate(new Date());
		    returnMessage=NOINPUTERROR;
	    }
	    return NOINPUTERROR;
    }
    
    //TODO
    // You need to complete this method. 
    // It should be called in method updateAccountProfile
    private boolean hasProfileChanges(UserAccount existingAccount, String userName, String password, 
    		String firstName, String lastName, String email, String phone){
    		// check profile change 
		return true; // you may change this statement if necessary
    }

    private void setAccountProfile(UserAccount userAccount, String userName, String password, String firstName, String lastName, String email, String phone){
		userAccount.setUserName(userName);
		userAccount.setPassword(password);
		userAccount.setFirstName(firstName);
		userAccount.setLastName(lastName);
		userAccount.setEmail(email);
		userAccount.setPhoneNumber(phone);
    }
    
    // return the user account if the given userName and password are correct
    // otherwise null
    public UserAccount login(String userName, String password) {
    		for (UserAccount userAccount: userAccounts)
    			if(userAccount.isValidCredential(userName, password))    
    				return userAccount;   
    		return null;
    }
    
    public boolean doesUserNameExist(String userName){
    		for (UserAccount userAccount: userAccounts)
    			if(userAccount.matchUserName(userName))   
    				return true;   
    		return false;
    }
    //TODO
	// you need to complete this method
 	public String forgotUserName(String email){
		// Check if the given email is valid
		// if not, return an error message
		// otherwise check if there is an account that matches the given email
		// if found, send the user name to the email address
		// otherwise return an error message
		return NOINPUTERROR; // you may change this statement if necessary
   	
    }

    public String forgotPassword(String userName){
		// Check if the given user name is valid
		// if not, return an error message
		// check if there is an account that matches the given user name
		// if found, send the password to the email address
		// otherwise return an error message
    	if (!UserAccount.isUserNameValid(userName))
    		return "That username is invalid. Please try again.";
    	for (UserAccount userAccount: userAccounts) {
    		if (userAccount.matchUserName(userName)) {
    			userAccount.sendEmail(FORGOT_PASSWORD);
    			return "An email containing your password has been sent!";
    		}
    		else
    			return "That username does not exist. Please try again.";
    	}
    }

    
}
