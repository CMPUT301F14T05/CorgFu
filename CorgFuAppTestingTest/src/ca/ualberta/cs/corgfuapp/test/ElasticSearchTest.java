package ca.ualberta.cs.corgfuapp.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.Question;


public class ElasticSearchTest extends TestCase {
	
	private static final String RESOURCE_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t05/";
	private Gson gson;
			
			
	public ElasticSearchTest(){
		super();
		gson = new Gson();
	}
	
	public void testaddQuestion(){
		AllQuestionsController AQController = AllQuestionsApplication.getAllQuestionsController();//the app allQuestion singleton
		Question Q = new Question("Test one");
		AQController.addQuestion(Q);
		
		int id;
		id = Q.getId();
		// The following code is taken from the Lab 7 on Elastic Search on 11/04/2014
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(RESOURCE_URL + id);

		HttpResponse response;
		
		try {
			response = httpClient.execute(httpGet);
			//SearchHit<Movie> sr = parseMovieHit(response);
			//Question found = sr.getSource();
			//assertEquals(found,Q);

		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
	}

}
