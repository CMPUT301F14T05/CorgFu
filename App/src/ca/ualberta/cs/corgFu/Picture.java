package ca.ualberta.cs.corgFu;

import ca.ualberta.cs.corgFuModels.Answer;
import ca.ualberta.cs.corgFuModels.Question;
import android.graphics.Bitmap;

public class Picture {
	// max size of the picture in bytes
	private static int MAX_PICTURE_SIZE = 65536;
	
	// via an intent in VIEW 
	// attachImage method modifies Model attributes "hasPic" 
	// hasPic = True means Image successfully attached; vice versa if otherwise
	public void attachImageQuest(Question question, Bitmap image) {
	  /* 1. fetches picture from camera directory 
			
		 2. fetches picture from another directory
		
		 3. prescribe a default image if no images are supplied/option aborted */
		
		// Finally, attach Image meets requirements 
		if (smallPicture(image)) {
			question.setImage(image);
		}
	}

	public void attachImageAns(Answer Ans, Bitmap Image) {
		if (smallPicture(Image)) {
			Ans.setPicture(Image);
		}
	}
	
	public Boolean smallPicture(Bitmap Image) {
		// attached picture meets requirements
		if (byteSizeOf(Image) <= MAX_PICTURE_SIZE) {
			return true;
		}
		// attached picture fails requirements 
		return false;
	}
	
	public int byteSizeOf(Bitmap bitmap) {
		int imageSize = bitmap.getByteCount(); 
		return imageSize;
	}
}