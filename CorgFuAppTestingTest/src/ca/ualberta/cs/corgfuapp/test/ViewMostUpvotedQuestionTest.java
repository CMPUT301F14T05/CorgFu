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
		Question mQ1 = new Question();//add question text
		Question mQ2 = new Question();//add question text
		//upvote one of the questions
		AllQuestions mAQ = new AllQuestions();
		//add questions
		QuestionController mQC = new QuestionController(mAQ);
		ArrayList<Question> expected = new ArrayList<Question>();
		expected.add(mQ1);
		expected.add(mQ2);
		assertEquals("Testing if QuestionController returns list in correct order", mQC.sortByUpvote()
				,expected);
	}
	

	
	
}
