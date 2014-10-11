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
		return 0;
	}
	
	public void upvoteQ(){
		question.upvote();
	}

	public void makeAvailOffline() {
		// TODO Auto-generated method stub
		
	}

	public void makeAuthoredQAvailOffline() {
		// TODO Auto-generated method stub
		
	}

}
