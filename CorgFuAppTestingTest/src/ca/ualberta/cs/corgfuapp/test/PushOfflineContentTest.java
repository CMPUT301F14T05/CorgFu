package ca.ualberta.cs.corgfuapp.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuControllers.AuthoredOfflineController;
import ca.ualberta.cs.corgFuControllers.QAController;
import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.AuthoredOffline;
import ca.ualberta.cs.corgFuModels.Question;

public class PushOfflineContentTest extends TestCase {
	
	public PushOfflineContentTest(){
		super();
	}
	
	public void testPushOfflineContent(){
		Question mQ1 = new Question("Authored offline question");
		
		QAController mQAC = new QAController(mQ1);
	
		AuthoredOffline AO = new AuthoredOffline();
		AuthoredOfflineController AOC = new AuthoredOfflineController(AO);
		AllQuestions AQ = new AllQuestions();
		AllQuestionsController AQC = new AllQuestionsController(AQ);
		
		mQAC.addToAuthoredOffline(AOC);
		
		AOC.pushContent(AQC);
		
		assertNull("Testing authored offline was emptied",AOC.getAuthoredOffline());
		
		ArrayList<Question> expected = new ArrayList<Question>();
		expected.add(mQ1);
		
		assertEquals("Testing the question got added to all questions", expected, AQC.getAllQuestions());
		
				
	}
	
}
