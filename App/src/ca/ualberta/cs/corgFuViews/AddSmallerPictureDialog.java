package ca.ualberta.cs.corgFuViews;

import ca.ualberta.corgfuapp.R;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Dialog Fragment responsible for asking a user if he/she wants to add
 * smaller picture. Appears only after adding too large picture
 * through the AddPictureDialogFragment
 * @author Anthony Wu
 * @author Oleksii Shevchenko
 * @see ca.ualberta.cs.corgFuModels.Question
 * @see ca.ualberta.cs.corgFuModels.Answer
 * @see ca.ualberta.cs.corgFu.Picture
 * @see ca.ualberta.cs.corgFuViews.ViewQusetionAndAnswers
 */
public class AddSmallerPictureDialog extends DialogFragment {
    @SuppressLint("InflateParams")
	
	@Override
	/**
	 * creates custom alert dialog
	 */
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View v = inflater.inflate(R.layout.dialog_add_smaller_picture, null);
        
        builder.setView(v);
        
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        
        builder.setTitle("Your image exceeds 64KB");
        
        builder.setPositiveButton(R.string.yes_button_text, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   // Go to another activity that fetches pictures from Android Media
                	   goToAddPicture();
                   }

               });
        
        builder.setNegativeButton(R.string.no_button_text, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User refused to add smaller picture, go back to MainActivity
                	   Intent intent = new Intent(getActivity(), MainActivity.class);
                	   startActivity(intent);
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

