package ca.ualberta.cs.corgFuViews;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFu.UserName;

public class LoginActivity extends Activity implements LocationListener{
	public static Context context;
	private String provider;
	private LocationManager locationManager;
	private Location currentLocation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		context = getBaseContext();
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_login);
		TextView TV = (TextView)findViewById(R.id.initPrompt);//grabs the text view to be displayed
		Typeface customTypeFace = Typeface.createFromAsset(getAssets(), "fonts/26783.ttf");//creates a custom typeface from the textview
		TV.setTypeface(customTypeFace);//sets the textview to obtain that specific typeface
		TV.setTextColor(Color.argb(255,4,193,210));//sets the colour according to the argb values used in the storyboard
		
		currentLocation = getLocation(this);
		
		UserName user = UserName.getInstance();
		user.setLocation(currentLocation);
		
		final Button btn = (Button) findViewById(R.id.LoginButton);
		btn.setOnClickListener(new UserLoginListner());
		

	}
	
	public Location getLocation(Context con){
		String location_context = Context.LOCATION_SERVICE;
	    locationManager = (LocationManager) con.getSystemService(location_context);
	    List<String> providers = locationManager.getProviders(true);
	    for (String provider : providers) {
	        locationManager.requestLocationUpdates(provider, 1000, 0,
	            new LocationListener() {// the location listener code was found on Google Android tutorials http://developer.android.com/guide/topics/location/strategies.html on 11/21/14

	                public void onLocationChanged(Location location) {}

	                public void onProviderDisabled(String provider) {}

	                public void onProviderEnabled(String provider) {}

	                public void onStatusChanged(String provider, int status,
	                        Bundle extras) {}
	            });
	        Location location = locationManager.getLastKnownLocation(provider);
	        if (location != null) { // used to prevent null pointer exceptions
	            int latitude = (int)location.getLatitude();
	            int longitude = (int)location.getLongitude();
	            String lat = String.valueOf(latitude);
	            String lng = String.valueOf(longitude);
	           // Toast.makeText(getApplicationContext(), lat + " " + lng, Toast.LENGTH_LONG).show();
	            return location;
	        } else {
	        	
	        }
	    }
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	private final class UserLoginListner implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			EditText uId = (EditText) findViewById(R.id.UIDEditText);
			String UserNameString = uId.getText().toString();
			int ulen = UserNameString.length();
			if (ulen <= 0 ){
				Toast.makeText(getApplicationContext(), "User Name Cant be empty", Toast.LENGTH_LONG).show();
			}else{
				UserName nameOfUser= UserName.getInstance();
				nameOfUser.setUserName(UserNameString);
				Toast.makeText(getApplicationContext(), UserName.getInstance().getUserName()  +" logged in!", Toast.LENGTH_LONG).show();
				AddLocationToQuestionFragment addLocationFragment = new AddLocationToQuestionFragment();
				addLocationFragment.show(getFragmentManager(), null);
			}
		}	
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
}
