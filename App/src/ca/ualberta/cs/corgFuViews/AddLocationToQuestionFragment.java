package ca.ualberta.cs.corgFuViews;

import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFu.UserName;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/** This is a custom Dialog Fragment that handles upon login whether or not
 * a user would like to attach their location to any questions they post. 
 * It handles both whether they'd like to attach to questions, or not.
 * 
 * @author Alex Makepeace
 *
 *@version 1.0 Nov. 21/2014
 */

public class AddLocationToQuestionFragment extends DialogFragment{
	
	/**This is a  method that creates the dialog box for choosing
	 * whether the user is going to attach their location to questions.
	 * The method creates the appropriate view for the dialog box and 
	 * the appropriate options that the user may select.It is set to take the user
	 * to the main ask questions page after they make their choice.
	 * 
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the Builder class for convenient dialog construction
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    
	    LayoutInflater inflater = LayoutInflater.from(getActivity());
	    View v = inflater.inflate(R.layout.dialog_attach_location, null);
	    
	    builder.setView(v);

	    builder.setPositiveButton(R.string.attach_location_text, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
         	   //If the user would like to attach their location to questions this triggers a boolean on the username
            	UserName myUser = UserName.getInstance();
            	myUser.attachLocation(true);
            	if (myUser.getLocation() == null){
            		toCreateLocation();
            	}
            }
        });
	    builder.setNegativeButton(R.string.do_not_attach_location, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User refused to attach location to the questions they create
            	UserName myUser = UserName.getInstance();
            	myUser.attachLocation(false);
            	toMain();
            }
        });
	    
	    return builder.create();
	}
	
	public void toCreateLocation(){
		Intent intent = new Intent(getActivity(), CreateLocationActivity.class);
		startActivity(intent);
	}
	
	/**The toMain() method takes the user to the MainActivity 
	 * screen after they decide whether they'd like to attach
	 * their location to questions.
	 */
	private void toMain (){
		Intent intent = new Intent(getActivity(), MainActivity.class);
		startActivity(intent);
	}
}
