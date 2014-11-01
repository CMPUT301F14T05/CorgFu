package ca.ualberta.cs.corgFuModels;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

import com.google.gson.Gson;
import ca.ualberta.cs.corgFu.IView;
import ca.ualberta.cs.corgFu.Model;

//This is the collection of all questions that have been asked/created
public class AllQuestions extends Model<IView>
{
	private ArrayList<Question> allQuestions;
	private static final String RESOURCE_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t05/";
	private static final String TAG = "QuestionSearch";
	
	private Gson gson;
	
	public AllQuestions(){
		allQuestions = new ArrayList<Question>();
		gson = new Gson();
		
	}
	
	public void addQuestion(Question Q){
		allQuestions.add(Q);
		HttpClient httpClient = new DefaultHttpClient();

		try {
			HttpPost addRequest = new HttpPost(RESOURCE_URL + Q.getQuestionString());

			StringEntity stringEntity = new StringEntity(gson.toJson(Q));
			addRequest.setEntity(stringEntity);
			addRequest.setHeader("Accept", "application/json");

			HttpResponse response = httpClient.execute(addRequest);
			String status = response.getStatusLine().toString();
			Log.i(TAG, status);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Question> getAllQuestions(){
		return allQuestions;
	}

}
