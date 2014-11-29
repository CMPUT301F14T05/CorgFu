package ca.ualberta.cs.corgFu;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

/**The username class is a singleton that holds all relevant information that the
 * application may need to access. 
 * 
 * @author Alex Makepeace
 * @author Devon Sigurd
 * 
 * @version 2.0 Nov. 26,1024
 *
 */


public class UserName {
	
	/**The singleton instance for the username being used across the app*/
	private static UserName instance = null;
	/**String representing the inputted username*/
	private String uN;
	/**The location that is attached to the username if location services are turned on*/
	private Location location;
	/**A geocoder object used to convert a location object into a formatted string*/
	private Geocoder geocoder;
	/**The boolean that is checked to see if  the user would like to attach their location to posts*/
	private Boolean attachLocation;
	/**The address object that is used to store the users address from their location*/
	private Address adr;
	/**The easily readable string that is meant to display the current location as entered by the user or 
	 * retrieved by geolocation.*/
	private String formattedAddress;
	/**This is the city name saved for search and location proximity services*/
	private String myCity;
	
	/**Constructor for username, never really used*/
	public UserName(){
		
	}
	
	/**Initializes the singleton for the UserName object that exists across the app session.
	 * 
	 * @return the instance of the username used in the session
	 */
	public static UserName getInstance(){
		if (instance == null){
			instance = new UserName();	
		}
		return instance;
	}
	
	/**This method sets the desired username for the instance of the username class
	 * 
	 * @param uNameString the string enetered by the user that is their desired username
	 */
	public void setUserName(String uNameString){
		instance.uN = uNameString; 
	}
	
	/**getUserName returns the string that has been set as the username 
	 * for this session.
	 * 
	 * @return the string name attached to the username singleton
	 */
	public String getUserName(){
		return instance.uN;
	}
	
	/**Method returns the location that has been attached ot the instance of the username.
	 * 
	 * @return singleton's attached location object
	 */
	public Location getLocation() {
		// TODO Auto-generated method stub
		return instance.location;
	}
	
	/**Method sets the location of the user singleton to whatever location is passed in
	 * 
	 * @param l is the desired location the user has been determined to be set at
	 */
	public void setLocation(Location l) {
		// TODO Auto-generated method stub
		instance.location = l;
	}
	
	/**Sets the users current address based upon the set location for the user. 
	 * 
	 * @param con takes in the application context
	 */
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
			setFormattedAddress();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**Method returns the set address for the user singleton
	 * 
	 * @return the singeton's address object
	 */
	public Address getAddress(){
		return instance.adr;
	}
	
	/**Method takes an inputted string and sets it to the formatted address of the user.
	 * 
	 * @param l the location that the user has chosen
	 */
	public void makeAddress(String l) {
		// TODO Auto-generated method stub
		instance.formattedAddress = l;	
	}
	
	/**Method sets whether or not the user would like to attach their location to their posts.
	 * 
	 * @param attach the boolean that establishes the user preference
	 */
	public void attachLocation(Boolean attach){
		instance.attachLocation = attach;
	}

	/**Method returns the user's preference whether or not they would like to attach their
	 * location to questions.
	 * 
	 * @return boolean of user attach location to question preference
	 */
	public Boolean getAttachLocation() {
		// TODO Auto-generated method stub
		return instance.attachLocation;
	}
	
	/**Determines how the the formatted address will be attached based on the users set preferences.
	 * If the user does not have a previously set address and would like their address to be 
	 * attached to future posts it will call the setFormattedAddress method.
	 * If the user does not want their location to be posted, the method will set their 
	 * formatted address to "No Location Available". If they have previously set a formattedAddress
	 * that will be returned.
	 * 
	 * @return the UserName singleton's formatted address
	 */
	public String getFormattedAddress(){
		if (instance.formattedAddress == null){
			if (instance.getLocation() != null){	
				if (instance.getAttachLocation() == true){
					setFormattedAddress();
				} else {
					instance.formattedAddress = "No Location Available.";
				}
			} else {
					instance.formattedAddress = "No Location Available.";
				}
			}
		return instance.formattedAddress;
	}
	
	/**The set formatted address method is only called if the user has location 
	 * services enabled on their device. The method takes the address object attached to 
	 * the username and converts it to a readable string containing the users city 
	 * and country.
	 * 
	 * @return the readable string of the users city and country
	 */
	public String setFormattedAddress(){
		Address address = instance.adr;
		String str = new String();
		
		String city = address.getLocality();
		instance.myCity = city;
		String country = address.getCountryName();
		
		str = city + ", " + country;
		instance.formattedAddress = str;
		return str;
	}
	
	/**Method retrieves the city the user currently is set to.
	 * 
	 * @return returns the users set city
	 */
	public String getUserCity(){
		return instance.myCity;
	}
	
}
