package ca.ualberta.cs.corgfuapp.test;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFu.UserName;

public class SetUsernameTest extends TestCase {
	
	public SetUsernameTest(){
		super();
	}
	
	public void testInitializeUsername(){
		UserName myUserName = new UserName();
		assertNotNull(myUserName);
	}
	
	public void testSetUsername(){
		String firstUsername = "testUser";
		String secondUsername = "second testUser";
		UserName myUserName = new UserName();
		myUserName.setUserName(firstUsername);
		assertEquals("Testing setting username", firstUsername, myUserName.getUserName());
		myUserName.setUserName(secondUsername);
		assertEquals("Testing setting another username", secondUsername, myUserName.getUserName());
	}
	public void testLoadSaveUsername(){
		String testUsername = "testUser";
		UserName myUserName = new UserName();
		myUserName.setUserName(testUsername);
		
		myUserName.saveUserName();
		
		assertEquals("Testing load/save username", testUsername, myUserName.loadUserName().getUserName());
	}
}
