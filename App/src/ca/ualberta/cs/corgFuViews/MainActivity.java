package ca.ualberta.cs.corgFuViews;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuControllers.DataController;
import ca.ualberta.cs.corgFuModels.Question;
/**
 * This is the MainActivity Activity. It is in charge of our screen for asking questions. 
 * It is on this screen that you can enter a question into our edittext box and post it to the app.
 * From this page you can go to your personal profile or go to browse questions posed by others.
 * 
 * @author Alex Makepeace
 * @author Wyatt Fleming
 * 
 * @version 2.0 Nov.6/2014
 * 
 */
public class MainActivity extends Activity
{
	public static Context context;
	/** This is the question that is being added by the user*/
	protected Question q; //most recent Question added by the user
	DataController DC;
	/**onCreate() sets up the display of the Activity, it makes sure that all of the desired text is set to the correct typeface.*/
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		context = getBaseContext();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button myProfileButton = (Button)findViewById(R.id.MyProfileButton);//button to click to go to your user profile
		Button answersButton = (Button)findViewById(R.id.GoToAnswer);//button to click to go to the list of previously asked questions
		TextView TV = (TextView)findViewById(R.id.MainQuestionText);//grabs the text view to be displayed
		
		Typeface customTypeFace = Typeface.createFromAsset(getAssets(), "fonts/26783.ttf");//creates a custom typeface from the textview
		DC = new DataController();
		myProfileButton.setTypeface(customTypeFace);//sets the button to obtain that specific typeface
		answersButton.setTypeface(customTypeFace);//sets the typeface for another button
		TV.setTypeface(customTypeFace);//sets the textview to obtain that specific typeface
	}
	/**toBrowseItems() changes to intent of the app to that of viewing browseItems.
	 * Clicking on a button starts the Activity of BrowseItems
	 * 
	 * @param view takes in the current view
	 */
	public void toBrowseItems(View view){
    	Intent intent = new Intent(this,BrowseItems.class);//creates a new intent for the BrowseItems activity
    	startActivity(intent);//starts the new activity
	}
	
	/**addQuestion() method handles what happens when the add question button is clicked.
	 * Takes what is entered in an edittext box and creates a question out of the text.
	 * This method also handles whether or not the user wants to attach a picture to their question through fragment menus.
	 * The created question will then be added to the Question singleton.
	 * A toast will appear if the question has been added successfully.
	 * 
	 * @param view takes in the view of the current activity
	 */
	public void addQuestion(View view){
		
		EditText questionText = (EditText) findViewById(R.id.EnterQuestionBox);//grabs the iD of the edit Text box where you will be entering ypour information

		String question = questionText.getText().toString();//this is the text pulled from our edittext box

		questionText.setText("");//sets the edit text box to blank after entering a question

		q = new Question(question); // creates a new question object
		
		AddPictureDialogFragment addPictureDialog = new AddPictureDialogFragment();
		addPictureDialog.show(getFragmentManager(), null);
			
		AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
		AQC.addQuestion(q);
		DC.addData(q, 3);
		Toast.makeText(getApplicationContext(), "Your question has been added.", Toast.LENGTH_LONG).show();

	}
	/**onCreateOptionsMenu() is an auto-generated method */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/**goToProfile() creates a new intent for the desired activity we wanted to move to.
	 * Taking this intent we start the MyProfile activity.
	 * 
	 * @param v takes in the view of the activity
	 */
	public void goToProfile(View v){
		Intent intent = new Intent(this, MyProfile.class);
		startActivity(intent);
	}
	/**onOptionsItemSelected() is an auto-generated method*/
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
