package ca.ualberta.cs.corgFuViews;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFu.View;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.Question;

public class BrowseItems extends Activity implements View {
	
	private EditText searchText;
	private ListView bQListView;
	private ArrayAdapter<Question> listAdapter;
	private AllQuestionsController AQController;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse_items);
		
		searchText = (EditText) findViewById(R.id.searchEditText);
		bQListView = (ListView) findViewById(R.id.browseQuestionsListView);
		
		//listAdapter = new ArrayAdapter<Question>(this, R.layout.question_item_layout, );
		//bQListView.setAdapter(listAdapter);
		
		TextView TV = (TextView) findViewById(R.id.searchLabel);
		//Make text desired font and colour for labels.
		Typeface customTypeFace = Typeface.createFromAsset(getAssets(), "fonts/26783.ttf");
		TV.setTypeface(customTypeFace);
		TV.setTextColor(Color.argb(255,4,193,210));
		
		TextView TV2 = (TextView) findViewById(R.id.sortLabel);
		TV2.setTypeface(customTypeFace);
		TV2.setTextColor(Color.argb(255,4,193,210));
		
		//Taken from http://developer.android.com/guide/topics/ui/controls/spinner.html
		//Populates spinner (dropdown menu)
		Spinner sortOptions = (Spinner) findViewById(R.id.sortSpinner);
		ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, 
				R.array.sortOptions, android.R.layout.simple_spinner_item);
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sortOptions.setAdapter(spinnerAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.browse_items, menu);
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
	
	public void viewMostUpvotedQuestions(){
		//Test string
	}
	
	public void viewMostUpvotedAnswers(){
		
	}

	public static ArrayList<Question> getCurrentDisplayCollection()
	{
		
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public void update(Object model) {
		// TODO Auto-generated method stub
		
	}
}
