package ca.ualberta.cs.corgfuapp.test;

import java.util.ArrayList;

import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Question;
import junit.framework.TestCase;

public class SortQuestionsDateTest extends TestCase {
	
	public void testViewHasCorrectDateOrder(){
		Question mQ1 = new Question("Question 1");//add question text
		Question mQ2 = new Question("Question 2");//add question text
		
		AllQuestions mAQ = new AllQuestions();
		mAQ.addQuestion(mQ1);
		mAQ.addQuestion(mQ2);
		
		AllQuestionsController mQC = new AllQuestionsController(mAQ);
		
		// expected output should arrange questions by most recent to latest according to date  
		ArrayList<Question> expected = new ArrayList<Question>();
		expected.add(mQ1);
		expected.add(mQ2);
		
		assertEquals("Testing if QuestionController returns list in correct date order", mQC.sortByDate()
				,expected);
	}
}
