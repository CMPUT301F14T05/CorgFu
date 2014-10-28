package ca.ualberta.cs.corgfuapp.test;

import ca.ualberta.cs.corfuapp.Util.BogoPicGen;
import ca.ualberta.cs.corgFu.Picture;
import junit.framework.TestCase;
import android.graphics.Bitmap;

public class SmallPicturesOnlyTest extends TestCase {
	
	public void testTooBigPicture(){
		Bitmap bigBMP = BogoPicGen.generateBitmap(400, 400);
		
		Picture testPic = new Picture(); 	
		
		assertFalse("Testing if attached picture requirements; bigBMP returns False", testPic.smallPicture(bigBMP));
	}
	
	public void testSmallPicturesOnly(){
		Bitmap smallBMP = BogoPicGen.generateBitmap(1, 1);
		
		Picture testPic = new Picture(); 	
		
		assertTrue("Testing if attached picture requirements; smallBMP returns True", testPic.smallPicture(smallBMP));
	}
}
