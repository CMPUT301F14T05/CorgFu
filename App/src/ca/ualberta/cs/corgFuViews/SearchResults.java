package ca.ualberta.cs.corgFuViews;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFu.InsertQuestionAdapter;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuES.ElasticSearch;
import ca.ualberta.cs.corgFuModels.Question;

/**
 * The activity that displays the search results of a search.
 * The search string is passed to the activity as an extra 
 * and it is extracted here and used to query elasticsearch.
 * The rest of the view behavies as BrowseItems does, allowing
 * the user to view the question text, upvote count, number of
 * answers, and location.The question can also be clicked on to
 * open the ViewQuestionAndReplies Activity.
 * @author wrflemin
 * @see ca.ualberta.cs.corgFuViews.BrowseItems
 * @see ca.ualberta.cs.corgFuViews.ViewQuestionAndReplies
 */
public class SearchResults extends Activity {

	private ElasticSearch ES;
	private ArrayList<Question> results;
	private InsertQuestionAdapter listAdapter;
	
	@Override
	/**
	 * Here the class used for elastic search is set up and the
	 * list for viewing the questions is set up.
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_results);
		ES = new ElasticSearch();
		results = null;
		results = startSearch();
		setAdapter();
		setListViewListener();
	}
	
	@Override
	public void onResume(){
		super.onResume();
		listAdapter.notifyDataSetChanged();
	}
	/**
	 * Method that performs the search using the keywords
	 * entered by the user in BrowseItems
	 * @return The list of search results of the query
	 */
	private ArrayList<Question> startSearch() {
		Bundle extra = getIntent().getExtras();
		String searchTerm = "";
		if (extra != null){
			searchTerm = extra.getString("@string/idSearchTerm");
		}
		AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
		return AQC.search(searchTerm, null);
	}
	
	/**
	 * Sets the question adapter for the listview that will
	 * display the questions from the search results.
	 */
	private void setAdapter() {
		listAdapter = new InsertQuestionAdapter(
				SearchResults.this, results);
		ListView resultsList = (ListView) findViewById(R.id.resultsListView);
		TextView empty = (TextView) findViewById(android.R.id.empty);
		resultsList.setEmptyView(empty);
		resultsList.setAdapter(listAdapter);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_results, menu);
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
		
		ListView listView = (ListView) findViewById(R.id.resultsListView);
		listView.setOnItemClickListener(mMessageClickedHandler);
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
