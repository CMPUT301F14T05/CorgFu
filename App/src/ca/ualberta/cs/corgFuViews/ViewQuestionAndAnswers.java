package ca.ualberta.cs.corgFuViews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFu.AllAnswersApplication;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFu.IView;
import ca.ualberta.cs.corgFu.InsertAnswerAdapter;
import ca.ualberta.cs.corgFuControllers.AllAnswersController;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuControllers.DataController;
import ca.ualberta.cs.corgFuControllers.QAController;
import ca.ualberta.cs.corgFuModels.AllAnswers;
import ca.ualberta.cs.corgFuModels.Answer;
import ca.ualberta.cs.corgFuModels.Question;
/**
 * Activity that is responsible for showing a Question. A question
 * can be composed of the question text, a picture related to the question
 * number of upvotes, replies to the question, and all of the answers to 
 * the questions which can have answer text, a picture relating to the answer,
 * upvotes, and replies.
 * @see ca.ualberta.cs.corgFuModels.Question
 * @see ca.ualberta.cs.corgFuModels.Answer
 * @see ca.ualberta.cs.corgFu.Picture
 * @see ca.ualberta.cs.corgFu.Reply
 * @author wrflemin
 *
 */
public class ViewQuestionAndAnswers extends Activity implements IView
{
	
	/** This is the previous question asked by other users*/
	Question myQuestion;
	private int qId = 0;
	DataController fc;
	/** This is the answer that is being added by the user*/
	protected Answer a; //most recent Answer added by the user
	AllAnswers AA; 
	AllAnswersController AAController;
	
	/** This is the custom adapter*/
    InsertAnswerAdapter listAdapter;
    ExpandableListView expListView;
    List<String> answerHeader = new ArrayList<String>();
    HashMap<String, List<String>> replyChild = new HashMap<String, List<String>>();
	int headIncrement = 0;
 
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_question_and_answers);
		
		getQuestion();
		setFont();
		setPicture();
		populateListView();
	}
	
	@Override
	public void onResume(){
		super.onResume();
		expListView = (ExpandableListView) findViewById(R.id.questionRepliesExpandable);
		listAdapter = (InsertAnswerAdapter) expListView.getExpandableListAdapter();
		if (listAdapter != null){
			listAdapter.notifyDataSetChanged();
		}
	}
	
	/**
	 * Gets the question id that was sent through the intent using the tag
	 * of @string/idExtraTag. From their the question object is retrieved
	 * and its contents are populated into their respective views.
	 */
	private void getQuestion(){
		Bundle extra = getIntent().getExtras();
		if (extra != null){
			qId = extra.getInt("@string/idExtraTag");
		}
		
		Typeface customTF = Typeface.createFromAsset(getAssets(), "fonts/26783.ttf");
		
		AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
		
		fc = new DataController();
		
		myQuestion = AQC.getQuestionById(qId);
		QAController QAC = new QAController(myQuestion);
		QAC.updateQuestion();
		
		if(myQuestion.isFavourited()){
			ImageButton favButton = (ImageButton) findViewById(R.id.favoriteButton);
			favButton.setImageResource(android.R.drawable.btn_star_big_on);
			favButton.setClickable(false);
			favButton.setEnabled(false);
		}
		if (myQuestion.hasBeenRead()==false){
			myQuestion.Read();
			
			
			Log.i("BeenRead", "adding to read");
			fc.addData(myQuestion,1);
		}
		
		TextView questionText = (TextView) findViewById(R.id.questionText);
		questionText.setTypeface(customTF);
		questionText.setText(QAC.getQuestionString());
		
		TextView upvoteCount = (TextView) findViewById(R.id.upvoteCount);
		upvoteCount.setTypeface(customTF);
		upvoteCount.setText(Integer.toString(QAC.getVotes()));
		
	}
	
	/**
	 * Sets the font of the buttons to the "fonts/26783.ttf" font
	 */
	private void setFont(){
		Typeface customTF = Typeface.createFromAsset(getAssets(), "fonts/26783.ttf");
		
		Button readLater = (Button) findViewById(R.id.readLaterButton);
		readLater.setTypeface(customTF);
		
		Button submit = (Button) findViewById(R.id.submitAnswerButton);
		submit.setTypeface(customTF);
	}
	
	/**
	 * Updates imageView with a question picture if this question has a picture
	 */
	private void setPicture() {
		if (myQuestion.hasPicture()) {
			//Toast.makeText(this, "has picture", Toast.LENGTH_LONG).show();
			ImageView qPictureView = (ImageView)findViewById(R.id.qPictureView);
			qPictureView.setImageBitmap(myQuestion.getImage());
		}
		else {
			//Toast.makeText(this, "no picture :(", Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * Populates the ListView of answers with answers in the order 
	 * specified by the user (Sorted by date on default)
	 */
	public void populateListView()
	{	
		// fetch answers via MVC 
		AllAnswers AA = AllAnswersApplication.getAllAnswers();
		AA.addView(this);
		final AllAnswersController AAController = AllAnswersApplication
				.getAllAnswersController();

		// populate expandable 
		try{
			expListView = (ExpandableListView) findViewById(R.id.questionRepliesExpandable);
			// setting list adapter
			expListView.setAdapter(listAdapter);
        }catch(Exception e){
            System.out.println("Errrr +++ " + e.getMessage());
        }
		
	}
	
	/**
	 * Add the question being viewed to the read later list which makes the
	 * question available for reading offline. 
	 * @param v The view that was clicked on
	 */
	public void readLater(View v){
		
		Log.i("VQAA", "breaks after");
		if (myQuestion.isReadLater()==false){
			myQuestion.addToReadLatered();
			fc.addData(myQuestion,2);
		}else{
			Toast.makeText(this, "This has already been added", Toast.LENGTH_SHORT).show();
		}
		Log.i("VQAA", "makes it back");
		// Change button image after question has been added to favorites
		
	}
	
	/**
	 * Add the question being viewed to the favorite list which makes the
	 * question available for reading offline. 
	 * @param v The view being clicked on
	 */
	public void addToFavorite(View v){
		Log.i("VQAA", "breaks after");
		if (myQuestion.isFavourited() ==false){
			myQuestion.favourited();
			fc.addData(myQuestion,0);
			Log.i("VQAA", "makes it back");
			ImageButton favButton = (ImageButton) findViewById(R.id.favoriteButton);
			favButton.setImageResource(android.R.drawable.btn_star_big_on);
			favButton.setClickable(false);
			favButton.setEnabled(false);
		}else{
			Toast.makeText(this,"already added!", Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * Upvotes the question. First the question is retrieved through the id, then
	 * it is upvoted.
	 * @param v The view that is being clicked on.
	 */
	public void upvote(View v){
		
		AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
		Question myQuestion = AQC.getQuestionById(qId);
		QAController QAC = new QAController(myQuestion);
		
		Typeface customTF = Typeface.createFromAsset(getAssets(), "fonts/26783.ttf");

		TextView upvoteCount = (TextView) findViewById(R.id.upvoteCount);
		upvoteCount.setTypeface(customTF);
		QAC.upvote();
		upvoteCount.setText(Integer.toString(QAC.getVotes()));
		
		Button upvoteButton = (Button) findViewById(R.id.upvoteButton);
		upvoteButton.setClickable(false);
		upvoteButton.setEnabled(false);	
	}
	
	/**
	 * Adds an answer to the question when the user clicks the submit answer button
	 * @param v The view that was clicked on
	 */
	public void submitAnswer(View v){
		// fetch answer string
		EditText answerEditText = (EditText) findViewById(R.id.answerQuestionEditText);
		String answerText = answerEditText.getText().toString();
		answerEditText.setText("");
		
		// MVC to handle singleton answer
		a = new Answer(answerText);
		AllAnswersController AAC = AllAnswersApplication.getAllAnswersController();
		AAC.addAnswer(a);
		Toast.makeText(this, "Your answer has been added", Toast.LENGTH_SHORT).show();
		
		// populate explistView
		prepareListData(answerText);
		listAdapter = new InsertAnswerAdapter(this, answerHeader, replyChild);
		populateListView();
		
		// update();
	}
	
	@Override
	public void update()
	{
		//listAdapter.notifyDataSetChanged();
	}
	
	// testing 
    private void prepareListData(String answerText) {
        // Adding header data
    	
    	
        answerHeader.add(answerText);
 
        // Adding child data
        List<String> replies = new ArrayList<String>();
        replies.add("Reply 1");

        replyChild.put(answerHeader.get(headIncrement), replies); // Header, Child data
        headIncrement +=1;
    }
}