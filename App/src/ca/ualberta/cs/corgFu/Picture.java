package ca.ualberta.cs.corgFu;

import ca.ualberta.cs.corgFuModels.Answer;
import ca.ualberta.cs.corgFuModels.Question;
import android.graphics.Bitmap;

/**
 * This is an abstract class that performs all the needed
 * methods required for handling pictures which includes 
 * determining whether or not the image meets the size requirements
 * and finally associating the image to either the Answer/Question
 * objects. 
 * 
 * @author Anthony Wu
 * @author Oleksii Shevchenko
 * @see ca.ualberta.cs.corgFuModels.Question
 * @see ca.ualberta.cs.corgFuModels.Answer
 */
public class Picture {

	/**MAX_PICTURE_SIZE is the maximum size of the picture in bytes*/
	private static int MAX_PICTURE_SIZE = 65536;
	
	// via an intent in VIEW 
	// attachImage method modifies Model attributes "hasPic" 
	// hasPic = True means Image successfully attached; vice versa if otherwise
	
	/**
	 * Attaches an Image to the Question provided that it also meets the size requirements.  
	 * @param question
	 * @param image
	 */
	public void attachImageQuest(Question question, Bitmap image) {
	  /* 1. fetches picture from camera directory 
		 2. fetches picture from another directory
		 3. prescribe a default image if no images are supplied/option aborted */
		
		// Finally, attach Image meets requirements 
		if (smallPicture(image)) {
			question.setImage(image);
		}
	}

	/**
	 * Attaches an Bitmap Image to the Answer provided that it also meets the size requirements.  
	 * @param Ans
	 * @param Image
	 */
	public void attachImageAns(Answer Ans, Bitmap Image) {
		if (smallPicture(Image)) {
			Ans.setPicture(Image);
		}
	}
	
	/**
	 * Determines whether or not the Bitmap Image supplied is small and meets the size requirements. 
	 * @param Image
	 * @return Boolean indicating whether the Bitmap Image attached meets or fails the size requirements.  
	 */
	public static Boolean smallPicture(Bitmap Image) {
		// attached picture meets requirements
		if ((byteSizeOf(Image) >0) && (byteSizeOf(Image) <= MAX_PICTURE_SIZE)) {
			return true;
		}
		// attached picture fails requirements 
		return false;
	}
	
	/**
	 * Determines the number of bytes of an Bitmap Image. 
	 * @param bitmap
	 * @return The byte size of the supplied Bitmap Image (kB) 
	 */
	public static int byteSizeOf(Bitmap bitmap) {
		int imageSize = bitmap.getByteCount(); 
		return imageSize;
	}
}