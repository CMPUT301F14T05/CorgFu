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
		// TODO Auto-generated method stub
		return -1;
	}
	
	public void upvoteQ(){
		question.upvote();
	}

	public void makeAvailOffline(OfflineDataController controller) {
		// TODO Auto-generated method stub
		
	}

	public void makeAuthoredQAvailOffline(OfflineDataController controller) {
		// TODO Auto-generated method stub
		
	}

	public void setFav(FavoritesController favController) {
		// TODO Auto-generated method stub
		
	}
	
	public void addToAuthoredOffline(AuthoredOfflineController AOC) {
		
	}

}
