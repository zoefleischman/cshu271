package gui;

import javax.swing.*;

import business.UserAccountManager;

import java.awt.event.*;
import java.awt.BorderLayout;

public class ForgotUserNameWindow extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private JTextField emailTextField = new JTextField(30);

	private JButton OKButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");

	private UserAccountManager accountManager;
		
	public ForgotUserNameWindow(JFrame parent, UserAccountManager accountManager) {
		super(parent, "Forgot User Name");
		this.accountManager = accountManager;
	    getContentPane().setLayout(new BorderLayout());
	    getContentPane().add(createProfilePanel(), BorderLayout.CENTER,0);
	    getContentPane().add(createActionPanel(), BorderLayout.SOUTH,0);
	    pack();
	}
	
	private JPanel createProfilePanel(){
	    JPanel panel = new JPanel();
	    panel.add(new JLabel(" Email"));
	    panel.add(emailTextField);
	    return panel;
	}
	
	private void setUpConfirmButton(){
	    OKButton.setFont(new java.awt.Font("Dialog", 0, 12));
		OKButton.setName("OKButton");
	    OKButton.addActionListener(new java.awt.event.ActionListener() {
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
	    panel.add(OKButton);
	    panel.add(cancelButton);
	    return panel;
	}
	
	void cancelButtonHandler(ActionEvent e) {
		this.dispose();		
	}

	void confirmButtonHandler(ActionEvent e) {
		String actionResult = accountManager.forgotUserName(emailTextField.getText());
    		if(actionResult.equals(UserAccountManager.NOINPUTERROR)) 
    			JOptionPane.showMessageDialog(this, "User name has been sent to the email address!", "Forgot User Name", JOptionPane.INFORMATION_MESSAGE);
	    else
			JOptionPane.showMessageDialog(this, actionResult, "Incorrect email",JOptionPane.INFORMATION_MESSAGE);	
		this.dispose();
	}

}

