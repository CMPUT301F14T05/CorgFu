package ca.ualberta.cs.corgfuapp.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Question;
import ca.ualberta.cs.corgFuViews.BrowseItems;

public class BrowseQuestionsTest extends TestCase {
	public BrowseQuestionsTest(){
		super();
		}
	// test for UC-01
		public void testBrowseQuestionView(){
			Question Q1 = new Question("Question to view");
			Question Q2 = new Question("second question in view");
			
			AllQuestions AQM = new AllQuestions(); // AQM = all question model
			AQM.addQuestion(Q1);
			AQM.addQuestion(Q2);
			// model is updated
			// controller is notified
			// notifies the view
			ArrayList<Question> VQL = BrowseItems.getCurrentDisplayCollection(); //VQL = View Question List
			// view should now be updated
			assertEquals("the view displayed same thing", VQL, AQM);
			// should equal
		}
}