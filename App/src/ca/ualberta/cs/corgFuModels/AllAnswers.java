package ca.ualberta.cs.corgFuModels;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

import com.google.gson.Gson;

import ca.ualberta.cs.corgFu.ElasticSearch;
import ca.ualberta.cs.corgFu.IView;
import ca.ualberta.cs.corgFu.Model;

/**
 * The model that holds all of the currently available answers 
 * that will be modified by an AllAnswersController. The contents
 * of the model will depend on Internet connectivity. If there is
 * Internet connectivity and the app is active, the model will contain 
 * all available answers, from all other users, since the model was 
 * last updated. If there is no Internet connectivity, the model will
 * contain all answers that have been made available offline through
 * one of three methods. <p>
 * 1) Open the Answer for viewing <p>
 * 2) Set Answer as a favourite <p>
 * 3) Choose the Read Later option when viewing the Answer.
 * @author ajwu
 */
public class AllAnswers extends Model<IView>
{
	private ArrayList<Answer> allAnswers;
	ElasticSearch ES;
	/**
	 * Builds the AllAnswers model which will hold the questions
	 * that are currently available (online or offline)
	 */
	public AllAnswers(){
		allAnswers = new ArrayList<Answer>();
		
	}
	/**
	 * Adds an Answer to the collection of current Answers that 
	 * AllAnswer model is holding
	 * @param A an Answer object to be added to the current 
	 */
	public void addAnswer(Answer A){
		allAnswers.add(A);
	}
	/**
	 * Returns all of the currently available answers in an ArrayList.
	 * @return All of the currently available answers.
	 */
	public ArrayList<Answer> getAllAnswers(){
		return allAnswers;
	}

}