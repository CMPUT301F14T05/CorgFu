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

	public class DateComparator implements Comparator<Question>{
		@Override
		public int compare(Question lhs, Question rhs) {
			return lhs.getDate().compareTo(rhs.getDate());
		}
		
	}
	public ArrayList<Question> sortByDate(){
		for (Question question:allQuestionsArray){
			System.out.println(question.getQuestionString());
		}
		
		Collections.sort(allQuestionsArray, new DateComparator());
		for (Question question:allQuestionsArray){
			System.out.println(question.getQuestionString());
		}
		return allQuestionsArray;
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
