package ca.ualberta.cs.corgFuModels;

import java.util.ArrayList;

import ca.ualberta.cs.corgFu.IView;
import ca.ualberta.cs.corgFu.Model;

//This is the collection of all questions that have been asked/created
public class AllQuestions extends Model<IView>
{
	private ArrayList<Question> allQuestions;
	
	public AllQuestions(){
		allQuestions = new ArrayList<Question>();
	}
	
	public void addQuestion(Question Q){
		allQuestions.add(Q);
	}
	
	public ArrayList<Question> getAllQuestions(){
		return allQuestions;
	}

}
