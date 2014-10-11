package ca.ualberta.cs.corgfuapp.test;

import java.util.ArrayList;

import ca.ualberta.cs.corgFuControllers.OfflineDataController;
import ca.ualberta.cs.corgFuControllers.QAController;
import ca.ualberta.cs.corgFuModels.OfflineData;
import ca.ualberta.cs.corgFuModels.Question;
import junit.framework.TestCase;

public class ViewOfflineTest extends TestCase {
	
	public ViewOfflineTest(){
		super();
		}
		
		public void testViewOffline(){
			Question mQ1 = new Question("Question to view offline");
			
			QAController mQAC = new QAController(mQ1);
			mQAC.makeAvailOffline();
			
			OfflineData model = new OfflineData();
			OfflineDataController controller = new OfflineDataController(model);
			
			ArrayList<Question> expected = new ArrayList<Question>();
			expected.add(mQ1);
			
			assertEquals("Testing if authored questions are made"
					+ "available offline",expected,controller.getViewOfflineQs());
			
		}
}
