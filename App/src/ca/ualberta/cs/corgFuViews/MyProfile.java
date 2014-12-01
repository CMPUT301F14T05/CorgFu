package ca.ualberta.cs.corgFuViews;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import ca.ualberta.corgfuapp.R;
/**
 * This is the activity that contains several choices of content that the
 * user would like to see. This is the cached data, the favourites, the read
 * later, the authored questions and the changing of the location you are in
 * @author Ahmed
 *
 */
public class MyProfile extends Activity {
	public static final String EXTRA_CHOICE = "the Choice";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_profile);
	}

	/**
	 * This starts an activity of the data that has been cached to the users device
	 * because they have viewed it
	 * @param v the view that is being changed to the activity of cached data
	 */
	public void toCached(View v){
		Intent intent = new Intent(this, OfflineDataView.class);
		intent.putExtra(EXTRA_CHOICE, "CacheFile.save");
		startActivity(intent);
	}
	
	/**
	 * This starts an activity of the data that has been favourite by the user to
	 * their device because they have selected to favourite it
	 * @param v is the view that is being changed to the activity of favourites
	 */
	public void toFav(View v){
		Intent intent = new Intent(this, OfflineDataView.class);
		intent.putExtra(EXTRA_CHOICE, "Favourites.save");
		startActivity(intent);
	}
	/**
	 * This starts an activity of the data that has been chose to read later by the user to
	 * their device because they have selected the read later button
	 * @param v is the view that is being changed to the activity of read later
	 */
	public void toReadLater(View v){
		Intent intent = new Intent(this, OfflineDataView.class);
		intent.putExtra(EXTRA_CHOICE, "ReadLater.save");
		startActivity(intent);
	}
	/**
	 * This starts an activity of the data that has been authored by the user to
	 * their device because they have created the questions
	 * @param v is the view that is being changed to the activity of their authored questions
	 */
	public void toMyQuestions(View v){
		Intent intent = new Intent(this, OfflineDataView.class);
		intent.putExtra(EXTRA_CHOICE, "MyQuestions.save");
		startActivity(intent);
	}
	
	/**
	 * this goes to the activity that allows the user to change the location that they have originally set
	 * to the user profile
	 * @param v is the view that is being changed to the activity of the location changing activity
	 */
	public void toAddLocation(View v){
		Intent intent = new Intent(this, CreateLocationActivity.class);
		startActivity(intent);
		
	}
	@Override
	/**
	 * the menu of the activity
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_profile, menu);
		return true;
	}

	@Override
	/**
	 * the menu of the activity
	 */
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
