package ca.ualberta.cs.corgfuapp.test;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFuControllers.QAController;
import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Question;

public class ViewNumberOfAnswersTest extends TestCase {

	public ViewNumberOfAnswersTest(){
		super();
	}
	
	public void testNumberOfAnswers(){
		Question mQ1 = new Question("Question with answers");
		mQ1.addAnswer("First answer");
		mQ1.addAnswer("Second answer");
		
		QAController mQC = new QAController(mQ1);
		
		assertEquals("Testing if number of answers is correct",2,
				mQC.getAnswerCount());
	}

}
