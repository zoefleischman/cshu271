package sampletests;

import gui.UserManagerGUI;

import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTextField;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.eventdata.KeyEventData;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.DialogFinder;
import junit.extensions.jfcunit.finder.NamedComponentFinder;


public class UserManagerGUITests extends JFCTestCase {

	private UserManagerGUI loginWindow = null;
	private TestHelper helper = null;

	public UserManagerGUITests(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		helper = new JFCTestHelper();
		loginWindow = new UserManagerGUI("LoginGUITest: " + getName());
		loginWindow.setVisible(true);
	}

	protected void tearDown() throws Exception {
		loginWindow = null;
		JFCTestHelper.cleanUp(this);
		super.tearDown();
	}

	@SuppressWarnings({ "unchecked", "unchecked" })
	public void testUserAndPasswordEmpty() {
	    NamedComponentFinder finder = new NamedComponentFinder(JComponent.class, "CancelButton" );
	    JButton cancelButton = ( JButton ) finder.find( loginWindow, 0);
	    assertNotNull( "Could not find the Cancel button", cancelButton );

	    finder.setName( "LoginButton" );
	    JButton loginButton = ( JButton ) finder.find( loginWindow, 0 );
	    assertNotNull( "Could not find the Login button", loginButton );

	    finder.setName( "LoginNameTextField" );
	    JTextField userNameField = ( JTextField ) finder.find( loginWindow, 0 );
	    assertNotNull( "Could not find the userNameField", userNameField );
	    assertEquals( "Username field is empty", "", userNameField.getText( ) );

	    finder.setName( "PasswordTextField" );
	    JTextField passwordField = ( JTextField ) finder.find( loginWindow, 0 );
	    assertNotNull( "Could not find the passwordField", passwordField );
	    assertEquals( "Password field is empty", "", passwordField.getText( ) );

		try {
			helper.enterClickAndLeave(new MouseEventData(this, loginButton));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

	    DialogFinder dFinder = new DialogFinder(UserManagerGUI.FailedLogin);
	    List showingDialogs = dFinder.findAll(loginWindow);
	    assertEquals( "Number of dialogs showing is wrong", 1, showingDialogs.size( ) );
	    JDialog dialog = ( JDialog )showingDialogs.get( 0 );
	    assertEquals( "Wrong dialog showing up", UserManagerGUI.FailedLogin, dialog.getTitle( ) );
	    TestHelper.disposeWindow( dialog, this );
	}

	@SuppressWarnings("unchecked")
	public void testEmptyPassword() {

	    NamedComponentFinder finder = new NamedComponentFinder( JComponent.class, "CancelButton" );
	    JButton cancelButton = ( JButton ) finder.find(loginWindow, 0 );
	    assertNotNull( "Could not find the Cancel button", cancelButton );

	    finder.setName( "LoginButton" );
	    JButton loginButton = ( JButton ) finder.find( loginWindow, 0 );
	    assertNotNull( "Could not find the Login button", loginButton );

	    finder.setName( "LoginNameTextField" );
	    JTextField userNameField = ( JTextField ) finder.find( loginWindow, 0 );
	    assertNotNull( "Could not find the userNameField", userNameField );
	    try {
	    	helper.sendString( new StringEventData( this, userNameField, "admin" ) );
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

	    finder.setName( "PasswordTextField" );
	    JTextField passwordField = ( JTextField ) finder.find( loginWindow, 0 );
	    assertNotNull( "Could not find the passwordField", passwordField );
	    assertEquals( "Password field is empty", "", passwordField.getText( ) );

		try {
			helper.enterClickAndLeave(new MouseEventData(this, loginButton));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

		DialogFinder dFinder = new DialogFinder(UserManagerGUI.FailedLogin);
	    List showingDialogs = dFinder.findAll( loginWindow );
	    assertEquals( "Number of dialogs showing is wrong", 1, showingDialogs.size( ) );
	    JDialog dialog = ( JDialog )showingDialogs.get( 0 );
	    assertEquals( "Wrong dialog showing up", UserManagerGUI.FailedLogin, dialog.getTitle( ) );
	    TestHelper.disposeWindow( dialog, this );
	}
	    
	public void testValidAccount() {

	    NamedComponentFinder finder = new NamedComponentFinder( JComponent.class, "CancelButton" );
	    JButton cancelButton = ( JButton ) finder.find( loginWindow, 0 );
	    assertNotNull( "Could not find the Cancel button", cancelButton );

	    finder.setName( "LoginButton" );
	    JButton loginButton = ( JButton ) finder.find( loginWindow, 0 );
	    assertNotNull( "Could not find the Login button", loginButton );

	    finder.setName( "LoginNameTextField" );
	    JTextField userNameField = ( JTextField ) finder.find( loginWindow, 0 );
	    assertNotNull( "Could not find the userNameField", userNameField );

	    finder.setName( "PasswordTextField" );
	    JTextField passwordField = ( JTextField ) finder.find( loginWindow, 0 );
	    assertNotNull( "Could not find the passwordField", passwordField );

		try {
		    helper.sendString( new StringEventData( this, userNameField, "admin" ) );
		    helper.sendString( new StringEventData( this, passwordField, "@BSUccp251") );
			helper.enterClickAndLeave(new MouseEventData(this, loginButton));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		DialogFinder dFinder = new DialogFinder(UserManagerGUI.SuccessfulLogin);
	    List showingDialogs = dFinder.findAll( loginWindow );
	    assertEquals( "Number of dialogs showing is wrong", 1, showingDialogs.size( ) );
	    JDialog dialog = ( JDialog )showingDialogs.get( 0 );
	    assertEquals( "Wrong dialog showing up", UserManagerGUI.SuccessfulLogin, dialog.getTitle( ) );
	    TestHelper.disposeWindow( dialog, this );
	}
	
	
	@SuppressWarnings("unchecked")
	public void testFocus() {

	    NamedComponentFinder finder = new NamedComponentFinder( JComponent.class, "CancelButton" );
	    JButton cancelButton = ( JButton ) finder.find( loginWindow, 0 );
	    assertNotNull( "Could not find the Cancel button", cancelButton );

	    finder.setName( "LoginButton" );
	    JButton loginButton = ( JButton ) finder.find( loginWindow, 0 );
	    assertNotNull( "Could not find the Login button", loginButton );

	    finder.setName( "LoginNameTextField" );
	    JTextField userNameField = ( JTextField ) finder.find( loginWindow, 0 );
	    assertNotNull( "Could not find the userNameField", userNameField );

	    finder.setName( "PasswordTextField" );
	    JTextField passwordField = ( JTextField ) finder.find( loginWindow, 0 );
	    assertNotNull( "Could not find the passwordField", passwordField );

	    DialogFinder dFinder = new DialogFinder(null);
	    dFinder.setWait(0);

	    // ( Keyboard ) Enter pressed on the userName field - focus should go to the passwordField
	    pauseAWT( );
	    userNameField.requestFocus( );
	    flushAWT( );
	    assertTrue( "userNameField does not have focus", userNameField.hasFocus( ) );
	    
	    try {
	    	helper.sendKeyAction( new KeyEventData( this, userNameField, KeyEvent.VK_ENTER ) );
	    }
	    catch (Exception ex) {
	    	ex.printStackTrace();
	    }
	    
	    List showingDialogs = dFinder.findAll( loginWindow );
	    assertEquals( "( 1 )Number of dialogs showing is wrong", 0, showingDialogs.size( ) );
//	    assertTrue( "passwordField does not have focus", passwordField.hasFocus( ) );

	    // ( Keyboard ) Enter pressed on the password field - error dialog should appear
	    pauseAWT( );
	    passwordField.requestFocus( );
	    flushAWT( );
	    assertTrue( "passwordField does not have focus", passwordField.hasFocus( ) );
	    try {
	    	helper.sendKeyAction( new KeyEventData( this, passwordField, KeyEvent.VK_ENTER ) );
		}
    	catch (Exception ex) {
    		ex.printStackTrace();
    	}

	    showingDialogs = dFinder.findAll( loginWindow );

	}
		
}