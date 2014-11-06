package ca.ualberta.cs.corgFu;

/**This class ElasticSearch deals with all elements of implementing Elastic Search.
 * There are methods contained here for adding new Questions to the server, 
 * retrieving Questions from the server, and deleting questions from the server.
 * 
 * @author Alex Makepeace
 * @author Wyatt Fleming
 * 
 * @version 1.0 Nov.6/2014
 */

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import ca.ualberta.cs.corgFuModels.Question;

import com.google.gson.Gson;

import android.util.Log;

public class ElasticSearch {
	
	/**RESOURCE_URL is the location of the server in which Questions will be stored in*/
	private static final String RESOURCE_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t05/";
	/**TAG is the tag used to identify what items should be included in a QuestionSearch*/
	private static final String TAG = "QuestionSearch";
	
	/**gson is used to implement Gson methods for storage*/
	private Gson gson;
	
	/**ElasticSearch() is the constructor for the ElasticSearch class object.
	 * The constructor instantiates the gson object*/
	public ElasticSearch(){//constructor
		gson = new Gson();
	}
	
	/**getQuestion() retrieves a Question from the server if it exists
	 * 
	 * @param Q takes in the Question object that we are hoping to retrieve
	 * @return a Question object retrieved by your method
	 */
	public Question getQuestion(Question Q){
		// The following code is taken from the Lab 7 on Elastic Search on 11/04/2014
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(RESOURCE_URL + Q.getId());
	
		HttpResponse response;
	
		try {
			response = httpClient.execute(httpGet);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		SearchHit<Question> sr = parseQuestionHit(response);
		Question found = sr.getSource();
		return found;
	}
	/**addQuestion() is a method that receives a question and tries to push said question online if there is an internet connection.
	 * 
	 * @param Q is given a question that is being added to the server
	 */
	
	public void addQuestion(Question Q){
		HttpClient httpClient = new DefaultHttpClient();

		try {
			HttpPost addRequest = new HttpPost(RESOURCE_URL + Q.getId());

			StringEntity stringEntity = new StringEntity(gson.toJson(Q));
			addRequest.setEntity(stringEntity);
			addRequest.setHeader("Accept", "application/json");

			HttpResponse response = httpClient.execute(addRequest);
			String status = response.getStatusLine().toString();
			Log.i(TAG, status);

		} catch (Exception e) { // this is where you will add question to authoured offline if no connection is found
			e.printStackTrace();
		}
	}
	
	/**parseQuestionHit() determines from a HttpResponse whether or not there is a SearchHit for Questions
	 * 
	 * @param response is a HttpResponse that is predetermined to be the response of a server
	 * @return
	 */
	private SearchHit<Question> parseQuestionHit(HttpResponse response){
		SearchHit<Question> sr = new SearchHit<Question>();
		return sr;
	}
	/**deleteQuestion() takes in the id of the question we are trying to delete and removes it from the server
	 * 
	 * @param id the id of the Question that we are hoping to delete
	 */
	public void deleteQuestion(int id) {
		
	}
	
	/**searchQuestion() takes a searchString that we are hoping to match to other questions
	 * and returns a list of all the Question that are found on the server that matches
	 * the desired string.
	 * 
	 * @param searchString the string that we are searching for 
	 * @param field any narrowed down search field that we are looking for
	 * @return an ArrayList of questions that match our desired searchString
	 */
	public ArrayList<Question> searchQuestion(String searchString, String field) {
		return null;
		
	}

}
