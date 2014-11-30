package ca.ualberta.cs.corgfuapp.UItest;

import java.util.ArrayList;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFu.ElasticSearch;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.Answer;
import ca.ualberta.cs.corgFuModels.Question;
import ca.ualberta.cs.corgFuViews.BrowseItems;

public class BrowseItemsActivityTest extends
		ActivityInstrumentationTestCase2<BrowseItems> {
	
	private ArrayList<Integer> qAdded;
	private ElasticSearch ES;
	private BrowseItems activity;
	
	public BrowseItemsActivityTest(){
		super(BrowseItems.class);
		qAdded = new ArrayList<Integer>();
		ES = new ElasticSearch();
	}
	
	@Override 
	public void setUp(){
		ES.clearQuestions();
	}
	
	public void testDefaultBrowseItems(){
		AllQuestionsController AQController = AllQuestionsApplication.getAllQuestionsController();
		Question Q1 = new Question("Test one");
		Question Q2 = new Question("Test two");
		
		qAdded.add(Q1.getId());
		qAdded.add(Q2.getId());
		
		AQController.addQuestion(Q1);
		AQController.addQuestion(Q2);
		activity = (BrowseItems) getActivity();
		ListView listView = (ListView) activity.findViewById(ca.ualberta.corgfuapp.R.id.browseQuestionsListView);
		ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
		assertTrue("Testing browseItems initializes with correct questions",adapter.getCount()>0);
		activity.finish();
	}
	
	public void testUpdate(){
		AllQuestionsController AQController = AllQuestionsApplication.getAllQuestionsController();
		Question Q3 = new Question("Test three");
		
		qAdded.add(Q3.getId());
		
		activity = (BrowseItems) getActivity();
		ListView listView = (ListView) activity.findViewById(ca.ualberta.corgfuapp.R.id.browseQuestionsListView);
		ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
		int count = ES.searchQuestion("", null).size();
		assertEquals("Testing browseItems initializes with correct questions",count,adapter.getCount());
		AQController.addQuestion(Q3);
		try{
			Thread.sleep(500);
		} catch(Exception ex){
			ex.printStackTrace();
		}
		assertEquals("Testing browseItems updates when Question Added",count+1,adapter.getCount());
		activity.finish();
	}
	
	public void testAnswerCount(){
		AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
		Question Q1 = new Question("Testing answer count");
		Answer A1 = new Answer("Testing answerCount");
		for (int i=0; i<10; i++){
			Q1.addAnswer(A1);
		}
		AQC.addQuestion(Q1);
		try{
			Thread.sleep(500);
		} catch(Exception ex){
			ex.printStackTrace();
		}
		qAdded.add(Q1.getId());
		
		Intent intent = new Intent();
		setActivityIntent(intent);
		activity = (BrowseItems) getActivity();
		ListView listView = (ListView) activity.findViewById(ca.ualberta.corgfuapp.R.id.browseQuestionsListView);
		ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
		
		RelativeLayout layout = (RelativeLayout) adapter.getView(0, null, listView);
		
		int children = layout.getChildCount();
		assertEquals("Check that there are 4 children",4,children);
		TextView tView = (TextView) layout.getChildAt(2);
				
		assertEquals("Testing display answers","10",tView.getText());
		activity.finish();
	}
	
	@Override
	public void tearDown(){
		AllQuestionsApplication.destroy();
		for (int id : qAdded){
			ES.deleteQuestion(id);
		}
		qAdded.clear();
		activity.finish();
	}

}
