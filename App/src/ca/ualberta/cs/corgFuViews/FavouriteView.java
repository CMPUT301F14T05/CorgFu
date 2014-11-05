package ca.ualberta.cs.corgFuViews;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFu.FavouriteListManager;
import ca.ualberta.cs.corgFu.InsertQuestionAdapter;
import ca.ualberta.cs.corgFu.Listener;
import ca.ualberta.cs.corgFuControllers.FavouritesController;
import ca.ualberta.cs.corgFuModels.Question;

public class FavouriteView extends Activity {
	private static InsertQuestionAdapter listAdapter1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favourite);
		ListView BFListView = (ListView) findViewById(R.id.browseFavouriteListView);
		FavouriteListManager.initManager(this.getApplicationContext());
		final ArrayList<Question> favourites = FavouritesController.getFavouriteList().getFavouriteList();
		listAdapter1 = new InsertQuestionAdapter(FavouriteView.this, favourites);
		BFListView.setAdapter(listAdapter1);
		FavouritesController.getFavouriteList().addListener(new Listener(){
			@Override
			public void update(){
				favourites.clear();
				ArrayList<Question> tmp = FavouritesController.getFavouriteList().getFavouriteList();
				favourites.addAll(tmp);
				listAdapter1.notifyDataSetChanged();
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.favourite, menu);
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
