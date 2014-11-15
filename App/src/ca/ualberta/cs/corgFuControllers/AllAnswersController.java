package ca.ualberta.cs.corgFuControllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ca.ualberta.cs.corgFu.ElasticSearch;
import ca.ualberta.cs.corgFuModels.AllAnswers;
import ca.ualberta.cs.corgFuModels.Answer;

/**
 *	Controller class that updates and modifies the AllAnswers model
 *	that holds all the currently available answers. 
 *	@see ca.ualberta.cs.corgFuModels.AllAnswers
 * @author ajwu
 */
public class AllAnswersController {

	AllAnswers allAnswers;
	ArrayList<Answer> allAnswersArray;
	ElasticSearch ES = new ElasticSearch();
	
	/**
	 * Initializes the controller with the model that it will be updating
	 * and modifying.
	 * @param aQ The AllAnswerss model that the controller will be editing
	 */
	public AllAnswersController(AllAnswers aAns) {
		allAnswers = aAns;

	}

	/**
	 * Returns all of the answers that are currently available in the
	 * AllAnswers model
	 * @return All currently available answers in the model in no 
	 * particular order.
	 * @see ca.ualberta.cs.corgFuModels.AllAnswers
	 */
	public ArrayList<Answer> getAllAnswers() {
		return allAnswers.getAllAnswers();
	}
	/**
	 * Adds the specified question to the AllAnswer model making it 
	 * available
	 * @param A an Answer object that the user submits
	 * @see ca.ualberta.cs.corgFuModels.AllAnswers
	 */
	public void addAnswer(Answer A) {
		allAnswers.addAnswer(A);
	}
	/**
	 * Gets the answer from the AllAnswer model that has the specified
	 * id
	 * @param id The psuodo random number given to a answer to help identify
	 * it.
	 * @return The answer that has the id matching the specified id. Null if 
	 * no such matching answer is found.
	 * @see ca.ualberta.cs.corgFuModels.AllAnswers
	 */
	public Answer getAnswerById(int id){
		ArrayList<Answer> answers = allAnswers.getAllAnswers();
		for (Answer a:answers){
			if (a.getId() == id){
				return a;
			}
		}
		return null;
	}

}