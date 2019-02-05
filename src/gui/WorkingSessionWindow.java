package gui;

import javax.swing.*;

import business.UserAccount;
import business.UserAccountManager;

import java.awt.event.*;
import java.awt.BorderLayout;

public class WorkingSessionWindow extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private JTextArea infoTextArea = new JTextArea(5, 40);

	private JButton editProfileButton = new JButton("Edit Profile");
	private JButton cancelButton = new JButton("Cancel");

	private UserAccountManager accountManager;
	private UserAccount currentAccount;
	
	public WorkingSessionWindow(JFrame parent, UserAccount currentAccount, UserAccountManager accountManager) {
		super(parent, UserManagerGUI.SuccessfulLogin);
		this.currentAccount= currentAccount;
		this.accountManager = accountManager;
	    getContentPane().setLayout(new BorderLayout());
	    getContentPane().add(createInfoPanel(), BorderLayout.CENTER,0);
	    getContentPane().add(createActionPanel(), BorderLayout.SOUTH,0);
	    pack();
	}
	
	private JPanel createInfoPanel(){
	    JPanel infoPanel = new JPanel();
	    infoTextArea.setEditable(false);
	    infoTextArea.setText("Welcome, "+currentAccount.getFirstName()+
	    		"!\nPlease enjoy the free services.");
	    infoPanel.add(infoTextArea);
	    return infoPanel;
	}
	
	private void setUpConfirmButton(){
	    editProfileButton.setFont(new java.awt.Font("Dialog", 0, 12));
		editProfileButton.setName("EditProfileButton");
	    editProfileButton.addActionListener(new java.awt.event.ActionListener() {
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
	    panel.add(editProfileButton);
	    panel.add(cancelButton);
	    return panel;
	}
	
	void cancelButtonHandler(ActionEvent e) {
		this.dispose();		
	}

	void confirmButtonHandler(ActionEvent e) {
		JDialog editProfileWindow = new EditProfileWindow((JFrame) this.getParent(), currentAccount, accountManager);
		editProfileWindow.setLocationRelativeTo(this);
		editProfileWindow.setVisible(true);
	}

}

