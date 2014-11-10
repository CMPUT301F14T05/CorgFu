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


public class ElasticSearchTest extends TestCase {
	
	private static final String RESOURCE_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t05/";
	private static final String TAG = "QuestionSearch";
	private Gson gson;
	private ArrayList<Integer> qAdded;
	ElasticSearch ES;
			
			
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
		Question Recieved;
		Recieved = ES.getQuestion(Q);
		assertEquals("Sent question is equal to recieved question", Q,Recieved);
	}
	
	public void testaddQuestion() throws ClientProtocolException, IOException{
		AllQuestionsController AQController = AllQuestionsApplication.getAllQuestionsController();//the app allQuestion singleton
		Question Q = new Question("ES Adding Question Test");
		ES.addQuestion(Q);
		qAdded.add(Q.getId());
		
		int id;
		id = Q.getId();
		Question found;
		found = ES.getQuestion(Q);
		assertEquals("The retrieved question vs the expected question.",Q,found);
	}
	
	public void testDeleteQuestion() throws ClientProtocolException, IOException{
		Question Q = new Question("ES Question to be deleted");
		
		ES.addQuestion(Q);
		qAdded.add(Q.getId());
		int id = Q.getId();
		
		//Question found;
		//found = ES.getQuestion(Q);
		
		//assertEquals("Tests that question was added", Q, found);
		
		ES.deleteQuestion(id); //deletes question
		
		//found = ES.getQuestion(Q);
		
		//assertEquals("Tests the question is not there anymore",null,found);
		
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
		ArrayList<Question> result = ES.searchQuestion("duck","questionText");
		
		ArrayList<Question> expected = new ArrayList<Question>();
		expected.add(Q);
		
		assertEquals("Testing questionText search",expected.get(0).toString(), result.get(0).toString());
		
	}
	
	public void testSearchQuestionMultiple(){
		Question Q1 = new Question("This question has the word duck in it");
		Question Q2 = new Question("This question does not have the d word");
		Question Q3 = new Question("Another duck question!");
		Question Q4 = new Question("All of the duck");
		
		ES.addQuestion(Q1);
		ES.addQuestion(Q2);
		ES.addQuestion(Q3);
		ES.addQuestion(Q4);
		
		
		qAdded.add(Q1.getId());
		qAdded.add(Q2.getId());
		qAdded.add(Q3.getId());
		qAdded.add(Q4.getId());
		//Add a waiting method
		ArrayList<Question> result = ES.searchQuestion("duck","questionText");
		
		assertEquals("Testing search for multiple questions",3, result.size());
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
