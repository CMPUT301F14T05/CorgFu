package ca.ualberta.cs.corgFuViews;

import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Question;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity
{
	AllQuestionsApplication aQ;//singleton for our questions
	AllQuestions allQuestions; //the allQuestions model that updates to hold all questions
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button myProfileButton = (Button)findViewById(R.id.MyProfileButton);
		Button answersButton = (Button)findViewById(R.id.GoToAnswer);
		TextView TV = (TextView)findViewById(R.id.MainQuestionText);//grabs the text view to be displayed
		
		Typeface customTypeFace = Typeface.createFromAsset(getAssets(), "fonts/26783.ttf");//creates a custom typeface from the textview
		
		myProfileButton.setTypeface(customTypeFace);//sets the button to obtain that specific typeface
		answersButton.setTypeface(customTypeFace);
		TV.setTypeface(customTypeFace);//sets the textview to obtain that specific typeface
		
		allQuestions = aQ.getAllQuestions();
	}
	
	public void toBrowseItems(View view){
    	Intent intent = new Intent(this,BrowseItems.class);
    	startActivity(intent);
	}
	
	public void addQuestion(View view){
		AddPictureDialogFragment addPictureDialog = new AddPictureDialogFragment();
		addPictureDialog.show(getFragmentManager(), null);
		
		
		allQuestions = aQ.getAllQuestions(); // grabs the most recent allQuestions from the singleton class
		
		EditText questionText = (EditText) findViewById(R.id.EnterQuestionBox);//grabs the iD of the edit Text box where you will be entering ypour information
		
		String question = questionText.getText().toString();//this is the text pulled from our edittext box
		
		questionText.setText("");//sets the edit text box to blank after entering a question
		
		Question q = new Question(question); // creates a new question object
		
		allQuestions.addQuestion(q); // adds the newly made question to our allQuestions
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
