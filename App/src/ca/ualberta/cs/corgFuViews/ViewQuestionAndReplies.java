package ca.ualberta.cs.corgFuViews;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;

import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.LayoutInflater;
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
import ca.ualberta.cs.corgFu.IView;
import ca.ualberta.cs.corgFu.InsertReplyAdapter;
import ca.ualberta.cs.corgFu.UserName;
import ca.ualberta.cs.corgFu.Picture;
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
public class ViewQuestionAndReplies extends Activity implements IView
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
	protected Reply reply; //most recent Reply added by the user
	
    /** ReplyList view with replies to a question */
    ListView replyListView;
    /** Custom arrayAdapter to handle list of Answers */
    InsertReplyAdapter arrayReplyAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_question_and_replies);
		hasBeenRead = false;
		getQuestion();
		setFont();
		//setPicture();
		populateReplyView();
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
		
		TextView date = (TextView) findViewById(R.id.date);
		date.setText(myQuestion.stringDate());
		
		TextView author = (TextView) findViewById(R.id.author);
		author.setText(myQuestion.stringAuthor());
		
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
		
		Button submit = (Button) findViewById(R.id.submitReplyButton);
		submit.setTypeface(customTF);
	}
	
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
		QAController QAC = new QAController(myQuestion);
		ArrayList<Reply> replies = QAC.getReplies();
		replyListView = (ListView) findViewById(R.id.replyList);
		arrayReplyAdapter = new InsertReplyAdapter(this, replies);
		replyListView.setAdapter(arrayReplyAdapter);
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
	 * Adds an reply to the question when the user clicks the submit answer button
	 * @param v The view that was clicked on
	 */
	public void submitReply(View v) {
		 
		EditText replyEditText = (EditText) findViewById(R.id.ReplyEditText);
		String replyText = replyEditText.getText().toString();
		
		int replyLen = replyText.length();
		
		if (replyLen <= 0||isBlank(replyText) == true) {
			Toast.makeText(getApplicationContext(), "Reply can't be empty.", Toast.LENGTH_LONG).show();
		}else{
			reply = new Reply(replyText);
			replyEditText.setText("");

			QAController QAC = new QAController(myQuestion);
			QAC.addReply(reply);

			populateReplyView();
			Toast.makeText(this, "Your Reply has been added", Toast.LENGTH_SHORT).show();

			UserName user = UserName.getInstance();
			reply.setAuthor(user.getUserName());
		}
	}
	
	/**
	 * Links to QuestionAndAnswers View when clicked 
	 * @param v The view that was clicked on
	 */
	public void gotoAnswer(View v) {
		int qId = myQuestion.getId();
		Toast.makeText(this, "Going to Answers", Toast.LENGTH_SHORT).show();
		Intent launch = new Intent(this, ViewQuestionAndAnswers.class);
    	launch.putExtra("@string/idExtraTag", qId);
		startActivity(launch);

	}

	@Override
	public void update() {
		arrayReplyAdapter.notifyDataSetChanged();
	}    
	
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
	
}