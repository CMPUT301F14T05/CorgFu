package ca.ualberta.cs.corgfuapp.test;

import java.io.IOException;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFu.ElasticSearch;
import ca.ualberta.cs.corgFu.SearchHit;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.Question;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


public class ElasticSearchTest extends TestCase {
	
	private static final String RESOURCE_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t05/question/";
	private static final String TAG = "QuestionSearch";
	private Gson gson;
	private ArrayList<Integer> qAdded;
	private ElasticSearch ES;
			
			
	public ElasticSearchTest(){
		super();
		gson = new Gson();
		ES = new ElasticSearch();
		qAdded = new ArrayList<Integer>();
	}
	
	public void testgetQuestion(){
		AllQuestionsController AQController = AllQuestionsApplication.getAllQuestionsController();//the app allQuestion singleton
		Question Q = new Question("ES getQuestion question");
		qAdded.add(Q.getId());
		ES.addQuestion(Q);
		try {
			Thread.sleep(5*60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Question Recieved = null;
		try{
			Recieved = ES.getQuestion(Q.getId());
		} catch(JsonSyntaxException e){
			e.printStackTrace();
		}
		assertEquals("Sent question is equal to recieved question", Q.toString(),Recieved.toString());
	}
	
	public void testaddQuestion() throws ClientProtocolException, IOException{
		AllQuestionsController AQController = AllQuestionsApplication.getAllQuestionsController();//the app allQuestion singleton
		Question Q = new Question("ES Adding Question Test");
		ES.addQuestion(Q);
		qAdded.add(Q.getId());
		try {
			Thread.sleep(5*60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Question found = ES.getQuestion(Q.getId());
		
		assertEquals("The retrieved question vs the expected question.",Q.toString(),found.toString());
	}
	
	public void testDeleteQuestion() throws ClientProtocolException, IOException{
		Question Q = new Question("ES Question to be deleted");
		
		ES.addQuestion(Q);
		qAdded.add(Q.getId());
		int id = Q.getId();
		
		Question found;
		found = ES.getQuestion(Q.getId());
		
		assertEquals("Tests that question was added", Q.toString(), found.toString());
		
		ES.deleteQuestion(id); //deletes question
		
		found = ES.getQuestion(Q.getId());
		
		assertEquals("Tests the question is not there anymore",null,found);
		
	}
	
	public void testSearchQuestions(){
		//searchQuestion takes search string and field as an argument and 
		//returns an array of Questons.
		Question Q = new Question("This question has the word duck in it");
		Question Q2 = new Question("This question does not have the d word");
		
		ES.addQuestion(Q);
		ES.addQuestion(Q2);
		qAdded.add(Q.getId());
		qAdded.add(Q2.getId());
		//Wait a bit to make sure question makes it to the server
		try {
			Thread.sleep(5*60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Search for the question
		ArrayList<Question> result = ES.searchQuestion("duck","questionText");
		
		assertEquals("Make sure result was returned in array", 1, result.size());
		if (result.size() > 0){
			Question resultQ = result.get(0);
			assertEquals("Testing questionText search",Q.toString(), resultQ.toString());
		}
	}
	
	public void testSearchQuestionMultiple(){
		Question Q1 = new Question("This question has the word duck in it");
		Question Q2 = new Question("This question does not have the d word");
		Question Q3 = new Question("Another duck question!");
		Question Q4 = new Question("All of the duck questions");
		
		ES.addQuestion(Q1);
		ES.addQuestion(Q2);
		ES.addQuestion(Q3);
		ES.addQuestion(Q4);
		//Waits a bit to make sure the questions make it to the server
		try {
			Thread.sleep(5*60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Keeps track of question ids that have been added so we can delete them 
		// once the test is complete
		qAdded.add(Q1.getId());
		qAdded.add(Q2.getId());
		qAdded.add(Q3.getId());
		qAdded.add(Q4.getId());
		//Add a waiting method
		ArrayList<Question> result = ES.searchQuestion("duck","questionText");

		assertEquals("Testing search for multiple questions",3, result.size());
		
		result = ES.searchQuestion("", null);
		
		assertEquals("Testing the search method with no arguments (get all questions)",
				4, result.size());
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
