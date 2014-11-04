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

//This is the collection of all questions that have been asked/created
public class AllQuestions extends Model<IView>
{
	private ArrayList<Question> allQuestions;
	ElasticSearch ES;
	
	public AllQuestions(){
		allQuestions = new ArrayList<Question>();
		ES = new ElasticSearch();
		
	}
	
	public void addQuestion(Question Q){
		allQuestions.add(Q);
		ES.addQuestion(Q);
	}
	
	public ArrayList<Question> getAllQuestions(){
		return allQuestions;
	}

}
