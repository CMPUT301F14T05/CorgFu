package ca.ualberta.cs.corgfuapp.test;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFuControllers.QAController;
import ca.ualberta.cs.corgFuModels.Answer;
import ca.ualberta.cs.corgFuModels.Question;

public class ViewNumberOfAnswersTest extends TestCase {

	public ViewNumberOfAnswersTest(){
		super();
	}
	
	public void testNumberOfAnswers(){
		Question mQ1 = new Question("Question with answers");
		Answer A1 = new Answer("First answer");
		Answer A2 = new Answer("Second answer");
		mQ1.addAnswer(A1);
		mQ1.addAnswer(A2);
		
		QAController mQC = new QAController(mQ1);
		
		assertEquals("Testing if number of answers is correct",2,
				mQC.getAnswerCount());
	}

}
