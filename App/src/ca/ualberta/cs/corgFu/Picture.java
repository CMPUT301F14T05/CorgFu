package ca.ualberta.cs.corgFu;

import java.io.Serializable;

import ca.ualberta.cs.corgFuModels.Answer;
import ca.ualberta.cs.corgFuModels.Question;
import android.graphics.Bitmap;
import android.util.Log;

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
public class Picture implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3443016608644060514L;
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
		Bitmap resizedImage = getResizedBitmap(image, 64);
		question.setImage(resizedImage);

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
		return true;
	}
	
	public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
		int outWidth;
		int outHeight;
		int inWidth = image.getWidth();
		int inHeight = image.getHeight();
		if(inWidth > inHeight){
		    outWidth = maxSize;
		    outHeight = (inHeight * maxSize) / inWidth; 
		} else {
		    outHeight = maxSize;
		    outWidth = (inWidth * maxSize) / inHeight; 
		}
		Bitmap resizedBitmap = Bitmap.createScaledBitmap(image, outWidth, outHeight, false);
		return resizedBitmap;
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