package ca.ualberta.cs.corgfuapp.UItest;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFu.ElasticSearch;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.Question;
import ca.ualberta.cs.corgFuViews.BrowseItems;
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
	}
	
	public void testSimpleSearch(){
		Question Q1 = new Question("Testing SearchResultsActivityTest");
		Question Q2 = new Question("SearchResultsActivityTest");
		
		AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
		
		AQC.addQuestion(Q1);
		AQC.addQuestion(Q2);
		
		String searchString = "Testing";
		
		Intent intent = new Intent();
		intent.putExtra("@string/idSearchTerm", searchString);
		setActivityIntent(intent);
		activity = (SearchResults) getActivity();
		ListView listView = (ListView) activity.findViewById(ca.ualberta.corgfuapp.R.id.resultsListView);
		ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
		while(adapter.getCount() == 0){}
		assertEquals("Testing search shows the right array",1,adapter.getCount());
	}
	
	public void testOpenSearchResult(){
		Question Q1 = new Question("Testing SearchResultsActivityTest");
		Question Q2 = new Question("SearchResultsActivityTest");
		
		AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
		
		AQC.addQuestion(Q1);
		AQC.addQuestion(Q2);
		
		String searchString = "Testing";
		
		Intent intent = new Intent();
		intent.putExtra("@string/idSearchTerm", searchString);
		setActivityIntent(intent);
		activity = (SearchResults) getActivity();
		
		ListView listView = (ListView) activity.findViewById(ca.ualberta.corgfuapp.R.id.resultsListView);
		ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
		while(adapter.getCount() == 0){}
		assertEquals("Testing search shows the right array",1,adapter.getCount());
		TextView tView= (TextView) adapter.getView(1, null, listView);
		tView.performClick();
	}
	@Override
	public void tearDown(){
		ES.clearQuestions();
		activity.finish();
	}
}
