package ca.ualberta.cs.corgFuControllers;

import java.util.ArrayList;

import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Question;

public class QAController {
	
	Question question;
		
	public QAController(Question q) {
		question = q;
	}

	public int getAnswerCount() {
		return question.getAnswerCount();
	}
	
	public void upvoteQ(){
		question.upvote();
	}

	public void makeAvailOffline(OfflineDataController controller) {
		
	}

	public void makeAuthoredQAvailOffline(OfflineDataController controller) {
		
	}

	public void setFav(FavoritesController favController) {
		
	}
	
	public void addToAuthoredOffline(AuthoredOfflineController AOC) {
		
	}

	public String getQuestionString() {
		return null;
	}

}
