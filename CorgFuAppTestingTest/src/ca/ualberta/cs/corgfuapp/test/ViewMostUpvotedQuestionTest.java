package ca.ualberta.cs.corgfuapp.test;

import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.corgFuControllers.BrowseQuestionsSorter;
import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Question;
import ca.ualberta.cs.corgFuViews.BrowseItems;

public class ViewMostUpvotedQuestionTest 
	extends ActivityInstrumentationTestCase2<BrowseItems>{
	
	public ViewMostUpvotedQuestionTest() {
		super(BrowseItems.class);
	}
	
	public void testViewHasCorrectOrder(){
		BrowseQuestionsSorter mBQS = new BrowseQuestionsSorter();
		Question mQ = new Question();
		AllQuestions mAQ = new AllQuestions();
		assertEquals("Testing if BrowseItems is displaying the list of " +
				"items in descending number of upvotes",BrowseItems.getCurrentDisplayCollection(),
				BrowseQuestionsSorter.sortByUpvotes());
	}
	

	
	
}
