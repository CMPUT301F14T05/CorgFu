package ca.ualberta.cs.corgfuapp.test;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.Question;

public class AllQuestionControllerTest extends TestCase {

	public AllQuestionControllerTest() {
		// TODO Auto-generated constructor stub
	}

	public void testGetQuestionById(){
		AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
		Question q1 = new Question("test one");
		AQC.addQuestion(q1);
		Question q2 = AQC.getQuestionById(q1.getId());
		
		assertEquals("Testing if we get the same object to modify",q1,q2);
		
		q2.upvote();
		assertEquals("Modify object from getQuestionById should be same as actual object",q2.getVotes(),
				AQC.getQuestionById(q1.getId()).getVotes());
	}

}
