package gui;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import business.UserAccount;
import business.UserAccountManager;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.awt.BorderLayout;

public class EditProfileWindow extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private JTextField usernameTextField = new JTextField(30);
	private JPasswordField passwordTextField = new JPasswordField(30);
	private JPasswordField reenterPasswordTextField = new JPasswordField(30);
	private JTextField firstNameTextField = new JTextField(20);
	private JTextField lastNameTextField = new JTextField(20);
	private JTextField emailTextField = new JTextField(30);
	private JTextField phoneTextField;

	private JButton confirmButton = new JButton("Confirm");
	private JButton cancelButton = new JButton("Cancel");

	private UserAccountManager accountManager;
	private UserAccount currentAccount;
	
	public EditProfileWindow(JFrame parent, UserAccount currentAccount, UserAccountManager accountManager) {
		super(parent, currentAccount==null? "Create New Account": "Edit Current Account");
		this.currentAccount= currentAccount;
		this.accountManager = accountManager;
		uploadProfile();
	    getContentPane().setLayout(new BorderLayout());
	    getContentPane().add(createProfilePanel(), BorderLayout.CENTER,0);
	    getContentPane().add(createActionPanel(), BorderLayout.SOUTH,0);
	    pack();
	}
	
	private void uploadProfile(){
		if (currentAccount!=null) {
			usernameTextField.setText(currentAccount.getUserName());
			passwordTextField.setText(currentAccount.getPassword());
			reenterPasswordTextField.setText(currentAccount.getPassword());
			firstNameTextField.setText(currentAccount.getFirstName());
			lastNameTextField.setText(currentAccount.getLastName());
			emailTextField.setText(currentAccount.getEmail());
			phoneTextField.setText(currentAccount.getPhoneNumber());
		}
	}
	
	private JPanel createProfilePanel(){
	    JPanel labelPanel = new JPanel();
	    labelPanel.setLayout(new GridLayout(7,1));
	    
	    //Create a MaskFormatter for phone number
	    try {
			MaskFormatter phoneFormat = new MaskFormatter("(###) ###-####");
			phoneTextField = new JFormattedTextField(phoneFormat);
		} catch (ParseException e) {
			System.out.println("Something went wrong: Phone number did not parse correctly");
			e.printStackTrace();
		}
	    
	    labelPanel.add(new JLabel(" User name"));
	    labelPanel.add(new JLabel(" Password"));
	    labelPanel.add(new JLabel(" Re-enter password"));
	    labelPanel.add(new JLabel(" First name"));
	    labelPanel.add(new JLabel(" Last name"));
	    labelPanel.add(new JLabel(" Email address"));
	    labelPanel.add(new JLabel(" Phone number"));

	    JPanel inputPanel = new JPanel();
	    inputPanel.setLayout(new GridLayout(7,1));
	    inputPanel.add(usernameTextField);
	    inputPanel.add(passwordTextField);
	    inputPanel.add(reenterPasswordTextField);
	    inputPanel.add(firstNameTextField);
	    inputPanel.add(lastNameTextField);
	    inputPanel.add(emailTextField);
	    inputPanel.add(phoneTextField);
	    
	    JPanel profilePanel = new JPanel();
	    profilePanel.setLayout(new BorderLayout());
	    profilePanel.add(labelPanel, BorderLayout.WEST);
	    profilePanel.add(inputPanel, BorderLayout.EAST);
	    return profilePanel;
	}
	
	private void setUpConfirmButton(){
	    confirmButton.setFont(new java.awt.Font("Dialog", 0, 12));
		confirmButton.setName("ConfirmButton");
	    confirmButton.addActionListener(new java.awt.event.ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        confirmButtonHandler(e);	        
	      }
	    });
	} 
	
	private void setUpCancelButton(){
	    cancelButton.setFont(new java.awt.Font("Dialog", 0, 12));
		cancelButton.setName("CancelButton");
	    cancelButton.addActionListener(new java.awt.event.ActionListener(){
	      public void actionPerformed(ActionEvent e) {
	    	  	cancelButtonHandler(e);
	      }
	    });
	}

	private JPanel createActionPanel(){
	    JPanel panel = new JPanel();
	    setUpConfirmButton();
	    setUpCancelButton();
	    panel.add(confirmButton);
	    panel.add(cancelButton);
	    return panel;
	}
	
	void cancelButtonHandler(ActionEvent e) {
		this.dispose();		
	}

	void confirmButtonHandler(ActionEvent e) {
		String accountActionResult;
		if (currentAccount==null) //create new user account
			accountActionResult = accountManager.registerNewUser(
					usernameTextField.getText(),
					new String(passwordTextField.getPassword()),
					new String(reenterPasswordTextField.getPassword()),
					firstNameTextField.getText(),
					lastNameTextField.getText(),
					emailTextField.getText(),
					phoneTextField.getText());
		else // update existing account profile
			accountActionResult = accountManager.updateAccountProfile(
					currentAccount,
					usernameTextField.getText(),
					new String(passwordTextField.getPassword()),
					new String(reenterPasswordTextField.getPassword()),
					firstNameTextField.getText(),
					lastNameTextField.getText(),
					emailTextField.getText(),
					phoneTextField.getText());
			
    		if(accountActionResult.equals(UserAccountManager.NOINPUTERROR)) {
    			if (currentAccount==null)
    				JOptionPane.showMessageDialog(this, "New account created!", "Successful user registration", JOptionPane.INFORMATION_MESSAGE);
    			else
    				JOptionPane.showMessageDialog(this, "Account profile updated!", "Successful profile update", JOptionPane.INFORMATION_MESSAGE);
    			this.dispose();
		}
	    else
			JOptionPane.showMessageDialog(this, accountActionResult, "Incorrect inputs",JOptionPane.INFORMATION_MESSAGE);	
	}

	public static void main(String args[]){
		JDialog editProfileWindow = new EditProfileWindow(null, null, new UserAccountManager());
		editProfileWindow.setVisible(true);
	}

}

