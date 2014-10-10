package ca.ualberta.cs.corgfuapp.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFuControllers.QuestionController;
import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Question;

public class ViewMostUpvotedQuestionTest 
	extends TestCase{
	
	public ViewMostUpvotedQuestionTest() {
		super();
	}
	
	public void testViewHasCorrectOrder(){
		Question mQ1 = new Question("Question 1");//add question text
		Question mQ2 = new Question("Question 2");//add question text
		mQ2.upvote();
		
		AllQuestions mAQ = new AllQuestions();
		mAQ.addQuestion(mQ1);
		mAQ.addQuestion(mQ2);
		
		QuestionController mQC = new QuestionController(mAQ);
		
		ArrayList<Question> expected = new ArrayList<Question>();
		expected.add(mQ2);
		expected.add(mQ1);
		
		assertEquals("Testing if QuestionController returns list in correct order", mQC.sortByUpvote()
				,expected);
	}
	

	
	
}
