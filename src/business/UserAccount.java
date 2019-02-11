package business;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class UserAccount {

	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private Date registrationDate;
	private Date lastUpdateDate;

	public enum EmailMessageType {FORGOT_USERNAME, FORGOT_PASSWORD}

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

	public static boolean isUserNameValid(String userName){
		boolean valid = true;
		if(!Character.isLetter(userName.charAt(0))) {
			valid = false;
		}
		for(int i=1;i<userName.length();i++) {
			if(!Character.isLetter(userName.charAt(i))) {
				if(!Character.isDigit(userName.charAt(i))) {
					valid=false;
				}
			}
		}
		return valid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

	/**
	 * Checks password for all required elements.
	 * @param password the password to check for validity.
	 * @return true if valid, false if invalid.
	 */
	public static boolean isPasswordValid(String password){
		String specialChars = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";
		char currentChar;
		boolean containsNumber = false;
		boolean containsUpperCase = false;
		boolean containsLowerCase = false;
		boolean containsSpecialChar = false;
		boolean isLongEnough = (password.length() > 5);

		for (int i = 0; i < password.length(); i++) {
			currentChar = password.charAt(i);
			if (Character.isDigit(currentChar)) {
				containsNumber = true;
			} else if (Character.isUpperCase(currentChar)) {
				containsUpperCase = true;
			} else if (Character.isLowerCase(currentChar)) {
				containsLowerCase = true;
			} else if (specialChars.contains(String.valueOf(currentChar))) {
				containsSpecialChar = true;
			}
		}

		if (containsNumber && containsUpperCase && containsLowerCase && 
				containsSpecialChar && isLongEnough) {
			return true;
				}
		else return false;
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

	/**
	 * Determines if a given email address follows correct syntax rules.
	 * @param email The email address to check for validity.
	 * @return true if valid, false if invalid.
	 */
	public static boolean isEmailValid(String email){

		try {
			InternetAddress emailAddress = new InternetAddress(email);
			emailAddress.validate();
		} catch (AddressException e) {
			return false;
		}

		return true;
	}

	public String getPhoneNumber(){
		return phone;
	}

	public void setPhoneNumber(String phone){
		this.phone = phone;
	}

	// you need to complete this method
	public static boolean isPhoneNumberValid(String phone){

		String validChars = "0123456789";
		if (phone.length()==10) {

			for (int i=0; i<phone.length(); i++) {
				if (!validChars.contains(phone.substring(i,i+1)))
					return false;
			}
			return true;
		}
		return false;
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

	/**
	 * Sends an email to the specified address
	 * 
	 * Adapted from www.tutorialspoint.com
	 * @param messageType FORGOT_USERNAME or FORGOT_PASSWORD based on desired message to be sent.
	 */
	public void sendEmail(EmailMessageType messageType) {

		String from = "UserAccountManager@example.com";

		// Assuming you are sending email from localhost
		String host = "localhost";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

			// Set Subject: header field
			switch (messageType) {
				case FORGOT_USERNAME:
					message.setSubject("Forgot Username - User Account Manager");
					message.setText("Hello "+firstName+",\n\n"
							+"Your username is "+userName+".\n\n"
							+"-User Account Manager Team");
					break;
				case FORGOT_PASSWORD:
					message.setSubject("Forgot Password - User Account Manager");
					message.setText("Hello "+firstName+",\n\n"
							+"Your password is "+password+".\n\n"
							+"-User Account Manager Team");
					break;
			}

			Transport.send(message);

		} catch (MessagingException mex) {
			System.out.println("**Failed to send email**");
			mex.printStackTrace();
		}
	}
}
