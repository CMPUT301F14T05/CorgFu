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
	public void makeAvailOffline(OfflineDataController controller) {
		
	}
	/**
	 * Makes the question available offline and is added to the Authored 
	 * questions list
	 * @param controller The controller that will add the question to the 
	 * Authored questions list
	 */
	public void makeAuthoredQAvailOffline(OfflineDataController controller) {
		
	}
	/**
	 * Sets the question as a favorite of the current user, and makes it 
	 * available offline in a favorites list.
	 * @param favController The controller that will add the question to
	 * the favorites list.
	 */
	public void setFav(FavouritesController favController) {
		// TODO Auto-generated method stub
	}
	/**
	 * Adds the question to the authored offline list which will
	 * be pushed to the server when user is online again
	 * @param AOC The controller that facilitates adding this question
	 * to the authored offline list of questions
	 */
	public void addToAuthoredOffline(AuthoredOfflineController AOC) {
		
	}
	/**
	 * Returns the text of the question.
	 * @return A String of the question text.
	 */
	public String getQuestionString() {
		return question.getQuestionString();
	}
	public int getVotes() {
		return question.getVotes();
	}

}
