package ca.ualberta.cs.corgFu;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import ca.ualberta.cs.corgFuModels.Question;

import com.google.gson.Gson;

import android.util.Log;

public class ElasticSearch {
	
	private static final String RESOURCE_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t05/";
	private static final String TAG = "QuestionSearch";
	
	private Gson gson;
	
	public ElasticSearch(){//constructor
		gson = new Gson();
	}
	
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
	public SearchHit<Question> parseQuestionHit(HttpResponse response){
		SearchHit<Question> sr = new SearchHit<Question>();
		return sr;
	}

}
