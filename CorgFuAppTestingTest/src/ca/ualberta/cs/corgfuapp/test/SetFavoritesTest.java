package ca.ualberta.cs.corgfuapp.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFuControllers.OfflineDataController;
import ca.ualberta.cs.corgFuModels.Question;

public class SetFavoritesTest extends TestCase {

	public SetFavoritesTest() {
		super();
	}

	public void testSetFavorite(){
		OfflineDataController mOFDC = new OfflineDataController();
		Question Q1 = new Question("Test one");
		Question Q2 = new Question("Test two");
		mOFDC.addFavouriteQuestion(Q1);
		mOFDC.addFavouriteQuestion(Q2);
		ArrayList<Question> expected = new ArrayList<Question>();
		expected.add(Q1);
		expected.add(Q2);
		assertEquals(expected, mOFDC.getFavourites());
		
	}
}
