package ca.ualberta.cs.corgfuapp.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFuControllers.FavoritesController;
import ca.ualberta.cs.corgFuControllers.QAController;
import ca.ualberta.cs.corgFuModels.Favorites;
import ca.ualberta.cs.corgFuModels.Question;

public class SetFavoritesTest extends TestCase {
	
	public SetFavoritesTest(){
		super();
	}
	
	public void testSetFavorite(){
		Question mQ1 = new Question("This is my fav question");
		
		QAController mQAC = new QAController(mQ1);
		
		Favorites favModel = new Favorites();
		FavoritesController favController = new FavoritesController(favModel);
		
		mQAC.setFav(favController);
		
		ArrayList<Question> expected = new ArrayList<Question>();
		expected.add(mQ1);
		
		assertEquals("Testing favorites",expected,favController.getFavs());

	}
	
}
