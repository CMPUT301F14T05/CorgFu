package ca.ualberta.cs.corgFuControllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Question;

public class AllQuestionsController {

	AllQuestions allQuestions;
	ArrayList<Question> allQuestionsArray;
	//Comparator<Question> dateSorter = null;
	
	public AllQuestionsController(AllQuestions aQ) {
		allQuestions = aQ;
		ArrayList<Question> allQuestionsArray = allQuestions.getAllQuestions();
		
		}

	
		
	
	public ArrayList<Question> sortByDate(){
		
		Collections.sort(allQuestions.getAllQuestions(), new Comparator<Question>(){
			
			public int compare(Question q1, Question q2)
			{
				return q1.getDate().compareTo(q2.getDate());
			}
		});
		return allQuestions.getAllQuestions();
	
	}
	
	
	public ArrayList<Question> sortByPicture(){
		/*
		 * sort date
		 * then sort if has picture
		 * 
		 * if the question in array has a picture
		 */
		return null;
	}

	public ArrayList<Question> sortByUpvote() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Question> search(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Question> getAllQuestions(){
		return allQuestions.getAllQuestions();
	}
	
	
	
	
	public void addQuestion(Question Q){
		allQuestions.addQuestion(Q);
	}
	
}
