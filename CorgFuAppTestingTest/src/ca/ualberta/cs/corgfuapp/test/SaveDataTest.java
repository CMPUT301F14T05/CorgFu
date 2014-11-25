package ca.ualberta.cs.corgfuapp.test;

import java.util.ArrayList;
import java.util.Arrays;

import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.corgFuControllers.DataController;
import ca.ualberta.cs.corgFuModels.Question;
import ca.ualberta.cs.corgFuViews.MainActivity;

public class SaveDataTest extends ActivityInstrumentationTestCase2<MainActivity> {

	public SaveDataTest() {
		super(MainActivity.class);
		setActivityInitialTouchMode(true);
		
	}
	
	private static final String FavouritesFile = "Favourites.save";
	private static final String CacheFile = "CacheFile.save";
	private static final String ReadLater = "ReadLater.save";
	private static final String MyQuestions = "MyQuestions.save";
	DataController DataController;
	private static ArrayList<String> FileArray;
	ArrayList<Question> expected  = new ArrayList<Question>();
	ArrayList<Question> actual = new ArrayList<Question>();
	public void testSetFavorite(){
		MainActivity activity = getActivity();
		DataController = new ca.ualberta.cs.corgFuControllers.DataController();
		Question Q1 = new Question("Test one");
		Question Q2 = new Question("Test two");
		Question Q3 = new Question("Test three");
		
		FileArray = new ArrayList<String>();
		FileArray.add(CacheFile);
		FileArray.add(FavouritesFile);
		FileArray.add(MyQuestions);
		FileArray.add(ReadLater);
		for(String str: FileArray){
			DataController.clearData(str);
			DataController.addData(Q1, str);
			DataController.addData(Q2, str);
			DataController.addData(Q2, str);
			DataController.addData(Q3, str);
		}
		
		expected.add(Q1);
		expected.add(Q2);
		expected.add(Q3);
		actual = DataController.getData("CacheFile.save");
		for(int i =0; i<3;i++)
			assertEquals("test", actual.get(i).getId(), expected.get(i).getId());
		actual = DataController.getData("Favourites.save");
		for(int i =0; i<3;i++)
			assertEquals("test", actual.get(i).getId(), expected.get(i).getId());
		actual = DataController.getData("ReadLater.save");
		for(int i =0; i<3;i++)
			assertEquals("test", actual.get(i).getId(), expected.get(i).getId());
		actual = DataController.getData("MyQuestions.save");
		for(int i =0; i<3;i++)
			assertEquals("test", actual.get(i).getId(), expected.get(i).getId());
	}
	
}
