package ca.ualberta.cs.corgFuViews;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import ca.ualberta.corgfuapp.R;

public class MyProfile extends Activity {
	public static final String EXTRA_CHOICE = "the Choice";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_profile);
	}
	
	public void toCached(View v){
		Intent intent = new Intent(this, OfflineDataView.class);
		int choiceClicked = 1;
		intent.putExtra(EXTRA_CHOICE, choiceClicked);
		startActivity(intent);
	}
	public void toFav(View v){
		Intent intent = new Intent(this, OfflineDataView.class);
		int choiceClicked = 0;
		intent.putExtra(EXTRA_CHOICE, choiceClicked);
		startActivity(intent);
	}
	public void toReadLater(View v){
		Intent intent = new Intent(this, OfflineDataView.class);
		int choiceClicked = 2;
		intent.putExtra(EXTRA_CHOICE, choiceClicked);
		startActivity(intent);
	}
	public void toMyQuestions(View v){
		Intent intent = new Intent(this, OfflineDataView.class);
		int choiceClicked = 3;
		intent.putExtra(EXTRA_CHOICE, choiceClicked);
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
