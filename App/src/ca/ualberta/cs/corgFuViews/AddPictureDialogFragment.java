package ca.ualberta.cs.corgFuViews;

import ca.ualberta.corgfuapp.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

public class AddPictureDialogFragment extends DialogFragment {
	private static int RESULT_LOAD_IMAGE = 1;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.add_picture_option)
               .setPositiveButton(R.string.yes_button_text, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User wants to add a picture, fetch it from Image Gallery
                	   Intent i = new Intent(
                			   Intent.ACTION_PICK, 
                			   android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
       				
                	   startActivityForResult(i, RESULT_LOAD_IMAGE);
                   }

               })
               .setNegativeButton(R.string.no_button_text, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User refused to add a picture
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
    
    
}
