package ca.ualberta.cs.corgfuapp.test;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFu.UserName;

public class UsernameTest extends TestCase {
	
	
	public UsernameTest(){
		super();
	}
	
	public void testInitializeUsername(){
		UserName myUserName = UserName.getInstance();//initialize a username
		assertNotNull(myUserName);
	}
	
	public void testSetUsername(){
		UserName myUserName = UserName.getInstance();
		myUserName.setUserName("test");
		UserName myUserName2 = UserName.getInstance();
		myUserName2.setUserName("test");
		assertEquals("User names are:",myUserName.getUserName(), myUserName2.getUserName());
	}
	
	public void testSetLocation(){
		
	}
	/*public void testLoadSaveUsername(){
		String testUsername = "testUser";
		UserName myUserName = new UserName(testUsername);
		myUserName.setUserName(testUsername);
		
		myUserName.saveUserName();
		
		assertEquals("Testing load/save username", testUsername, myUserName.loadUserName().getUserName());
	}*/
}
