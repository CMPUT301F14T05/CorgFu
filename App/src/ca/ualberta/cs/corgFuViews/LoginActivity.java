package ca.ualberta.cs.corgFuViews;

import java.io.IOError;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
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

/**The LoginActivity Activity handles setting the global username for the session
 * , it also saves the user preference of whether or not they would like
 * to attach their location to questions that they post. The location is 
 * also determined in this activity and is assumed to not change during
 * the users session.
 * 
 * 
 * @author Alex Makepeace
 * @author Devon Sigurd
 * 
 * @version 2.0 Nov. 22,2014
 *
 */

public class LoginActivity extends Activity implements LocationListener{
	/**The context is used to determine the GPS location of the user.*/
	public static Context context;
	/**A locationManager object is used to get providers and update the user's current location.*/
	private LocationManager locationManager;
	/**The currentLocation is used to hold whatever location is established in the getLocation() method.*/
	private Location currentLocation;
	
	/**The onCreate method is used to grab the username that will be used,
	 * also it grabs any location information that can be used during the session.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		context = getBaseContext();
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_login);
		TextView TV = (TextView)findViewById(R.id.initPrompt);//grabs the text view to be displayed
		Typeface customTypeFace = Typeface.createFromAsset(getAssets(), "fonts/26783.ttf");//creates a custom typeface from the textview
		TV.setTypeface(customTypeFace);//sets the textview to obtain that specific typeface
		TV.setTextColor(Color.argb(255,4,193,210));//sets the colour according to the argb values used in the storyboard
		
		UserName nameOfUser= UserName.getInstance();
		if (nameOfUser.getFormattedAddress() == null){
			getLocation(this);
		}
		
		//Toast.makeText(getApplicationContext(), address, Toast.LENGTH_LONG).show();
		
		final Button btn = (Button) findViewById(R.id.LoginButton);
		btn.setOnClickListener(new UserLoginListner());
	}
	
	/**The getLocation method implements grabbing the user's current location
	 * going through a  list of providers to find the last known GPS location of the 
	 * user's device in a location manager. It implements a location listener to 
	 * pull off this process. If the location received is not null, the 
	 * location will be returned to store in a username object, 
	 * else it will ask for the user to input a custom location using the 
	 * custom location activity.
	 * 
	 * @param con grabs the Context of the app that will be used to determine location
	 * @return the location object that the user inputs or the GPS grabs
	 * @throws Exception if location services aren't working
	 */
	public void getLocation(Context con){
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
		        UserName user = UserName.getInstance();
		        if (location != null) { // used to prevent null pointer exceptions
		    		user.setLocation(location);
		        }
		    }
	}
	
	public void goToCreateLocation(){
		Intent intent = new Intent(this,CreateLocationActivity.class);//creates a new intent for the CreateLocation activity
    	startActivity(intent);//starts the new activity
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
				Toast.makeText(getApplicationContext(), "User name can't be empty.", Toast.LENGTH_LONG).show();
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
