package ca.ualberta.cs.corgFuViews;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFu.ConnectedManager;
import ca.ualberta.cs.corgFu.Picture;
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
 * @version 3.0 Nov.21/2014
 * 
 */
public class MainActivity extends Activity
{
	/**
	 * RESULT_LOAD_IMAGE indicates whether an Image has been successfully loaded
	 */
	private static int RESULT_LOAD_IMAGE = 111;
	private static final String toBePushed = "Unpushed.save";
	
	public static Context context;

	DataController DC;
	ConnectedManager connected;
	boolean isConnected;
	/**onCreate() sets up the display of the Activity, it makes sure that all of the desired text is set to the correct typeface.*/
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		context = getBaseContext();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		connected =ConnectedManager.getInstatnce();
		connected.setContext(context);
		Button myProfileButton = (Button)findViewById(R.id.MyProfileButton);//button to click to go to your user profile
		Button answersButton = (Button)findViewById(R.id.GoToAnswer);//button to click to go to the list of previously asked questions
		TextView TV = (TextView)findViewById(R.id.MainQuestionText);//grabs the text view to be displayed
		
		Typeface customTypeFace = Typeface.createFromAsset(getAssets(), "fonts/26783.ttf");//creates a custom typeface from the textview
		DC = new DataController();
		myProfileButton.setTypeface(customTypeFace);//sets the button to obtain that specific typeface
		answersButton.setTypeface(customTypeFace);//sets the typeface for another button
		TV.setTypeface(customTypeFace);//sets the textview to obtain that specific typeface
		attemptToPushOfflineContent();
	}
	protected void onResume(){
		super.onResume();
		DC = new DataController();
		attemptToPushOfflineContent();
	}
	public void attemptToPushOfflineContent(){
		Log.i("Main Attempt", "start push");
		
		boolean isConnect = connected.isConnexted();
		if (isConnect )
		{
			Log.i("mainConnected", "going to DC to push");
			DC.pushOfflineContent();
		}
		
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
		Question q = new Question(question); // creates a new question object
		
		AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
		AQC.addQuestion(q);

		DC.addData(q, "MyQuestions.save");
		
		// invokes dialog for adding picture
		invokeAddPictureDialog(q.getId());
		// buggy method isConnexted();
		//boolean  isConnected = connected.isConnexted();
		isConnected = true;
		if (isConnected)
		{
			Toast.makeText(context, "question added successfully", Toast.LENGTH_SHORT).show();
			 // creates a new question object
			DC.getQuestionById(q.getId(), "MyQuestions.save");
			q.setIsPushed(true);
			AQC = AllQuestionsApplication.getAllQuestionsController();
			AQC.addQuestion(q);
		}else{
			Toast.makeText(context, "No Connection, Pushing when Connection is made", Toast.LENGTH_SHORT).show();
			
			DC.addData(q, toBePushed);
		}
	}

	/** Prepare and invoke dialog for adding
	 *  picture to Question
	 * @param qId - Id of the Question
	 */
	private void invokeAddPictureDialog(final int qId) {
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

                 	   startActivityForResult(i, qId);
                   }

               });
        
        builder.setNegativeButton(R.string.no_button_text, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User refused to add a picture
                   }
               });
	    
	    builder.show();
		
	}
	
	/** Prepare and invoke dialog for adding
	 *  smaller picture to Question
	 * @param qId - Id of the Question
	 */
	private void invokeAddSmallerPictureDialog(final int qId) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        final View v = inflater.inflate(R.layout.dialog_add_smaller_picture, null);

        builder.setView(v);

        builder.setTitle("Picture exceeds 64KB");
        
        builder.setPositiveButton(R.string.yes_button_text, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   // Go to another activity that fetches pictures from Android Media
                       // User wants to add a picture, fetch it from Image Gallery
                 	   Intent i = new Intent(Intent.ACTION_PICK, Media.EXTERNAL_CONTENT_URI);

                 	   startActivityForResult(i, qId);
                   }

               });
        
        builder.setNegativeButton(R.string.no_button_text, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User refused to add a picture
                   }
               });
	    
        builder.setIcon(android.R.drawable.ic_dialog_alert);
	    
	    builder.show();
		
	}
	
    /**
     *  Function onActivityResult fetches image from Media Activity
     *  @param requestCode - Question Id
     *  @param resuldCode - result of resolving external activity
     *  @param data - intent that contains image path
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

    			//AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
    			Question q = DC.getQuestionById(requestCode, "MyQuestions.save");
    			q.setImage(attachedPic);
    			DC.addData(q, "MyQuestions.save");
    			AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
    			AQC.addQuestion(q);
    			
    			Toast.makeText(this, "Picture is added", Toast.LENGTH_SHORT).show();

    		}
    		else {
    			Toast.makeText(this, "image is too large", Toast.LENGTH_LONG).show();
    			// Image is too large. Invoke another dialog asking to add another image
    			invokeAddSmallerPictureDialog(requestCode);

    		}

    	}
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
