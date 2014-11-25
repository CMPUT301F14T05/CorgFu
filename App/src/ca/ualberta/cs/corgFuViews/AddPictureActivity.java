package ca.ualberta.cs.corgFuViews;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFu.Picture;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.Question;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class AddPictureActivity extends Activity {
	
	/**
	 * RESULT_LOAD_IMAGE indicates whether an Image has been successfully loaded
	 */
	private static int RESULT_LOAD_IMAGE = 111;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        // User wants to add a picture, fetch it from Image Gallery
 	   Intent i = new Intent(Intent.ACTION_PICK, Media.EXTERNAL_CONTENT_URI);

 	   startActivityForResult(i, RESULT_LOAD_IMAGE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_picture, menu);
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
	
    @Override
    /**
     *  Function onActivityResult expects requestCode of successfully loaded image
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);

    	//fetches picture from image directory  
    	if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && null != data) {

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
    			Toast.makeText(this, "Picture is added", Toast.LENGTH_SHORT).show();
    			//((MainActivity)getActivity()).q.setImage(attachedPic);
    			//AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
    			// Pick the most recently added question
    			//Question q = AQC.getRecentQuestion();
    			//q.setImage(attachedPic);
    			
    			// After picture is added go back to MainActivity
    			Intent i = new Intent(this, MainActivity.class);
    			//i.putExtra(name, value)
    			startActivity(i);
    		}
    		else {
    			Toast.makeText(this, "image is too large", Toast.LENGTH_SHORT).show();
    			// Image is too large. Invoke another dialog asking to add another image
    			AddSmallerPictureDialog addPictureDialog = new AddSmallerPictureDialog();
    			addPictureDialog.show(getFragmentManager(), null);
    			
    			setContentView(R.layout.activity_add_picture);

    		}

    	}
    }
}
