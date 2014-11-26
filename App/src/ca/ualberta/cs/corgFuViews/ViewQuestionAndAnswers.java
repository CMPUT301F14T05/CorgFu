package ca.ualberta.cs.corgFuViews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFu.ArrayAnswerAdapter;
import ca.ualberta.cs.corgFu.IView;
import ca.ualberta.cs.corgFu.InsertAnswerAdapter;
import ca.ualberta.cs.corgFu.InsertReplyAdapter;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuControllers.DataController;
import ca.ualberta.cs.corgFuControllers.QAController;
import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Answer;
import ca.ualberta.cs.corgFuModels.Question;
import ca.ualberta.cs.corgFuModels.Reply;
/**
 * Activity that is responsible for showing a Question. A question
 * can be composed of the question text, a picture related to the question
 * number of upvotes, replies to the question, and all of the answers to 
 * the questions which can have answer text, a picture relating to the answer,
 * upvotes, and replies.
 * @see ca.ualberta.cs.corgFuModels.Question
 * @see ca.ualberta.cs.corgFuModels.Answer
 * @see ca.ualberta.cs.corgFu.Picture
 * @see ca.ualberta.cs.corgFuModels.Reply
 * @author wrflemin
 *
 */
public class ViewQuestionAndAnswers extends Activity implements IView
{
	// 0 = favourites file
	// 1 = cache file
	// 2 = read later file
	private final static String favourites = "Favourites.save";
	private final static String cache ="CacheFile.save";
	private final static String readlater = "ReadLater.save";
	/** This is the previous question asked by other users*/
	Question myQuestion;
	private int qId = 0;
	DataController dc;
	boolean hasBeenRead;
	/** This is the answer that is being added by the user*/
	protected Answer a; //most recent Answer added by the user
	
	/** This is the custom replyAdapter*/
    InsertReplyAdapter replyAdapter;
    ExpandableListView expListView;
    
    /* Outer list view with answers to a question */
    ListView listView;
    
    List<String> replyHeader = new ArrayList<String>();
    HashMap<String, List<String>> replyChild = new HashMap<String, List<String>>();
    
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_question_and_answers);
		hasBeenRead = false;
		getQuestion();
		setFont();
		setPicture();
		populateReplyView();
		populateAnswerView();
	}
	
	@Override
	public void onResume(){
		super.onResume();
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
		
		dc = new DataController();
		myQuestion = AQC.getQuestionById(qId);
		
		QAController QAC = new QAController(myQuestion);
		
		cache();
		isFavourited(myQuestion.getId());
		
		TextView questionText = (TextView) findViewById(R.id.questionText);
		questionText.setTypeface(customTF);
		questionText.setText(QAC.getQuestionString());
		
		TextView upvoteCount = (TextView) findViewById(R.id.upvoteCount);
		upvoteCount.setTypeface(customTF);
		upvoteCount.setText(Integer.toString(QAC.getVotes()));
		
	}
	/*
	 * caches question if it hasnt been cached already
	 */
	private void cache(){
		ArrayList<Question> cacheList = dc.getData(cache);
		for(Question CacheQ: cacheList){
			if(CacheQ.getId()==myQuestion.getId()){
				return;
			}
		}
		dc.addData(myQuestion, cache);
	}
	/*
	 * sets the button for favourite based on if its already saved.
	 * 
	 */
	private void isFavourited(int qID){
		
		ArrayList<Question> favList = dc.getData(favourites);
		boolean favourited = false;
		for (Question QListId: favList){
			if (QListId.getId()==qID){
				Log.i("IsFavID",String.valueOf(QListId.getId()));
				Log.i("QID", String.valueOf(qID));
				favourited =true;
				break;
			}
		}
		if(favourited){
			setButtonToClicked();
		}
		
	}
	/*
	 * set button to clicked for favourites
	 */
	private void setButtonToClicked(){
		
		ImageButton favButton = (ImageButton) findViewById(R.id.favoriteButton);
		favButton.setImageResource(android.R.drawable.btn_star_big_on);
		favButton.setClickable(false);
		favButton.setEnabled(false);
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
			//Toast.makeText(this, "no picture, Id:" + myQuestion.getId(), Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * Populates the ReplyView of questions with questions in the order 
	 * specified by the user (Sorted by date on default)
	 */

	public void populateReplyView()
	{	
    	List<String> replyHeader = new ArrayList<String>(); // re-initialize replyHeader 
		prepareReplyData(replyHeader, replyChild);

		try {
			expListView = (ExpandableListView) findViewById(R.id.questionRepliesExpandable);
			// setting replyAdapter
			replyAdapter = new InsertReplyAdapter(this, replyHeader, replyChild);
			expListView.setAdapter(replyAdapter);
        }
		catch(Exception e) {
            System.out.println("Errrr +++ " + e.getMessage());
        }
	}
	
	/**
	 * Populates the ListView of answers with answers in the order 
	 * specified by the user (Sorted by date on default)
	 */
	public void populateAnswerView()
	{	
		QAController QAC = new QAController(myQuestion);
		ArrayList<Answer> answers = QAC.getAnswersByDate();
		listView = (ListView) findViewById(R.id.answersListView);
		// setting arrayAdapter
		ArrayAnswerAdapter arrayAnswerAdapter = new ArrayAnswerAdapter(this, answers);
		listView.setAdapter(arrayAnswerAdapter);
	}
	
	/**
	 * Add the question being viewed to the read later list which makes the
	 * question available for reading offline. 
	 * @param v The view that was clicked on
	 */
	public void readLater(View v){
		
		ArrayList<Question> readList = dc.getData(readlater);
		if(hasBeenRead){
			Toast.makeText(this, "This has already been added", Toast.LENGTH_SHORT).show();
			return;
		}
		for(Question Q:readList){
			if(Q.getId()==myQuestion.getId()){
				hasBeenRead=true;
				Toast.makeText(this, "This has already been added", Toast.LENGTH_SHORT).show();
				return;
			}
		}
		dc.addData(myQuestion, readlater);
		return;
		
		// Change button image after question has been added to favorites
		
	}
	
	/**
	 * Add the question being viewed to the favorite list which makes the
	 * question available for reading offline. 
	 * @param v The view being clicked on
	 */
	public void addToFavorite(View v){
		Log.i("VQAA", "breaks after");
		dc.addData(myQuestion,favourites);
		Toast.makeText(this, "Saved to Favourites!", Toast.LENGTH_SHORT).show();
		setButtonToClicked();
			
		
	}
	
	/**
	 * Upvotes the question. First the question is retrieved through the id, then
	 * it is upvoted.
	 * @param v The view that is being clicked on.
	 */
	public void upvote(View v){
		
		AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
		Question myQuestion = AQC.getQuestionById(qId); // if we have this above why is gotten again??
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
	public void submitAnswer(View v) {
		// fetch answer string
		EditText answerEditText = (EditText) findViewById(R.id.answerQuestionEditText);
		String answerText = answerEditText.getText().toString();
		a = new Answer(answerText);
		answerEditText.setText("");

		// MVC to handle singleton answer		
		AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
		myQuestion = AQC.getQuestionById(qId);
		QAController QAC = new QAController(myQuestion);
		QAC.addAnswer(a);

		Toast.makeText(this, "Your answer has been added", Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * Add Reply to the Question
	 * @param v View that was clicked on
	 */
	public void submitReplyQuestion(View v){
		// get reply string
		EditText replyEditText = (EditText) findViewById(R.id.replyQuestionEditText);
		String replyText = replyEditText.getText().toString();
		Reply reply = new Reply(replyText);
		replyEditText.setText("");

		// get current question		
		AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
		myQuestion = AQC.getQuestionById(qId);
		
		// add reply to question with QAController		
		QAController QAC = new QAController(myQuestion);
		QAC.addReply(reply);
		
		// dynamic update of replies list
		populateReplyView();
		ExpandableListView listView = (ExpandableListView) findViewById(R.id.questionRepliesExpandable);
		listView.expandGroup(0);
		replyAdapter.notifyDataSetChanged();
		Toast.makeText(this, "Your reply has been added", Toast.LENGTH_SHORT).show();

	}
	
	@Override
	public void update() {
		replyAdapter.notifyDataSetChanged();
	}
	
	/** populates header and children of expandable view
	 * for replies to a Question
	 * @param replyHeader - header of expandable view
	 * @param replyChild - HashMap of expandable view
	 */
    private void prepareReplyData(List<String> replyHeader, HashMap<String, List<String>> replyChild) {
        replyHeader.add("Replies");
        List<String> replies = new ArrayList<String>();
    	
        int repliesNumber = myQuestion.getRepyCount();
    	if (repliesNumber == 0) {
    		// no replies to attach
            replies.add("No replies ... ");
    	}
    	else {
    		// populate replies attached to Question
    		for (Reply reply : myQuestion.getReplies()) {
    			replies.add(reply.getReplyString());
    		}
    	}
    	// add the last child that has reply_add view 
        replies.add(" ");

        replyChild.put(replyHeader.get(0), replies); // Header, Child data 
    }
    
	/** populates headers and children of expandable view
	 * for Answers to a Question
	 * @param answerHeader - header of expandable view
	 * @param answerChild - HashMap of expandable view
	 */
    private void prepareAnswerData(List<String> answerHeader, HashMap<String, List<String>> answerChild) {

    	for (Answer answer : myQuestion.getAnswers()) {
    		answerHeader.add(answer.getAnswerString());
        	List<String> replies = new ArrayList<String>();
    		
    	}

        //replyChild.put(replyHeader.get(0), replies); // Header, Child data 
    }
    
    
}