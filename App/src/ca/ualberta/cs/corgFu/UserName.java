package ca.ualberta.cs.corgFu;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;




public class UserName {
	
	private static UserName instance = null;
	private String uN;
	private Location location;
	private Geocoder geocoder;
	private Boolean attachLocation;
	
	public UserName(){
		
	}
	
	public static UserName getInstance(){
		if (instance == null){
			instance = new UserName();
		}
		return instance;
	}
	public void setUserName(String uNameString){
		instance.uN = uNameString; 
	}
	
	public String getUserName(){
		return instance.uN;
	}
	public Location getLocation() {
		// TODO Auto-generated method stub
		return instance.location;

	}
	public void setLocation(Location l) {
		// TODO Auto-generated method stub
		instance.location = l;
	}
	public Address getAddress() {
		// TODO Auto-generated method stub
		return null;
	}
	public void makeAddress(String location2) {
		// TODO Auto-generated method stub
		
	}
	public void attachLocation(Boolean attach){
		instance.attachLocation = attach;
	}

	public Boolean getAttachLocation() {
		// TODO Auto-generated method stub
		return instance.attachLocation;
	}

	
}
