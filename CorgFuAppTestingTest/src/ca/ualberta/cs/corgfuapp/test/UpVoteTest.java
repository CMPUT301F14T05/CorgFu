package ca.ualberta.cs.corgfuapp.test;

import java.util.ArrayList;

import android.graphics.Bitmap;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuES.ElasticSearch;
import ca.ualberta.cs.corgFuModels.Question;
import ca.ualberta.cs.corgfuapp.Util.BogoPicGen;
import junit.framework.TestCase;

public class UpVoteTest extends TestCase {
	
	private ArrayList<Integer> qAdded;
	private ElasticSearch ES;
	
	public UpVoteTest(){
		super();
		ES = new ElasticSearch();
		qAdded = new ArrayList<Integer>();
	}
	
	public void testViewHasCorrectOrder(){
		Question mQ1 = new Question("Question 1"); //Q1 with 10 votes
		try {
			Thread.sleep(5*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mQ1.setUpvotes(10);
		Question mQ2 = new Question("Question 2"); //Q2 with 5 votes
		try {
			Thread.sleep(5*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mQ2.setUpvotes(5);
		Question mQ3 = new Question("Question 3"); //Q3 with 5 votes
		try {
			Thread.sleep(5*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mQ3.setUpvotes(5);
		Question mQ4 = new Question("Question 4"); //Q4  with 0 votes
		try {
			Thread.sleep(5*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		AllQuestionsController mQC = AllQuestionsApplication.getAllQuestionsController();
		
		// expected output should arrange questions first by votes followed by date 
		// therefore; Q1, Q3, Q2, Q4 is the expected order 
		ArrayList<Question> expected = new ArrayList<Question>();
		expected.add(mQ1);
		expected.add(mQ3);
		expected.add(mQ2);
		expected.add(mQ4);
		
		mQC.addQuestion(mQ4);
		mQC.addQuestion(mQ2);
		mQC.addQuestion(mQ1);
		mQC.addQuestion(mQ3);
		
		qAdded.add(mQ1.getId());
		qAdded.add(mQ2.getId());
		qAdded.add(mQ3.getId());
		qAdded.add(mQ4.getId());
				
		assertEquals("Testing if QuestionController returns list in correct order based on Picture", expected
				,mQC.sortByUpvote());
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
