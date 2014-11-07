package ca.ualberta.cs.corgfuapp.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFuControllers.OfflineDataController;
import ca.ualberta.cs.corgFuControllers.QAController;
import ca.ualberta.cs.corgFuModels.Question;

public class RememberQuestionsAskedTest extends TestCase {
	
	public RememberQuestionsAskedTest(){
		super();
	}
	
	public void testRememberAskedQs(){
		Question mQ1 = new Question("Question to remember");
		
		QAController mQAC = new QAController(mQ1);
	
		ArrayList<Question> expected = new ArrayList<Question>();
		expected.add(mQ1);
		OfflineDataController mOFDC = new OfflineDataController();
		OfflineDataController.addAuthoredQuestion(mQ1);
		
		
//		ArrayList<Question> expected = new ArrayList<Question>();
//		expected.add(mQ1);
//		
		assertEquals("Testing if authored questions are made"
				+ "available offline",expected,mOFDC.getOfflineAuthoredQs());
		
	}
}
