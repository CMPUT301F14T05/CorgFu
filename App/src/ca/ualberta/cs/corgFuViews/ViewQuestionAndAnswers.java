package ca.ualberta.cs.corgFuViews;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuControllers.FavouritesController;
import ca.ualberta.cs.corgFuModels.Answer;
import ca.ualberta.cs.corgFuModels.Question;

public class ViewQuestionAndAnswers extends Activity
{

	Question myQuestion;
	private int qId = 0;
	Answer answer;
	

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_question_and_answers);
		getQuestion();
		setFont();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_question_and_answers, menu);
		return true;
	}
	
	private void getQuestion(){
		Bundle extra = getIntent().getExtras();
		if (extra != null){
			qId = extra.getInt("@string/idExtraTag");
		}
		
		Typeface customTF = Typeface.createFromAsset(getAssets(), "fonts/26783.ttf");
		
		AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
		Question myQuestion = AQC.getQuestionById(qId);
		
		TextView questionText = (TextView) findViewById(R.id.questionText);
		questionText.setTypeface(customTF);
		questionText.setText(myQuestion.getQuestionString());
		
		TextView upvoteCount = (TextView) findViewById(R.id.upvoteCount);
		upvoteCount.setTypeface(customTF);
		upvoteCount.setText(Integer.toString(myQuestion.getVotes()));
		
	}
	
	private void setFont(){
		Typeface customTF = Typeface.createFromAsset(getAssets(), "fonts/26783.ttf");
		
		Button readLater = (Button) findViewById(R.id.readLaterButton);
		readLater.setTypeface(customTF);
		
		Button submit = (Button) findViewById(R.id.submitAnswerButton);
		submit.setTypeface(customTF);
		
	}
	
	public void readLater(View v){
		
	}
	
	public void addToFavorite(View v){
		FavouritesController fc = new FavouritesController();
		fc.addFavourites(myQuestion);
		
		// Change button image after question has been added to favorites
		ImageButton favButton = (ImageButton) findViewById(R.id.favoriteButton);
		favButton.setImageResource(android.R.drawable.btn_star_big_on);
		favButton.setClickable(false);
		favButton.setEnabled(false);
	}
	
	public void upvote(View v){
		
		AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
		Question myQuestion = AQC.getQuestionById(qId);
		
		Typeface customTF = Typeface.createFromAsset(getAssets(), "fonts/26783.ttf");

		TextView upvoteCount = (TextView) findViewById(R.id.upvoteCount);
		upvoteCount.setTypeface(customTF);
		myQuestion.upvote();
		upvoteCount.setText(Integer.toString(myQuestion.getVotes()));
		
		Button upvoteButton = (Button) findViewById(R.id.upvoteButton);
		upvoteButton.setClickable(false);
		upvoteButton.setEnabled(false);
		
	}
	
	public void submitAnswer(View v){
		EditText answerEditText = (EditText) findViewById(R.id.answerQuestionEditText);
		String answerText = answerEditText.getText().toString();
		
		answer = new Answer(answerText);
		
		//throws addAnswer method exception
		//myQuestion.addAnswer(answer);
		
		answerEditText.setText("");

		Toast.makeText(this, "Your answer has been added", Toast.LENGTH_SHORT).show();
	}

}
