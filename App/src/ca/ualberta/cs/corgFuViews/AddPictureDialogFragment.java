package ca.ualberta.cs.corgFuViews;

import ca.ualberta.corgfuapp.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
    @SuppressLint("InflateParams")
	
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
                	   // Go to another activity that fetches pictures from Android Media
                	   goToAddPicture();
                   }

               });
        
        builder.setNegativeButton(R.string.no_button_text, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User refused to add a picture
                   }
               });
        
        // Create the AlertDialog object and return it
        return builder.create();
    }
    
	/**
	 * goToAddPicture() creates a new intent for the desired activity we wanted to move to.
	 * Taking this intent we start the AddPicture activity.
	 */
	public void goToAddPicture(){
		Intent intent = new Intent(getActivity(), AddPictureActivity.class);
		startActivity(intent);
	}

}

