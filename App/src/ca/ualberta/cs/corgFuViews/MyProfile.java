package ca.ualberta.cs.corgFuViews;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFu.ConnectedManager;

public class MyProfile extends Activity {
	public static final String EXTRA_CHOICE = "the Choice";
	private Context context=getBaseContext();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_profile);
		ConnectedManager connected = ConnectedManager.getInstatnce();
 		connected.attemptToPushOfflineContent();

	}
	
	public void toCached(View v){
		Intent intent = new Intent(this, OfflineDataView.class);
		intent.putExtra(EXTRA_CHOICE, "CacheFile.save");
		startActivity(intent);
	}
	public void toFav(View v){
		Intent intent = new Intent(this, OfflineDataView.class);
		intent.putExtra(EXTRA_CHOICE, "Favourites.save");
		startActivity(intent);
	}
	public void toReadLater(View v){
		Intent intent = new Intent(this, OfflineDataView.class);
		intent.putExtra(EXTRA_CHOICE, "ReadLater.save");
		startActivity(intent);
	}
	public void toMyQuestions(View v){
		Intent intent = new Intent(this, OfflineDataView.class);
		intent.putExtra(EXTRA_CHOICE, "MyQuestions.save");
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_profile, menu);
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
