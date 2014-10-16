package ca.ualberta.cs.corgfuapp.test;

import android.graphics.Bitmap;
import ca.ualberta.cs.corgFuModels.Answer;
import junit.framework.TestCase;

public class AttachPictureAnswerTest extends TestCase {

	private Bitmap testImage = BogoPicGen.generateBitmap(1,1);
	
	public void testAttachPicAns(){
		Answer mA1 = new Answer("Answer 1");
		mA1.setImage(testImage);
		assertNotNull("Testing to see if the image is attached",mA1.getImage());
	}
}
