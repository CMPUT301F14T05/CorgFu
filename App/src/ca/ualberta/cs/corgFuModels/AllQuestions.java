package ca.ualberta.cs.corgFuModels;

import java.util.ArrayList;

import android.view.View;

//This is the collection of all questions that have been asked/created
public class AllQuestions
{
	private ArrayList<Question> allQuestions;
	
	public AllQuestions(){
		allQuestions = new ArrayList<Question>();
	}
	
	public static void addView(View v)
	{

		// TODO Auto-generated method stub
		
	}
	
	public void addQuestion(Question Q){
		allQuestions.add(Q);
	}

}
