package ca.ualberta.cs.corgfuapp.test;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import junit.framework.TestCase;
import ca.ualberta.cs.corgFu.UserName;
import ca.ualberta.cs.corgFuModels.Question;
import ca.ualberta.cs.corgFuViews.LoginActivity;

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
	public void testsetLocation(){
		UserName myUserName = UserName.getInstance();
		Location l = new Location("test");
		myUserName.setLocation(l);
		assertNotNull(myUserName.getLocation());
	}
	
	public void testgetLocation(){
		UserName myUserName = UserName.getInstance();
		Location l = new Location("test");
		myUserName.setLocation(l);
		Location setLocation = myUserName.getLocation();
		assertNotNull(setLocation);
	}
	
	public void testgetAddress(){
		UserName myUserName = UserName.getInstance();
		Address address = myUserName.getAddress();
		assertNotNull(address);
	}
	public void testmakeAddress(){
		UserName myUserName = UserName.getInstance();
		String location = "Edmonton";
		myUserName.makeAddress(location);
		Address address = myUserName.getAddress();
		assertNotNull(address);
	}
	
	public void testattachLocation(){
		UserName myUserName = UserName.getInstance();
		myUserName.attachLocation(true);
		Boolean attach = myUserName.getAttachLocation();
		assertTrue(attach);
	}
	
	/*public void testLoadSaveUsername(){
		String testUsername = "testUser";
		UserName myUserName = new UserName(testUsername);
		myUserName.setUserName(testUsername);
		
		myUserName.saveUserName();
		
		assertEquals("Testing load/save username", testUsername, myUserName.loadUserName().getUserName());
	}*/
}
