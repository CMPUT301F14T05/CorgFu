package ca.ualberta.cs.corgfuapp.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import android.graphics.Bitmap;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuES.ElasticSearch;
import ca.ualberta.cs.corgFuModels.Question;
import ca.ualberta.cs.corgfuapp.Util.BogoPicGen;

public class SortQuestionsByPictureTest extends TestCase {

	private Bitmap testImage1 = BogoPicGen.generateBitmap(1,1);
	private Bitmap testImage2 = BogoPicGen.generateBitmap(1,1);
	private ArrayList<Integer> qAdded;
	private ElasticSearch ES;
	
	public SortQuestionsByPictureTest(){
		super();
		ES = new ElasticSearch();
		qAdded = new ArrayList<Integer>();
	}
	public void setUp(){
		ES.clearQuestions();
	}
	public void testViewHasCorrectPicOrder(){
		Question mQ1 = new Question("Question 1"); //Q1 with Image
		mQ1.setImage(testImage1);
		try {
			Thread.sleep(5*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Question mQ2 = new Question("Question 2"); //Q2 with Image
		mQ2.setImage(testImage2);
		try {
			Thread.sleep(5*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Question mQ3 = new Question("Question 3"); //Q3
		try {
			Thread.sleep(5*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Question mQ4 = new Question("Question 4"); //Q4  
				
		AllQuestionsController mQC = AllQuestionsApplication.getAllQuestionsController();
		
		mQC.addQuestion(mQ4);
		mQC.addQuestion(mQ2);
		mQC.addQuestion(mQ1);
		mQC.addQuestion(mQ3);
		//Keeps track of added questions so they can be deleted
		qAdded.add(mQ1.getId());
		qAdded.add(mQ2.getId());
		qAdded.add(mQ3.getId());
		qAdded.add(mQ4.getId());
		// expected output should arrange questions first by Picture followed by date 
		// therefore; Q1, Q2, Q3, Q4 is the expected order 
		ArrayList<Question> expected = new ArrayList<Question>();
		expected.add(mQ2);
		expected.add(mQ1);
		expected.add(mQ4);
		expected.add(mQ3);
		try {
			Thread.sleep(3*60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		assertEquals("Testing if QuestionController returns list in correct order based on Picture", expected, mQC.sortByPicture());
	}
	@Override
	public void tearDown(){
		AllQuestionsApplication.destroy();
		for (int id : qAdded){
			ES.deleteQuestion(id);
		}
		qAdded.clear();
	}

}
