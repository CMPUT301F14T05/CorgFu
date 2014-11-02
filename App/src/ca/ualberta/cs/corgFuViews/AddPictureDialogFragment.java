package ca.ualberta.cs.corgFuViews;

import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFu.Picture;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.LayoutInflater;
import android.view.View;


public class AddPictureDialogFragment extends DialogFragment {
	private static int RESULT_LOAD_IMAGE = 1;
    @Override
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
        
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);

    	//fetches picture from image directory  
    	if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && null != data) {
    		Uri selectedImage = data.getData();
    		String[] filePathColumn = { MediaStore.Images.Media.DATA };

    		Cursor cursor =  ((MainActivity)(AddPictureDialogFragment.this.getActivity())).getContentResolver().query(selectedImage,
    				filePathColumn, null, null, null);
    		cursor.moveToFirst();

    		int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
    		String picturePath = cursor.getString(columnIndex);
    		cursor.close();

    		Bitmap attachedPic = BitmapFactory.decodeFile(picturePath);

    		
    		if (Picture.smallPicture(attachedPic)) {
    			// Add image to the question
    			((MainActivity)(AddPictureDialogFragment.this.getActivity())).q.setImage(attachedPic);
    		}
    		else {
    			// Image is too large or if a void picture has been supplied, invoke another dialog asking to add another image
    		}

    	}
    }

}

