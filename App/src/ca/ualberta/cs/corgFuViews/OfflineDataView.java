package ca.ualberta.cs.corgFuViews;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFu.OfflineDataAdapter;
import ca.ualberta.cs.corgFu.UserName;
import ca.ualberta.cs.corgFuControllers.DataController;
import ca.ualberta.cs.corgFuModels.Question;

public class OfflineDataView extends Activity {
	private static OfflineDataAdapter listAdapter1;
	public static Context context ;
	
	public static ArrayList<Question> myData;
	DataController mfc;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("OFDV","arrived");
		Intent intentchosen = getIntent();
		int choice = intentchosen.getIntExtra(MyProfile.EXTRA_CHOICE, 0);
		Log.i("OFDV",String.valueOf(choice));
		
		populateListView(choice );
	}
	//4321 add order
	// fave 4231
	public void populateListView(int choice){
		context =getBaseContext();
		setContentView(R.layout.activity_favourite);
		//Log.i("FVL","befor controller");
		ArrayList<Question> myData = new ArrayList<Question>();
		
		final DataController mfc = new DataController();
		Log.i("OFDV", String.valueOf(choice));
		myData = mfc.getData(choice);
		
		ListView BFListView = (ListView) findViewById(R.id.browseOfflineListView);
		Log.i("FVL","before load attempt");
		
		
		listAdapter1 = new OfflineDataAdapter(OfflineDataView.this, myData);
		BFListView.setAdapter(listAdapter1);
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.favourite, menu);
		return true;
	}
	public void toUserProfile(View view){
		Intent intent = new Intent(this, MyProfile.class);
		startActivity(intent);
	}
	public void toAskQuestion(View view){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
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