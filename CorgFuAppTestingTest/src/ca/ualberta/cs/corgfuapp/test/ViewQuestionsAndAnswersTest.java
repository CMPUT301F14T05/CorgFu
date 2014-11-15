package ca.ualberta.cs.corgfuapp.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFu.Reply;
import ca.ualberta.cs.corgFuControllers.QAController;
import ca.ualberta.cs.corgFuModels.Answer;
import ca.ualberta.cs.corgFuModels.Question;

public class ViewQuestionsAndAnswersTest extends TestCase { 
	public ViewQuestionsAndAnswersTest (){
		super();
	}
	public void testViewQandA(){
		/* this is used test use case UC-02 and UC-03
		 * 
		 */
		Question q1 = new Question("q1");
		Answer a1 = new Answer("a1-q1");
		
		q1.addAnswer(a1);
		Reply q1r1= new Reply("HALP!");
		Reply a1r1 = new Reply("thakns");
		ArrayList<Reply> ARA = new ArrayList<Reply>();
		ArrayList<Reply> QRA = new ArrayList<Reply>();
		ARA.add(a1r1);
		QRA.add(q1r1);
		a1.addReply(a1r1);
		QAController QAC = new QAController(q1);
		assertEquals("test if right question", q1.getQuestionText(),QAC.getQuestionString()); // test if controller passes the right question
		assertEquals("test if right answer", a1, q1.getAnswers().get(0));
		assertEquals("test if right reply answer", ARA,a1.getReplies());
		assertEquals("test if right reply answer",ARA,q1.getReplies());
		
		
	}

}	
