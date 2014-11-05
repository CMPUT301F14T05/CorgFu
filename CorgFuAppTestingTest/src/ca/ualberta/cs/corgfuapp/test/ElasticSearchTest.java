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
	ElasticSearch ES;
			
			
	public ElasticSearchTest(){
		super();
		gson = new Gson();
		ES = new ElasticSearch();
	}
	
	public void testgetQuestion(){
		AllQuestionsController AQController = AllQuestionsApplication.getAllQuestionsController();//the app allQuestion singleton
		Question Q = new Question("Test one");
		AQController.addQuestion(Q);
		Question Recieved;
		Recieved = ES.getQuestion(Q);
		assertEquals("Sent question is equal to recieved question", Q,Recieved);
		cleanup();
	}
	
	public void testaddQuestion() throws ClientProtocolException, IOException{
		AllQuestionsController AQController = AllQuestionsApplication.getAllQuestionsController();//the app allQuestion singleton
		Question Q = new Question("Test one");
		AQController.addQuestion(Q);
		
		int id;
		id = Q.getId();
		
		// The following code is taken from the Lab 7 on Elastic Search on 11/04/2014
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(RESOURCE_URL + id);

		HttpResponse response;

		response = httpClient.execute(httpGet);
		SearchHit<Question> sr = ES.parseQuestionHit(response);
		Question found = sr.getSource();
		assertEquals("The retrieved question vs the expected question.", found,Q);
		cleanup();
	}
	
	public void testDeleteQuestion() throws ClientProtocolException, IOException{
		Question Q = new Question("Test one");
		
		ES.addQuestion(Q);
		
		int id = Q.getId();
		
		// The following code is taken from the Lab 7 on Elastic Search on 11/04/2014
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(RESOURCE_URL + id);

		HttpResponse response;

		response = httpClient.execute(httpGet);
		SearchHit<Question> sr = ES.parseQuestionHit(response);
		Question found = sr.getSource();
		
		assertEquals("Tests that question was added", Q, found);
		
		ES.deleteQuestion(id); //deletes question
		
		response = httpClient.execute(httpGet);
		sr = ES.parseQuestionHit(response);
		found = sr.getSource();
		
		assertEquals("Tests the question is not there anymore",null,found);
		
		cleanup();
	}
	
	public void testSearchQuestions(){
		//searchQuestion takes search string and field as an argument and 
		//returns an array of Questons.
		Question Q = new Question("This question has the word duck in it");
		Question Q2 = new Question("This question does not have the d word");
		
		ES.addQuestion(Q);
		ES.addQuestion(Q2);
		
		ArrayList<Question> result = ES.searchQuestion("duck","questionText");
		
		ArrayList<Question> expected = new ArrayList<Question>();
		expected.add(Q);
		
		assertEquals("Testing questionText search",expected, result);
		
		
	}
	
	private void cleanup(){
		AllQuestionsApplication.destroy();
	}

}
