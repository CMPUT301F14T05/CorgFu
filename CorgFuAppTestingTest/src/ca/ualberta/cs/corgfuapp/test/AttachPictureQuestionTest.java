package ca.ualberta.cs.corgfuapp.test;

import android.graphics.Bitmap;
import ca.ualberta.cs.corgFuModels.Question;
import junit.framework.TestCase;

public class AttachPictureQuestionTest extends TestCase {

	private Bitmap testImage = BogoPicGen.generateBitmap(1,1);
	
	public void testAttachPicAns(){
		Question mQ1 = new Question("Question 1");
		mQ1.setImage(testImage);
		assertNotNull("Testing to see if the image is attached",mQ1.getImage());
	}
}
