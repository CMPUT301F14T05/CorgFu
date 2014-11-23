package ca.ualberta.cs.corgFu;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.widget.Toast;




public class UserName {
	
	private static UserName instance = null;
	private String uN;
	private Location location;
	private Geocoder geocoder;
	private Boolean attachLocation;
	private Address adr;
	private String formattedAddress = null;
	
	public UserName(){
		
	}
	
	public String getFormattedAddress(){
		return instance.formattedAddress;
	}
	
	public String setFormattedAddress(){
		Address address = instance.adr;
		String str = new String();
		
		String city = address.getLocality();
		String country = address.getCountryName();
		
		str = city + ", " + country;
		instance.formattedAddress = str;
		return str;
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
	public void setAddress(Context con) {
		// TODO Auto-generated method stub
		Location currentLocation = instance.location;
		geocoder = new Geocoder(con, Locale.ENGLISH);
		double latitude = currentLocation.getLatitude();
		double longitude = currentLocation.getLongitude();
		try {
			List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
			Address add = addresses.get(0);
			instance.adr = add;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Address getAddress(){
		return instance.adr;
	}
	
	public void makeAddress(Context con,String l) {
		// TODO Auto-generated method stub
		instance.formattedAddress = l;
		geocoder = new Geocoder(con, Locale.ENGLISH);
		try {
			List<Address> addresses = geocoder.getFromLocationName(l, 1);
			Address add = addresses.get(0);
			instance.adr = add;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void attachLocation(Boolean attach){
		instance.attachLocation = attach;
	}

	public Boolean getAttachLocation() {
		// TODO Auto-generated method stub
		return instance.attachLocation;
	}

	
}
