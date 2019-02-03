package business;

import java.util.Date;

public class UserAccount {
	
	private String userName;
	private String password;
	
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private Date registrationDate;
	private Date lastUpdateDate;
	
	public UserAccount() {	
	}
	
	public static String checkInputError(String userName, String password, String firstName, String lastName, String email, String phone){
		String errorMessage ="";
		if (!isUserNameValid(userName))
				errorMessage += "Invalid user name.\n";
		if (!isPasswordValid(password))
				errorMessage += "Invalid password.\n";	
		if (!isFirstNameValid(firstName))
			errorMessage += "Invalid first name.\n";
		if (!isLastNameValid(lastName))
			errorMessage += "Invalid last name.\n";
		if (!isEmailValid(email))
			errorMessage += "Invalid email.\n";
		if (!isPhoneNumberValid(phone))
			errorMessage += "Invalid phone number.\n";
		return errorMessage;
	}
	
	public String getUserName(){
		return userName;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	// TODO
	// you need to complete this method
	public static boolean isUserNameValid(String userName){
		// check if userName is valid
		return true;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
	
	//TODO
	// you need to complete this method
	public static boolean isPasswordValid(String password){
		// check if password is valid
		return true;
	}

    public boolean isValidCredential(String userName, String password) {
         return matchUserName(userName) && matchPassword(password);
    }
    
    public boolean matchUserName(String userName) {
         return userName != null && userName.equals(this.userName);
    }
    
    private boolean matchPassword(String password) {
        return password != null && password.equals(this.password);
   }

	public String getFirstName(){
		return firstName;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	//TODO
	// you need to complete this method
	public static boolean isFirstNameValid(String firstName){
		for (int i = 0; i != firstName.length(); ++i) {
        		if (!Character.isLetter(firstName.charAt(i))) {
        		    return false;
       			 }
    		}
		return true;
	}
	
	public String getLastName(){
		return lastName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	//TODO
	// you need to complete this method
	public static boolean isLastNameValid(String lastName){
		for (int i = 0; i != lastName.length(); ++i) {
        		if (!Character.isLetter(lastName.charAt(i))) {
        		    return false;
       			 }
    		}
		return true;
	}
	
	
	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}
	
	//TODO
	// you need to complete this method
	public static boolean isEmailValid(String email){
		// check if email is valid
		return true;
	}

	public String getPhoneNumber(){
		return phone;
	}

	public void setPhoneNumber(String phone){
		this.phone = phone;
	}
	
	//TODO
	// you need to complete this method
	public static boolean isPhoneNumberValid(String phone){
		try{
			int pn  =  Integer.parseInt(phone);
			return(phone.length()==9);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	public Date getRegistrationDate(){
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate){
		this.registrationDate = registrationDate;
	}

	public Date getLastUpdateDate(){
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate){
		this.lastUpdateDate = lastUpdateDate;
	}

}
