package ca.ualberta.cs.corgfuapp.test;

import ca.ualberta.cs.corgFu.Picture;
import junit.framework.TestCase;
import android.graphics.Bitmap;

public class SmallPicturesOnlyTest extends TestCase {
	
	public void testSmallPicturesOnly(){
		Bitmap bigBMP = BogoPicGen.generateBitmap(400, 400);
		Bitmap smallBMP = BogoPicGen.generateBitmap(200, 200);
		Picture testPic = new Picture(); 	
		
		assertTrue("Testing if attached picture requirements; bigBMP returns False", testPic.smallPicture(bigBMP));
		assertTrue("Testing if attached picture requirements; smallBMP returns False", testPic.smallPicture(smallBMP));
	}
}
