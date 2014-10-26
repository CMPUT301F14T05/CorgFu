package ca.ualberta.cs.corgFuControllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Question;

public class AllQuestionsController {

	AllQuestions allQuestions;
	Comparator<Question> dateSorter = null;
	
	public AllQuestionsController(AllQuestions aQ) {
		allQuestions = aQ;
		//makes a comparator for sorting questions by date
		dateSorter = new Comparator<Question>(){
			@Override
			public int compare(Question Q1, Question Q2){
				return Q1.getDate().compareTo(Q2.getDate());
			}
		};
		
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
		return null;
	}
	public ArrayList<Question> sortByDate(){
		Collections.sort(allQuestions.getAllQuestions(), dateSorter);
		return allQuestions.getAllQuestions();
	}
	
	public ArrayList<Question> sortByPicture(){
		return null;
	}
	
}
