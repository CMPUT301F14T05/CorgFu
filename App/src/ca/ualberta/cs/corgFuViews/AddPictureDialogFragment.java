package ca.ualberta.cs.corgFuViews;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFu.Picture;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

/**
 * Activity (Dialog Fragment) that is responsible for prompting the user the 
 * choice whether of attaching an image to their answer/question and if yes,
 * redirect them to their android picture gallery to select and image. It
 * fetches the image path from the gallery and links it to an image view 
 * supplied in ViewQuestionAndAnswer Activity. A small message "Your Picture
 * is attached" will indicate the image chosen by the user has been successfully
 * loaded to the question/answer. Else, this indicates an image size error and a message
 * displaying "too large" or "too small" will be displayed. 
 * @author Anthony Wu
 * @author Oleksii Shevchenko
 * @see ca.ualberta.cs.corgFuModels.Question
 * @see ca.ualberta.cs.corgFuModels.Answer
 * @see ca.ualberta.cs.corgFu.Picture
 * @see ca.ualberta.cs.corgFuViews.ViewQusetionAndAnswers
 */
public class AddPictureDialogFragment extends DialogFragment {
	/**RESULT_LOAD_IMAGE indicates whether an Image has been successfully loaded*/
	private static int RESULT_LOAD_IMAGE = 1;
    //@SuppressLint("InflateParams")
	
	@Override
	/**
	 * 
	 */
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View v = inflater.inflate(R.layout.dialog_add_picture, null);
        
        builder.setView(v);
        
        builder.setPositiveButton(R.string.yes_button_text, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User wants to add a picture, fetch it from Image Gallery                	   
                	   Intent i = new Intent(Intent.ACTION_PICK, Media.EXTERNAL_CONTENT_URI);

                	   startActivityForResult(i, RESULT_LOAD_IMAGE);
                   }

               });
        
        builder.setNegativeButton(R.string.no_button_text, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User refused to add a picture
                   }
               });
        
    	//Bitmap attachedPic = null;
    	//attachedPic = Bitmap.createScaledBitmap(attachedPic, 50, 50, false);
		//((MainActivity)getActivity()).q.setImage(attachedPic);
        
        // Create the AlertDialog object and return it
        return builder.create();
    }
	

    @Override
    /**
     * 
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);

    	//fetches picture from image directory  
    	if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && null != data) {

    		Uri selectedImage = data.getData();
    		InputStream is = null;
    		Bitmap attachedPic = null;
			try {
				is = getActivity().getContentResolver().openInputStream(selectedImage);
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
    			Toast.makeText(getActivity(), "image has proper size", Toast.LENGTH_SHORT).show();
    			((MainActivity)getActivity()).q.setImage(attachedPic);
    		}
    		else {
    			// Image is too large. Invoke another dialog asking to add another image
    			Toast.makeText(getActivity(), "image is too large", Toast.LENGTH_SHORT).show();
    		}

    	}
    }

}

