package ca.ualberta.cs.corgfuapp.UItest;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
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
		cleanup();
	}
	
	public void testUpdate(){
		AllQuestionsController AQController = AllQuestionsApplication.getAllQuestionsController();
		Question Q3 = new Question("Test three");
		BrowseItems activity = (BrowseItems) getActivity();
		ListView listView = (ListView) activity.findViewById(ca.ualberta.corgfuapp.R.id.browseQuestionsListView);
		ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
		assertEquals("Testing browseItems initializes with correct questions",0,adapter.getCount());
		AQController.addQuestion(Q3);
		assertEquals("Testing browseItems updates when Question Added",1,adapter.getCount());
		cleanup();
	}
	
	public void testAnswerCount(){
		AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
		Question Q1 = new Question("Testing answer count");
		Answer A1 = new Answer("Testing answerCount");
		for (int i=0; i<10; i++){
			Q1.addAnswer(A1);
		}
		AQC.addQuestion(Q1);
		
		BrowseItems activity = (BrowseItems) getActivity();
		ListView listView = (ListView) activity.findViewById(ca.ualberta.corgfuapp.R.id.browseQuestionsListView);
		ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
		
		RelativeLayout layout = (RelativeLayout) adapter.getView(0, null, listView);
		
		int children = layout.getChildCount();
		assertEquals("Check that there are 2 children",2,children);
		TextView tView = (TextView) layout.getChildAt(1);
				
		assertEquals("Testing display answers","10",tView.getText());

		cleanup();
	}
	
	private void cleanup(){
		AllQuestionsApplication.destroy();
	}

}
