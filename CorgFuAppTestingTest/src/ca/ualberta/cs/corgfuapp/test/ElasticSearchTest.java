package ca.ualberta.cs.corgfuapp.test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFu.ElasticSearch;
import ca.ualberta.cs.corgFu.SearchHit;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.Question;


public class ElasticSearchTest extends TestCase {
	
	private static final String RESOURCE_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t05/";
	private Gson gson;
	ElasticSearch ES;
			
			
	public ElasticSearchTest(){
		super();
		gson = new Gson();
		ES = new ElasticSearch();
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
		assertEquals(found,Q);
		
		
		
	}

}
