package ca.ualberta.cs.corgFuViews;

import java.io.File;

import ca.ualberta.corgfuapp.R;
import ca.ualberta.corgfuapp.R.layout;
import ca.ualberta.corgfuapp.R.menu;
import ca.ualberta.cs.corgFuModels.Answer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class AttachPicture extends Activity {
	
	// mock instance of class Answer for testing
	Answer answerTest = new Answer("Testing");
	
	private static int RESULT_LOAD_IMAGE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attach_picture);

		ImageButton button = (ImageButton) findViewById(R.id.yes_button);
		OnClickListener listener = new OnClickListener() {
			public void onClick(View v) {
				// fetch picture from Image Gallery
				Intent i = new Intent(
						Intent.ACTION_PICK, 
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				
				startActivityForResult(i, RESULT_LOAD_IMAGE);
			}
		};
		button.setOnClickListener(listener);
	}
	
    @Override
    // Once user will select an image, we need to handle the data 
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        // 1. fetches picture from image directory  
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
 
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
 
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            
            // 2. String picturePath now contains the path of the selected Image
            Picture genericPic = new Picture(); 
            
            // 3. Attach the Picture and see if it meets requirements 
            Bitmap attachedPic = BitmapFactory.decodeFile(picturePath);
        	
            // need to implement 
            //genericPic.attachImageAns(answerTest, attachedPic);
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.attach_picture, menu);
		return true;
	}

}
