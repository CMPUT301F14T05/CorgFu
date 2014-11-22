package ca.ualberta.cs.corgfuapp.UItest;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFu.ElasticSearch;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.Question;
import ca.ualberta.cs.corgFuViews.SearchResults;

public class SearchResultsActivityTest extends
		ActivityInstrumentationTestCase2<SearchResults> {

	private ElasticSearch ES;
	private SearchResults activity;
	
	public SearchResultsActivityTest(){
		super(SearchResults.class);
		ES = new ElasticSearch();
		
	}
	
	@Override
	public void setUp(){
		ES.clearQuestions();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Question Q1 = new Question("Testing SearchResultsActivityTest");
		Question Q2 = new Question("SearchResultsActivityTest");
		
		AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
		
		AQC.addQuestion(Q1);
		AQC.addQuestion(Q2);
		
		try{
			Thread.sleep(1000);
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
		String searchString = "Testing";
		
		Intent intent = new Intent();
		intent.putExtra("@string/idSearchTerm", searchString);
		setActivityIntent(intent);
		activity = (SearchResults) getActivity();
	}
	
	public void testSimpleSearch(){
	
		
		ListView listView = (ListView) activity.findViewById(ca.ualberta.corgfuapp.R.id.resultsListView);
		ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
		assertEquals("Testing search shows the right array",1,adapter.getCount());
		activity.finish();
	}
	
	public void testOpenSearchResult(){
		
		ListView listView = (ListView) activity.findViewById(ca.ualberta.corgfuapp.R.id.resultsListView);
		ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
		
		assertEquals("Testing search shows the right array",1,adapter.getCount());
		RelativeLayout rView= (RelativeLayout) adapter.getView(0, null, listView);
		TextView tView = (TextView) rView.getChildAt(0);
		tView.performClick();
		activity.finish();
	}
	@Override
	public void tearDown(){
		ES.clearQuestions();
		activity.finish();
	}
}
