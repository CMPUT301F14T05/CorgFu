//package ca.ualberta.cs.corgfuapp.UItest;
//
//import android.test.ActivityInstrumentationTestCase2;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import ca.ualberta.cs.corgFu.AllQuestionsApplication;
//import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
//import ca.ualberta.cs.corgFuControllers.OfflineDataController;
//import ca.ualberta.cs.corgFuModels.Question;
//import ca.ualberta.cs.corgFuViews.BrowseItems;
//import ca.ualberta.cs.corgFuViews.FavouriteView;
//
//public class ViewOfflineTest extends ActivityInstrumentationTestCase2<FavouriteView> {
//	public ViewOfflineTest(){
//		super(FavouriteView.class);
//	}
//	/*
//	public void testDefaultOfflineItems(){
//		// testing if this can be made using favourites
//		// and if view displays favourites
//		OfflineDataController mOFDC = new OfflineDataController();
//		Question Q1 = new Question("Test one");
//		Question Q2 = new Question("Test two");
//		mOFDC.addFavouriteQuestion(Q1);
//		mOFDC.addFavouriteQuestion(Q2);
//		
//		FavouriteView activity = (FavouriteView) getActivity();
//		ListView listView = (ListView) activity.findViewById(ca.ualberta.corgfuapp.R.id.browseFavouriteListView);
//		ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
//		assertEquals("Testing Favourite view displays Question 1",Q1,adapter.getItemId(0));
//		cleanup();
//	}
//	public void testUpdate(){
//		OfflineDataController mOFDC = new OfflineDataController();
//		try {
//			Thread.sleep(5 * 60);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Question Q3 = new Question("Test three");
//		FavouriteView activity = (FavouriteView) getActivity();
//		ListView listView = (ListView) activity.findViewById(ca.ualberta.corgfuapp.R.id.browseFavouriteListView);
//		ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
//		mOFDC.addFavouriteQuestion(Q3);
//		assertEquals("Testing Favourite view displays Question 3 first",Q3,adapter.getItem(0));
//		
//		cleanup();
//	}
//	private void cleanup(){
//		AllQuestionsApplication.destroy();
//	}*/
//}
