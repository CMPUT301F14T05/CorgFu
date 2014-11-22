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

public class AddLocationToQuestionFragment extends DialogFragment{
	
	
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
            	toMain();
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
	
	private void toMain (){
		Intent intent = new Intent(getActivity(), MainActivity.class);
		startActivity(intent);
	}
}
