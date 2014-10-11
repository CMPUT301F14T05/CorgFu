package ca.ualberta.cs.corgFuModels;

import java.util.ArrayList;

import ca.ualberta.cs.corgFu.Reply;


public class Question
{
	private int upvotes;
	private String questionText;
	private ArrayList<Answer> answers;
	private ArrayList<Reply> replies;
	
	public Question(String qText){
		upvotes = 0;
		questionText = qText;
		answers = new ArrayList<Answer>();
		replies = new ArrayList<Reply>();
	}
	
	public void upvote(){
		upvotes += 1;
	}

	public void addAnswer(String string) {
		/* TODO create a new answer object with the text string and append it
		to the answers list*/
		
	}
}
