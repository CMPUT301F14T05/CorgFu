package ca.ualberta.cs.corgfuapp.test;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.Question;

public class AllQuestionTest extends TestCase {

	public AllQuestionTest() {
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
		cleanup();
	}
	public void testAddQuestion(){
		AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
		Question q1 = new Question("test one");
		AQC.addQuestion(q1);
		
		assertEquals("Tests add question", q1, AQC.getAllQuestions().get(0));
		cleanup();
	}
	
	public void cleanup(){
		AllQuestionsApplication.destroy();
	}

}
