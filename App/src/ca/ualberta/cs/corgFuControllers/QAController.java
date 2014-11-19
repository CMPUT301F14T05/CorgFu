package ca.ualberta.cs.corgFuControllers;

import java.util.ArrayList;

import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Question;
/**
 * The controller that allows for modification of the question
 * model, which also includes the modification of the answer
 * models. Upvotes, replies, and pictures can also be modified
 * throught the QAController.
 * @see ca.ualberta.cs.corgFuModels.Answer
 * @see ca.ualberta.cs.corgFu.Reply
 * @see ca.ualberta.cs.corgFu.Picture
 * @author wrflemin
 *
 */
public class QAController {
	
	Question question;
	/**
	 * Builds the controller with the question model that it will be 
	 * modifying.	
	 * @param q The question model the controller will modify.
	 */
	public QAController(Question q) {
		question = q;
	}
	/**
	 * Gets the number of Answers to the question
	 * @return The number of answers to the question
	 */
	public int getAnswerCount() {
		return question.getAnswerCount();
	}
	/**
	 * Increments the number of upvotes the question has.
	 */
	public void upvote(){
		question.upvote();
	}
	/**
	 * Makes the question available offline so it can be viewed later
	 * @param controller The controller that will add the question to the 
	 * list of available offline answers.
	 */
	
	/**
	 * Returns the text of the question.
	 * @return A String of the question text.
	 */
	public String getQuestionString() {
		return question.getQuestionText();
	}
	public int getVotes() {
		return question.getUpvotes();
	}

}
