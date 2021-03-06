package ca.ualberta.cs.corgFuViews;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFu.IView;
import ca.ualberta.cs.corgFu.InsertQuestionAdapter;
import ca.ualberta.cs.corgFu.UserName;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Question;

/**
 * The activity that Allows users to browse through the currently available
 * questions that are present in the AllQuestions model. The activity has a
 * search bar where the user can search all questions (if they have internet
 * connectivity) and can also choose how to sort the questions (regardless of 
 * network connectivity).
 * @author wrflemin
 * @see ca.ualberta.cs.corgFuModels.AllQuestions
 */
public class BrowseItems extends Activity implements IView
{
	
	private InsertQuestionAdapter listAdapter;
	Spinner sortOptions;
	AllQuestionsController AQController;
	
	@Override
	/**
	 * populates the List view with the questions from AllQuestions and 
	 * sets up the click listener for each item. Also sets the font for
	 * the text in the view.
	 */
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		//calls function to populate list
		populateListView();
		setListViewListener();
		setFont();
	}
	
	@Override
	/**
	 * Updates the list with the most recent entries from AllQuestions
	 */
	public void onResume(){
		super.onResume();
		populateListView();
		setListViewListener();
		setFont();
	}

	// Taken from
	// http://developer.android.com/guide/topics/ui/controls/spinner.html

	// populates list view using adapter class
	/**
	 * Populates the ListView of questions with questions in the order 
	 * specified by the user (Sorted by date on default)
	 */
	public void populateListView()
	{

		setContentView(R.layout.activity_browse_items);
		AllQuestions AQ = AllQuestionsApplication.getAllQuestions();
		AQ.addView(this);
		AQController = AllQuestionsApplication
				.getAllQuestionsController();
		AQController.updateAllQuestions(); //Populates allQuestions with available questions

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
						
						listAdapter = new InsertQuestionAdapter(
								BrowseItems.this, AQController.sortByDate());
						break;
					// will sort by pic
					case 1:
						
						listAdapter = new InsertQuestionAdapter(
								BrowseItems.this, AQController.sortByPicture());
						break;
					// will sort by vote
					case 2:
						
						listAdapter = new InsertQuestionAdapter(
								BrowseItems.this, AQController.sortByUpvote());
						break;
					//will open neaby questions
					case 3:
						UserName username = UserName.getInstance();
						startSearchActivity(username.getFormattedAddress(), "readableAddress");

				}
				// TODO Auto-generated method stub
				bQListView = (ListView) findViewById(R.id.browseQuestionsListView);
				bQListView.setAdapter(listAdapter);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{

				// TODO Auto-generated method stub

			}
		});
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
	/**
	 *  Method that is called when the user clicks on the Ask Question
	 *  button. Starts the activity so that the user can then ask a question.
	 *  <p>
	 * @param view The view that was clicked by the user.
	 */
	public void toAskQuestion(View view){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	/**
	 * Method that is called when the user clicks on User Profile button.
	 * Starts the user profile activity for the user.
	 * <p>
	 * @param view The view that was clicked by the user.
	 */
	public void toUserProfile(View view){
		Intent intent = new Intent(this, MyProfile.class);
		startActivity(intent);
	}
	/**
	 * Method that is called when the user clicks on the search button.
	 * It extracts the text from the search fields and sends it to the
	 * method that will start the search activity as long as the field
	 * isn't empty.
	 * @param view
	 */
	public void toSearchResults(View view){
		EditText searchBox = (EditText) findViewById(R.id.searchEditText);
		String searchTerm = searchBox.getText().toString();
		if (!searchTerm.equals("")){
			startSearchActivity(searchTerm, null);
		}
	}
	/**
	 * Method that adds the search term to be search to the extra so that
	 * the SearchResults activity can search using that search term.
	 * @param searchTerm The string entered by the user that they want to 
	 * search for
	 * @see ca.ualberta.cs.corgFuViews.SearchResults
	 */
	private void startSearchActivity(String searchTerm, String field){
		Intent intent = new Intent(this, SearchResults.class);
		intent.putExtra("@string/idSearchTerm", searchTerm);
		intent.putExtra("@string/idField", field);
		startActivity(intent);
	}
	
	/**
	 * Sets up the click listener for the ListView that will allow for 
	 * clicking on specific questions in the listview.
	 */
	private void setListViewListener(){
		final OnItemClickListener mMessageClickedHandler = new OnItemClickListener() {
		    public void onItemClick(AdapterView parent, View v, int position, long id) {
		    	Question question = (Question) parent.getItemAtPosition(position);
		    	int qId = question.getId();
		    	goToQuestion(qId);
		    }
		};
		
		ListView listView = (ListView) findViewById(R.id.browseQuestionsListView);
		listView.setOnItemClickListener(mMessageClickedHandler);
	}
	
	@Override
	public void update()
	{
		listAdapter.notifyDataSetChanged();
	}
	
	/**
	 * Sets the custom font for the text in the view using the font:
	 * fonts/26783.ttf
	 */
	private void setFont(){
		Typeface customTF = Typeface.createFromAsset(getAssets(), "fonts/26783.ttf");
		
		Button goToAsk = (Button) findViewById(R.id.GoToAsk);
		goToAsk.setTypeface(customTF);
		
		Button myProfile = (Button) findViewById(R.id.MyProfileButton);
		myProfile.setTypeface(customTF);
		
		TextView searchLabel = (TextView) findViewById(R.id.searchLabel);
		searchLabel.setTypeface(customTF);
		
		TextView sortLabel = (TextView) findViewById(R.id.sortLabel);
		sortLabel.setTypeface(customTF);
	}
	/**
	 * Opens the question that was clicked by the user and puts the question
	 * id in an extra so that the view question view can populate itself 
	 * based on the id of the question.
	 * @param qId The id of the question that is most likely unique 
	 * (uses a random number which should be unique enough for our
	 * purposes.
	 */
	private void goToQuestion(int qId){
		Intent intent = new Intent(this, ViewQuestionAndReplies.class);
    	intent.putExtra("@string/idExtraTag", qId);
    	startActivity(intent);
	}

}
