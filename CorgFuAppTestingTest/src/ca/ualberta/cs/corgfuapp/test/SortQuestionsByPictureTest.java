package ca.ualberta.cs.corgfuapp.test;

import java.util.ArrayList;

import android.graphics.Bitmap;
import ca.ualberta.cs.corfuapp.Util.BogoPicGen;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Question;
import junit.framework.TestCase;

public class SortQuestionsByPictureTest extends TestCase {

	private Bitmap testImage1 = BogoPicGen.generateBitmap(1,1);
	private Bitmap testImage2 = BogoPicGen.generateBitmap(1,1);
	
	public void testViewHasCorrectPicOrder(){
		Question mQ1 = new Question("Question 1"); //Q1 with Image
		try {
			Thread.sleep(5*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mQ1.setImage(testImage1);
		Question mQ2 = new Question("Question 2"); //Q2 with Image
		try {
			Thread.sleep(5*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mQ2.setImage(testImage2);
		Question mQ3 = new Question("Question 3"); //Q3
		try {
			Thread.sleep(5*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Question mQ4 = new Question("Question 4"); //Q4  
		try {
			Thread.sleep(5*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AllQuestions mAQ = new AllQuestions();


		AllQuestionsController mQC = new AllQuestionsController(mAQ);
		
		// expected output should arrange questions first by Picture followed by date 
		// therefore; Q1, Q2, Q3, Q4 is the expected order 
		ArrayList<Question> expected = new ArrayList<Question>();
		expected.add(mQ1);
		expected.add(mQ2);
		expected.add(mQ3);
		expected.add(mQ4);
		
		mAQ.addQuestion(mQ4);
		mAQ.addQuestion(mQ2);
		mAQ.addQuestion(mQ1);
		mAQ.addQuestion(mQ3);
				
		assertEquals("Testing if QuestionController returns list in correct order based on Picture", mQC.sortByPicture()
				,expected);
	}
}