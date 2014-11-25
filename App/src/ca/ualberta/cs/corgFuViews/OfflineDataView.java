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
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuControllers.DataController;
import ca.ualberta.cs.corgFuModels.Question;

public class OfflineDataView extends Activity {
	private static InsertQuestionAdapter listAdapter1;
	public static Context context ;
	int choice;
	public static ArrayList<Question> myData;
	DataController mfc;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("OFDV","arrived");
		Bundle extra = getIntent().getExtras();
		String choice = extra.getString(MyProfile.EXTRA_CHOICE);
		Log.i("OFDV",String.valueOf(choice));
		
		populateListView(choice );
		setListViewListener();
		
	}
	protected void onResume(){
		super.onResume();
		Log.i("OFDV","arrived");
		Bundle extra = getIntent().getExtras();
		String choice = extra.getString(MyProfile.EXTRA_CHOICE);
		Log.i("OFDV",String.valueOf(choice));
		
		populateListView(choice );
		setListViewListener();
	}
	
	//4321 add order
	// fave 4231
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
	private void goToQuestion(int qId){
		Intent intent = new Intent(this, ViewQuestionAndAnswers.class);
    	intent.putExtra("@string/idExtraTag", qId);
    	startActivity(intent);
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