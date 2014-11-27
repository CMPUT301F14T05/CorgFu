package ca.ualberta.cs.corgFuViews;

import ca.ualberta.corgfuapp.R;
import ca.ualberta.corgfuapp.R.id;
import ca.ualberta.corgfuapp.R.layout;
import ca.ualberta.corgfuapp.R.menu;
import ca.ualberta.cs.corgFu.UserName;
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

/**This activity handles when the user would like to select their own username.
 * It checks to see if a username has been chosen before, and transitions to the next 
 * activity needed accordingly.
 * 
 * @author Alex Makepeace
 *
 *@version 1.0 Nov.22/2014
 */
public class CreateLocationActivity extends Activity {

	/**The onCreate method handles the formatting, font and colour of the views on the 
	 * activity.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_location);
		TextView TV = (TextView)findViewById(R.id.locationText);//grabs the text view to be displayed
		Typeface customTypeFace = Typeface.createFromAsset(getAssets(), "fonts/26783.ttf");//creates a custom typeface from the textview
		TV.setTypeface(customTypeFace);//sets the textview to obtain that specific typeface
		TV.setTextColor(Color.argb(255,4,193,210));//sets the colour according to the argb values used in the storyboard
	}
	
	/**The addLocation method handles taking an input from the edittext box and 
	 * transforming it into a useable string that can be used as a location.
	 * This method accounts for whether or not it is the first time you are
	 * setting your location or not as well.
	 * 
	 * @param view is the button being clicked
	 */
	public void addLocation(View view){
		EditText ET = (EditText) findViewById(R.id.enterLocation);
		String locationString = ET.getText().toString();
		ET.setText("");
		int ulen = locationString.length();
		if (ulen <= 0 ){
			Toast.makeText(getApplicationContext(), "Location can't be empty.", Toast.LENGTH_LONG).show();
		}else{
			UserName nameOfUser= UserName.getInstance();
			if (nameOfUser.getFormattedAddress() == null){
				nameOfUser.makeAddress(locationString);
				//Toast.makeText(getApplicationContext(), nameOfUser.getFormattedAddress(), Toast.LENGTH_LONG).show();
				toMain();
			} else {
				nameOfUser.makeAddress(locationString);
				toMain();
			}
		}	
	}
	
	/**toMain() returns you to the AskQuestions screen after you decide to manually set your location
	 * regardless of GPS capabilities
	 */
	private void toMain (){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
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
