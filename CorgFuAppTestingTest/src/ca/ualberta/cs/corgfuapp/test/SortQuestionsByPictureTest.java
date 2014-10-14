package ca.ualberta.cs.corgfuapp.test;

import java.util.ArrayList;

import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Question;
import junit.framework.TestCase;

public class SortQuestionsByPictureTest extends TestCase {

	public void testViewHasCorrectPicOrder(){
		Question mQ1 = new Question("Question 1");//question with PIC
		Question mQ2 = new Question("Question 2");//question with PIC
		Question mQ3 = new Question("Question 3");//question without PIC
		Question mQ4 = new Question("Question 4");//question without PIC 
		
		AllQuestions mAQ = new AllQuestions();
		mAQ.addQuestion(mQ1);
		mAQ.addQuestion(mQ2);
		mAQ.addQuestion(mQ3);
		mAQ.addQuestion(mQ4);
		
		AllQuestionsController mQC = new AllQuestionsController(mAQ);
		
		// expected should arrange questions with an attached Picture followed by dates  
		ArrayList<Question> expected = new ArrayList<Question>();
		expected.add(mQ1);
		expected.add(mQ2);
		expected.add(mQ3);
		expected.add(mQ4);
		
		assertEquals("Testing if QuestionController returns list in correct Picture order", mQC.sortByUpvote()
				,expected);
	}
}