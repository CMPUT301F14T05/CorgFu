package ca.ualberta.cs.corgFuViews;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFu.IView;
import ca.ualberta.cs.corgFu.InsertQuestionAdapter;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Question;

public class BrowseItems extends Activity implements IView
{
	
	private InsertQuestionAdapter listAdapter;
	Spinner sortOptions;
	AllQuestions AQ;
	AllQuestionsController AQController;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		//calls function to populate list
		populateListView();

	}

	// Taken from
	// http://developer.android.com/guide/topics/ui/controls/spinner.html

	// populates list view using adapter class
	public void populateListView()
	{

		setContentView(R.layout.activity_browse_items);
		AllQuestions AQ = AllQuestionsApplication.getAllQuestions();
		AQ.addView(this);
		final AllQuestionsController AQController = AllQuestionsApplication
				.getAllQuestionsController();

		// populateSpinner(0);
		// Toast.makeText(this,"chose sort date", Toast.LENGTH_SHORT).show();
		final Spinner sortOptions = (Spinner) findViewById(R.id.sortSpinner);
		ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter
				.createFromResource(this, R.array.sortOptions,
						android.R.layout.simple_spinner_item);
		spinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sortOptions.setAdapter(spinnerAdapter);
		// creates a listener to determine sort order
		sortOptions.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3)
			{

				int position = sortOptions.getSelectedItemPosition();
				ListView bQListView = (ListView) findViewById(R.id.browseQuestionsListView);
				switch (position)
				{
				// will sort by date
					case 0:
						System.out.println("chose0");
						listAdapter = new InsertQuestionAdapter(
								BrowseItems.this, AQController.sortByDate());
						bQListView = (ListView) findViewById(R.id.browseQuestionsListView);
						bQListView.setAdapter(listAdapter);

						// Toast.makeText(this,"chose sort date",
						// Toast.LENGTH_SHORT).show();
						break;
					// will sort by pic
					case 1:
						System.out.println("chose1");
						listAdapter = new InsertQuestionAdapter(
								BrowseItems.this, AQController.sortByPicture());
						bQListView = (ListView) findViewById(R.id.browseQuestionsListView);
						bQListView.setAdapter(listAdapter);
						// Toast.makeText(this,"chose sort date",
						// Toast.LENGTH_SHORT).show();
						break;
					// will sort by vote
					case 2:
						System.out.println("chose2");

						listAdapter = new InsertQuestionAdapter(
								BrowseItems.this, AQController.sortByUpvote());
						bQListView = (ListView) findViewById(R.id.browseQuestionsListView);
						bQListView.setAdapter(listAdapter);
						// Toast.makeText(this,"chose sort date",
						// Toast.LENGTH_SHORT).show();
						break;

				}
				// TODO Auto-generated method stub

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{

				// TODO Auto-generated method stub

			}
		});
		Button myProfileButton = (Button) findViewById(R.id.MyProfileButton);
		Button askButton = (Button) findViewById(R.id.GoToAsk);
	}
	
	@Override
	public void onDestroy()
	{

		super.onDestroy();
		AllQuestions aQ = AllQuestionsApplication.getAllQuestions();
		aQ.deleteView(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.browse_items, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{

		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void toAskQuestion(View view)
	{

		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	public void toUserProfile(View view)
	{

	}

	public void viewMostUpvotedQuestions()
	{

		// Test string
	}

	public void viewMostUpvotedAnswers()
	{

	}

	public static ArrayList<Question> getCurrentDisplayCollection()
	{

		return null;
	}

	@Override
	public void update(Object model)
	{

		listAdapter.notifyDataSetChanged();
	}

}
