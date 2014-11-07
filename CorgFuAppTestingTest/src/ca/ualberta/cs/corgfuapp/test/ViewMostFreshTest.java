package ca.ualberta.cs.corgfuapp.test;

import java.util.ArrayList;

import ca.ualberta.cs.corgFu.Reply;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Question;
import junit.framework.TestCase;

public class ViewMostFreshTest extends TestCase {

	public ViewMostFreshTest(){
		super();
	}

	public void testViewMostFresh(){
		// checks if replies are sorted right
		Question mQ1 = new Question("Question 1");// add question text
		Question EQ1 = new Question("Question 1");
		Reply rQ1 = new Reply("Reply 1");// add question text
		try {
			Thread.sleep(5 * 60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reply rQ2 = new Reply("Reply 2");// add question text
		try {
			Thread.sleep(5 * 60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reply rQ3 = new Reply("Reply 3");// add question text
		try {
			Thread.sleep(5 * 60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reply rQ4 = new Reply("Reply 4");// add question text
		AllQuestions mAQ = new AllQuestions();

		mAQ.addQuestion(mQ1);
		mQ1.addReply(rQ4);
		mQ1.addReply(rQ1);
		mQ1.addReply(rQ3);
		mQ1.addReply(rQ2);

		// expected output should arrange questions by most recent to latest
		// according to date

		EQ1.addReply(rQ1);
		EQ1.addReply(rQ2);
		EQ1.addReply(rQ3);
		EQ1.addReply(rQ4);

		assertEquals(
				"Testing if QuestionController returns list in correct date order",
				mQ1.getReplies(), EQ1.getReplies());
	}
}
