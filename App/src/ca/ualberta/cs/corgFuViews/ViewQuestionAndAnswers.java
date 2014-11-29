package ca.ualberta.cs.corgFuViews;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
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
import ca.ualberta.cs.corgFu.InsertAnswerAdapter;
import ca.ualberta.cs.corgFu.IView;
import ca.ualberta.cs.corgFu.InsertAnswerAdapter;
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
	protected Answer myAnswer; //most recent Reply added by the user
	private int aId = 0;
	
    /** ReplyList view with answers to a question */
    ListView answerListView;

    /** Custom arrayAdapter to handle list of Answers */
    InsertAnswerAdapter arrayAnswerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_question_and_answers);
		hasBeenRead = false;
		getQuestion();
		setFont();
		//setPicture();
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
		
		UserName user = UserName.getInstance();
		
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
		
		Button submit = (Button) findViewById(R.id.submitAnswerButton);
		submit.setTypeface(customTF);
	}

	/** 	
	  * * Updates imageView with a question picture if this question has a picture 	
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

	public void populateAnswerView()
	{	
		QAController QAC = new QAController(myQuestion);
		ArrayList<Answer> answers = QAC.getAnswersByUpVotes();
		answerListView = (ListView) findViewById(R.id.answerList);
		arrayAnswerAdapter = new InsertAnswerAdapter(this, answers);
		answerListView.setAdapter(arrayAnswerAdapter);

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
	 * Upvotes the Answer. First the Answer is retrieved through the id, then
	 * it is upvoted.
	 * @param v The view that is being clicked on.
	 */
	public void upvoteAns(View v){
		QAController QAC = new QAController(myQuestion);

		ArrayList<Answer> answers = QAC.getAnswers();
		
		int indx = answerListView.getPositionForView(v);
		answers.get(indx).upvote();

		Typeface customTF = Typeface.createFromAsset(getAssets(), "fonts/26783.ttf");
		TextView upvoteCount = (TextView) findViewById(R.id.upvoteAnswerCount);
		
		upvoteCount.setTypeface(customTF);

		upvoteCount.setText(Integer.toString(QAC.getAnswers()
				.get(indx).getVotes()
		));

		
		Button upvoteAnsButton = (Button) v;
		upvoteAnsButton.setClickable(false);
		upvoteAnsButton.setEnabled(false);
		

		arrayAnswerAdapter.notifyDataSetChanged();
	}
	
	/**
	 * Adds an Answer to the question when the user clicks the submit answer button
	 * @param v The view that was clicked on
	 */
	public void submitAnswer(View v) {
		 
		EditText answerEditText = (EditText) findViewById(R.id.AnswerEditText);
		String answerText = answerEditText.getText().toString();
		myAnswer = new Answer(answerText);
		answerEditText.setText("");
		
		QAController QAC = new QAController(myQuestion);
		QAC.addAnswer(myAnswer);
		
		populateAnswerView();
		Toast.makeText(this, "Your Reply has been added", Toast.LENGTH_SHORT).show();
	}

	/** Prepare and invoke dialog for adding 	
	 * picture to Answer 	
	 * @param aId - Id of the Answer 	
	 */ 	
	private void invokeAddPictureDialog(final int aId) { 	
		// Use the Builder class for convenient dialog construction 	
		AlertDialog.Builder builder = new AlertDialog.Builder(this); 	
		
		LayoutInflater inflater = LayoutInflater.from(this); 	
		final View v = inflater.inflate(R.layout.dialog_add_picture, null); 	
		
		builder.setView(v); 	
		
		builder.setPositiveButton(R.string.yes_button_text, new DialogInterface.OnClickListener() { 	
			public void onClick(DialogInterface dialog, int id) { 	
				// Go to another activity that fetches pictures from Android Media 	
				// User wants to add a picture, fetch it from Image Gallery 	
				Intent i = new Intent(Intent.ACTION_PICK, Media.EXTERNAL_CONTENT_URI); 	
				
				startActivityForResult(i, aId); 	
			}
			
		}); 	
		
		builder.setNegativeButton(R.string.no_button_text, new DialogInterface.OnClickListener() { 	
			public void onClick(DialogInterface dialog, int id) { 	
				// User refused to add a picture 	
			} 	
		}); 	
		
		builder.show(); 	
		}
	
	/** 	
	 * Function onActivityResult fetches image from Media Activity 	
	 * @param requestCode - Question Id 	
	 * @param resuldCode - result of resolving external activity 	
	 * @param data - intent that contains image path 	
	 */ 	
	public void onActivityResult(int requestCode, int resultCode, Intent data) { 	
		super.onActivityResult(requestCode, resultCode, data); 	
		
		//fetches picture from image directory 	
		if (resultCode == Activity.RESULT_OK && null != data) { 	
			
			Uri selectedImage = data.getData(); 	
			InputStream is = null; 	
			Bitmap attachedPic = null; 	
			try { 	
				is = this.getContentResolver().openInputStream(selectedImage); 	
				attachedPic = BitmapFactory.decodeStream(is); 	
				
			} catch (FileNotFoundException e) { 	
				// Invalid URI exception 	
				e.printStackTrace(); 	
			}
			
			try {	
				is.close(); 	
			} catch (IOException e) { 	
				// Attempt to close non-existing InputStream 	
				e.printStackTrace(); 	
			}
			
			if (Picture.smallPicture(attachedPic)) {
				// Add image to the question
				Question q = dc.getQuestionById(qId, "MyQuestions.save"); 	
				Answer answer = q.getAnswerById(requestCode); 	
				answer.setPicture(attachedPic); 	
				dc.addData(q, "MyQuestions.save"); 	
				AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController(); 	
				AQC.addQuestion(q); 	
				
				Toast.makeText(this, "Picture is added", Toast.LENGTH_SHORT).show(); 	
				
			}
			else {	
				Toast.makeText(this, "image is too large", Toast.LENGTH_LONG).show(); 	
				// Image is too large. Invoke another dialog asking to add another image 	
			} 	

			// Dynamically update the listView 	
			populateAnswerView(); 	
			arrayAnswerAdapter.notifyDataSetChanged(); 	

		} 	
	}
	
	/**
	 * Links to QuestionAndReplies View when clicked 
	 * @param v The view that was clicked on
	 */
	public void goToQReplies(View v) {
		int qId = myQuestion.getId();
		
		Toast.makeText(this, "Going to QReplies", Toast.LENGTH_SHORT).show();
		
		Intent launch = new Intent(this, ViewQuestionAndReplies.class);
    	launch.putExtra("@string/idExtraTag", qId);
		startActivity(launch);
	}
	
	/**
	 * Links to AnswerAndReplies View when clicked 
	 * @param v The view that was clicked on
	 */

	public void goToAReplies(View v) {
		QAController QAC = new QAController(myQuestion);
		ArrayList<Answer> answers = QAC.getAnswers();
		
		int indx = answerListView.getPositionForView(v);
		myAnswer = answers.get(indx);
		
		int aId = myAnswer.getId();
		int qId = myQuestion.getId();
		
		Toast.makeText(this, "Going to AReplies", Toast.LENGTH_SHORT).show();
		
		Intent launch = new Intent(this, ViewAnswerAndReplies.class);
		
    	launch.putExtra("@string/idQuestionTag", qId);
    	launch.putExtra("@string/idAnswerTag", aId);
		startActivity(launch);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}