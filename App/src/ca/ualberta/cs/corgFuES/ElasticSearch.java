/*
 * Retrieved from Victor Guana's github 
 * (https://github.com/guana/elasticsearch) on November 10th, 2014
 */
package ca.ualberta.cs.corgFuES;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import ca.ualberta.cs.corgFuModels.Question;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.util.Log;
/**This class ElasticSearch deals with all elements of implementing Elastic Search.
 * There are methods contained here for adding new Questions to the server, 
 * retrieving Questions from the server, and deleting questions from the server.
 * 
 * @author Alex Makepeace
 * @author Wyatt Fleming
 * 
 * @version 1.0 Nov.6/2014
 */
public class ElasticSearch {
	
	private static final String SEARCH_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t05/question/_search?size=100";
	/**RESOURCE_URL is the location of the server in which Questions will be stored in*/
	private static final String RESOURCE_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t05/question/";
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
	public Question getQuestion(int id){
		// The following code is taken from the Lab 7 on Elastic Search on 11/04/2014
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(RESOURCE_URL + id);
	
		HttpResponse response;
	
		try {
			response = httpClient.execute(httpGet);
			SearchHit<Question> sr = parseQuestionHit(response);
			if (sr != null){
				return sr.getSource();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new Question(null);
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
		try {
			String json = getEntityContent(response);
			Type searchHitType = new TypeToken<SearchHit<Question>>() {}.getType();
			
			SearchHit<Question> sr = gson.fromJson(json, searchHitType);
			return sr;
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**deleteQuestion() takes in the id of the question we are trying to delete and removes it from the server
	 * 
	 * @param id the id of the Question that we are hoping to delete
	 */
	public void deleteQuestion(int id) {
		HttpClient httpClient = new DefaultHttpClient();

		try {
			HttpDelete deleteRequest = new HttpDelete(RESOURCE_URL + id);
			deleteRequest.setHeader("Accept", "application/json");

			HttpResponse response = httpClient.execute(deleteRequest);
			String status = response.getStatusLine().toString();
			Log.i(TAG, status);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**The method ClearQuestions gets rid of all the 
	 * questions currently saved to Elastic search.
	 */
	
	public void clearQuestions(){
		HttpClient httpClient = new DefaultHttpClient();

		try {
			HttpDelete deleteRequest = new HttpDelete(RESOURCE_URL);
			deleteRequest.setHeader("Accept", "application/json");

			HttpResponse response = httpClient.execute(deleteRequest);
			String status = response.getStatusLine().toString();
			Log.i(TAG, status);

		} catch (Exception e) {
			e.printStackTrace();
		}
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
		ArrayList<Question> result = new ArrayList<Question>();
		
		if (searchString == null || "".equals(searchString)) {
			searchString = "*";
		}
		
		HttpClient httpClient = new DefaultHttpClient();
		
		try {
			HttpPost searchRequest = createSearchRequest(searchString, field);
			Log.i(TAG,searchRequest.toString());
			HttpResponse response = httpClient.execute(searchRequest);
			
			String status = response.getStatusLine().toString();
			Log.i(TAG, status);
			
			SearchResponse<Question> esResponse = parseSearchResponse(response);
			Hits<Question> hits = esResponse.getHits();
			
			if (hits != null) {
				if (hits.getHits() != null) {
					for (SearchHit<Question> sesr : hits.getHits()) {
						result.add(sesr.getSource());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return result;
		
	}
	
	/**
	 * Gets content from an HTTP response
	 */
	private String getEntityContent(HttpResponse response) throws IOException {
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		return result.toString();
	}

	/**
	 * Creates a search request from a search string and a field
	 */
	private HttpPost createSearchRequest(String searchString, String field)	throws UnsupportedEncodingException {
		
		HttpPost searchRequest = new HttpPost(SEARCH_URL);
		
		String[] fields = getFields(field);
		
		SimpleSearchCommand command = new SimpleSearchCommand(searchString,	fields);
		
		String query = command.getJsonCommand();

		StringEntity stringEntity;
		stringEntity = new StringEntity(query);

		searchRequest.setHeader("Accept", "application/json");
		searchRequest.setEntity(stringEntity);

		return searchRequest;
	}
	/**
	 * Takes in a string of a field and makes it into a string[]
	 * @param field The string that represents the field that is
	 * being searched
	 */
	private String[] getFields(String field){
		String[] fields = null;
		if (field != null) {
			fields = new String[1];
			fields[0] = field;
		}
		return fields;
	}
	
	/**
	 * Parses the response of a search
	 */
	private SearchResponse<Question> parseSearchResponse(HttpResponse response) throws IOException {
		String json;
		json = getEntityContent(response);

		Type searchResponseType = new TypeToken<SearchResponse<Question>>() {
		}.getType();
		
		SearchResponse<Question> esResponse = gson.fromJson(json, searchResponseType);

		return esResponse;
	}
}
