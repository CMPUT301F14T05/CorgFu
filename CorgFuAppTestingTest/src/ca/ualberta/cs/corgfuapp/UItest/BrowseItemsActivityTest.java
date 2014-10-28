package ca.ualberta.cs.corgfuapp.UItest;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFu.InsertQuestionAdapter;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.Answer;
import ca.ualberta.cs.corgFuModels.Question;
import ca.ualberta.cs.corgFuViews.BrowseItems;

public class BrowseItemsActivityTest extends
		ActivityInstrumentationTestCase2<BrowseItems> {

	public BrowseItemsActivityTest(){
		super(BrowseItems.class);
	}
	
	public void testDefaultBrowseItems(){
		AllQuestionsController AQController = AllQuestionsApplication.getAllQuestionsController();
		Question Q1 = new Question("Test one");
		Question Q2 = new Question("Test two");
		AQController.addQuestion(Q1);
		AQController.addQuestion(Q2);
		BrowseItems activity = (BrowseItems) getActivity();
		ListView listView = (ListView) activity.findViewById(ca.ualberta.corgfuapp.R.id.browseQuestionsListView);
		ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
		assertEquals("Testing browseItems initializes with correct questions",2,adapter.getCount());
	}
	
	public void testUpdate(){
		AllQuestionsController AQController = AllQuestionsApplication.getAllQuestionsController();
		Question Q3 = new Question("Test three");
		BrowseItems activity = (BrowseItems) getActivity();
		ListView listView = (ListView) activity.findViewById(ca.ualberta.corgfuapp.R.id.browseQuestionsListView);
		ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
		assertEquals("Testing browseItems initializes with correct questions",2,adapter.getCount());
		AQController.addQuestion(Q3);
		assertEquals("Testing browseItems updates when Question Added",3,adapter.getCount());
	}

}
