package ca.ualberta.cs.corgfuapp.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFu.ElasticSearch;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Question;

public class SortQuestionsDateTest extends TestCase {
	
	private ArrayList<Integer> qAdded;
	private ElasticSearch ES;
	
	public SortQuestionsDateTest(){
		super();
		ES = new ElasticSearch();
		setUp();
		qAdded = new ArrayList<Integer>();
	}
	public void setUp(){
		ES.clearQuestions();
	}
	
	
	
	
	public void testSortList(){
		AllQuestionsController AQController = AllQuestionsApplication.getAllQuestionsController();
		Question mQ1 = new Question("Question 1");//add question text
		try {
			Thread.sleep(5*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Question mQ2 = new Question("Question 2");//add question text
		try {
			Thread.sleep(5*60*10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Question mQ3 = new Question("Question 3");//add question text
		
		AllQuestions aq = new AllQuestions();
		aq.addQuestion(mQ2);
		aq.addQuestion(mQ3);
		aq.addQuestion(mQ1);
		
		ArrayList<Question> sortedQList = AQController.sortByDate();
		
		// expected output should arrange questions by most recent to latest according to date  
		ArrayList<Question> expected = new ArrayList<Question>();
		expected.add(mQ3);
		expected.add(mQ2);
		expected.add(mQ1);
		
		
		assertEquals("Testing if QuestionController returns list in correct date order", 
				expected,sortedQList); // if it fails examine junit print out, some time fails but has correct order
		tearDown();
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
