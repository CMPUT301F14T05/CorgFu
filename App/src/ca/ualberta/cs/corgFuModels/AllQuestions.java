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
import ca.ualberta.cs.corgFuES.ElasticSearch;

/**
 * The model that holds all of the currently available questions 
 * that will be modified by an AllQuestionsController. The contents
 * of the model will depend on Internet connectivity. If there is
 * Internet connectivity and the app is active, the model will contain 
 * all available questions, from all other users, since the model was 
 * last updated. If there is no Internet connectivity, the model will
 * contain all questions that have been made available offline through
 * one of three methods. <p>
 * 1) Open the question for viewing <p>
 * 2) Set question as a favourite <p>
 * 3) Choose the Read Later option when viewing the question.
 * @author wrflemin
 */
public class AllQuestions extends Model<IView>
{
	private static ArrayList<Question> allQuestions;
	/**
	 * Builds the AllQuestions model which will hold the questions
	 * that are currently available (online or offline)
	 */
	public AllQuestions(){
		allQuestions = new ArrayList<Question>();
	}
	/**
	 * Adds a question to the collection of current questions that 
	 * AllQuestions model is holding
	 * @param Q A Question object to be added to the current 
	 */
	public void addQuestion(Question Q){
		allQuestions.add(Q);
	}
	/**
	 * Returns all of the currently available questions in an ArrayList.
	 * @return All of the currently available questions.
	 */
	public static ArrayList<Question> getAllQuestions(){
		return allQuestions;
	}
	
	public void setAllQuestions(ArrayList<Question> newAQ){
		allQuestions = newAQ;
	}

}
