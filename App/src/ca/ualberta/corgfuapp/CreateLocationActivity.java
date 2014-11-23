package ca.ualberta.corgfuapp;

import ca.ualberta.cs.corgFu.UserName;
import ca.ualberta.cs.corgFuViews.LoginActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateLocationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_location);
		TextView TV = (TextView)findViewById(R.id.locationText);//grabs the text view to be displayed
		Typeface customTypeFace = Typeface.createFromAsset(getAssets(), "fonts/26783.ttf");//creates a custom typeface from the textview
		TV.setTypeface(customTypeFace);//sets the textview to obtain that specific typeface
		TV.setTextColor(Color.argb(255,4,193,210));//sets the colour according to the argb values used in the storyboard
	}
	
	public void addLocation(View view){
		EditText ET = (EditText) findViewById(R.id.enterLocation);
		String locationString = ET.getText().toString();
		ET.setText("");
		int ulen = locationString.length();
		if (ulen <= 0 ){
			Toast.makeText(getApplicationContext(), "Location can't be empty.", Toast.LENGTH_LONG).show();
		}else{
			UserName nameOfUser= UserName.getInstance();
			nameOfUser.makeAddress(this,locationString);
			goToLogin();
		}	
		
	}
	
	public void goToLogin(){
		Intent intent = new Intent(this,LoginActivity.class);//creates a new intent for the CreateLocation activity
    	startActivity(intent);//starts the new activity
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_location, menu);
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
}
