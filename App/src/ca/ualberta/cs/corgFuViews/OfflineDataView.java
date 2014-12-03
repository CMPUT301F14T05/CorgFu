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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFu.InsertQuestionAdapter;
import ca.ualberta.cs.corgFu.choiceSingleton;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuControllers.DataController;
import ca.ualberta.cs.corgFuModels.Question;

/**
 * The activity that contains the data that has been made offline and been
 * made to view offline such as the users authored questions, viewed, favorited
 * and chosen to read later
 * @author Ahmed
 *
 */
public class OfflineDataView extends Activity {
	private static InsertQuestionAdapter listAdapter1;
	public static Context context ;
	
	public static ArrayList<Question> myData;
	DataController mfc;
	static String choice;
	@Override
	/**
	 * The on create method that calls the instance of the singleton of the choice
	 * depending on what file type wants to be displayed
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("OFDV","arrived");
		Bundle extra = getIntent().getExtras();
		String choice = extra.getString(MyProfile.EXTRA_CHOICE);
		Log.i("OFDV",String.valueOf(choice));
		choiceSingleton cs = choiceSingleton.getInstance();
		cs.setChoice(choice);
		populateListView(choice);
		setListViewListener();
	}
	/**
	 * on resume of the activity that calls the instance of a choice of file's singleton
	 */
	protected void onResume(){
		super.onResume();
		Log.i("OFDV","arrived");
		Bundle extra = getIntent().getExtras();
		String choice = extra.getString(MyProfile.EXTRA_CHOICE);
		Log.i("OFDV",String.valueOf(choice));
		choiceSingleton cs = choiceSingleton.getInstance();
		cs.setChoice(choice);
		populateListView(choice );
		setListViewListener();
	}
	
	/**
	 * The population of a list view depending on the choice of file that we are choosing
	 * to display
	 * @param choice is the data type that we are displaying
	 */
	public void populateListView(String choice){
		context =getBaseContext();
		setContentView(R.layout.activity_offline_data);
		//Log.i("FVL","befor controller");
		ArrayList<Question> myData = new ArrayList<Question>();
		
		final DataController myDataController = new DataController();
		Log.i("OFDV", String.valueOf(choice));
		myData = myDataController.getData(choice);
		Log.i("FVL","before load list view");
		
		final AllQuestionsController AQController = AllQuestionsApplication
				.getAllQuestionsController();
		AQController.updateAllQuestions();
		ArrayList<Question> onlineData = AQController.getAllQuestions();
		int i=0;
		for (Question q: myData){
			
			for(Question onlineQ: onlineData){
				if(q.getId()== onlineQ.getId()){
					
					Log.i("online upvotes", String.valueOf(onlineQ.getUpvotes()));
					myData.set(i, onlineQ);
					break;
				}
			}
			i++;
		}
		for(Question q: myData){
			Log.i("online upvotes",q.getQuestionText());
			Log.i("online upvotes", String.valueOf(q.getUpvotes()));
		}
		myDataController.saveData(myData, choice);
		
		
		
		
		listAdapter1 = new InsertQuestionAdapter(OfflineDataView.this, myData);
		Log.i("FVL","before insert1");
		ListView BFListView = (ListView) findViewById(R.id.browseOfflineListView);
		Log.i("FVL","before insert2");
		BFListView.setAdapter(listAdapter1);		
	}
	/**
	 * returns the choice
	 * @return the choice
	 */
	public String getChoice(){
		return choice;
	}
	/**
	 * setting the list view listener
	 */
	private void setListViewListener(){
		final OnItemClickListener mMessageClickedHandler = new OnItemClickListener() {
		    public void onItemClick(AdapterView parent, View v, int position, long id) {
		    	Question question = (Question) parent.getItemAtPosition(position);
		    	int qId = question.getId();
		    	goToQuestion(qId);
		    }
		};
		
		ListView listView = (ListView) findViewById(R.id.browseOfflineListView);
		listView.setOnItemClickListener(mMessageClickedHandler);
	}
	/**
	 * Going to the questioni in the view starting a new intent based on the question
	 * that was selected, that contains a unique ID
	 * @param qId the id of the question
	 */
	private void goToQuestion(int qId){
		Intent intent = new Intent(this, ViewQuestionAndReplies.class);
		
		choiceSingleton cs = new choiceSingleton();
		String choiceStr = cs.getChoice();
    	intent.putExtra("@string/idExtraTag", qId);
    	Log.i("string",choiceStr);
    	intent.putExtra("@string/loadFromTag", choiceStr);
    	startActivity(intent);
	}

	@Override
	/**
	 * the menu of the activity
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.favourite, menu);
		return true;
	}
	/**
	 * the function that goes to the user profile activity when selected by a button
	 * @param view is the view that is passed to go to user profile
	 */
	public void toUserProfile(View view){
		Intent intent = new Intent(this, MyProfile.class);
		startActivity(intent);
	}
	/**
	 * This is the function that goes to the ask question activity when a button is selected
	 * @param view is the view that is passed to go to ask question
	 */
	public void toAskQuestion(View view){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	@Override
	/**
	 * The menu of the activity
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