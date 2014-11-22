package ca.ualberta.cs.corgfuapp.UItest;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.corgFu.UserName;
import ca.ualberta.cs.corgFuViews.LoginActivity;

public class LoginActivityTest<Bundle> extends ActivityInstrumentationTestCase2<LoginActivity>{
	
	private String provider;
	
	public LoginActivityTest(){
		super(LoginActivity.class);
	}
	
	public void testgetLocation(){
		UserName user1 = UserName.getInstance();
		Location currLocation;
		LoginActivity activity = getActivity();
		
		LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		Location location = locationManager.getLastKnownLocation(provider);
		
		assertNotNull(location);
		
//		LocationListener locationListener = new LocationListener(){
//				
//				public void onLocationChanged(Location location) {
//				      // Called when a new location is found by the network location provider.
//				    	setUserLocation(location);
//				    }
//
//			    public void onStatusChanged(String provider, int status, Bundle extras) {}
//
//			    public void onProviderEnabled(String provider) {}
//
//			    public void onProviderDisabled(String provider) {}
//			    
//				@Override
//				public void onStatusChanged(String provider, int status,
//						android.os.Bundle extras) {
//					// TODO Auto-generated method stub
//					
//				}
//		};
//		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
//		Location l = user1.getLocation();    
		
	}

	protected void setUserLocation(Location location) {
		// TODO Auto-generated method stub
		UserName user1 = UserName.getInstance();
		user1.setLocation(location);
		
	}
}
