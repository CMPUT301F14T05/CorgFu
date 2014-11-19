//package ca.ualberta.cs.corgfuapp.test;
//
//import java.util.ArrayList;
//
//import junit.framework.TestCase;
//import ca.ualberta.cs.corgFuControllers.AuthoredOfflineController;
//import ca.ualberta.cs.corgFuControllers.QAController;
//import ca.ualberta.cs.corgFuModels.AuthoredOffline;
//import ca.ualberta.cs.corgFuModels.Question;
//
//public class AuthorOfflineTest extends TestCase {
//	
//	public AuthorOfflineTest(){
//		super();
//	}
//	
//	public void testAuthorOffline(){
//		Question mQ1 = new Question("Author offline question");
//	
//		QAController mQAC = new QAController(mQ1);
//	
//		AuthoredOffline AO = new AuthoredOffline();
//		AuthoredOfflineController AOC = new AuthoredOfflineController(AO);
//		
//		mQAC.addToAuthoredOffline(AOC);
//		
//		ArrayList<Question> expected = new ArrayList<Question>();
//		expected.add(mQ1);
//		
//		assertEquals("Testing author offline", expected, AOC.getAuthoredOffline());
//	}
//}
