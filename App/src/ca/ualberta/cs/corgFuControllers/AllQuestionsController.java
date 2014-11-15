package ca.ualberta.cs.corgFuControllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ca.ualberta.cs.corgFu.ElasticSearch;
import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Question;

/**
 *	Controller class that updates and modifies the AllQuestions model
 *	that holds all the currently available questions. 
 *	@see ca.ualberta.cs.corgFuModels.AllQuestions
 * @author wrflemin
 */
public class AllQuestionsController {

	AllQuestions allQuestions;
	ArrayList<Question> allQuestionsArray;
	ElasticSearch ES = new ElasticSearch();
	
	/**
	 * Initializes the controller with the model that it will be updating
	 * and modifying.
	 * @param aQ The AllQuestions model that the controller will be editing
	 */
	public AllQuestionsController(AllQuestions aQ) {
		allQuestions = aQ;

	}

	/**
	 * Sorts all of the questions in the AllQuestions model by their 
	 * creation date.
	 * @return An Array of the questions held by the AllQuestions 
	 * model sorted by date (Freshest first)
	 */
	public ArrayList<Question> sortByDate() {

		Collections.sort(allQuestions.getAllQuestions(),
				new Comparator<Question>() {

					public int compare(Question q1, Question q2) {
						return q1.getDate().compareTo(q2.getDate());
					}
				});
		return allQuestions.getAllQuestions();

	}
	/**
	 * Sots all of the questions in the AllQuestions model by whether or 
	 * not they have a picture.
	 * @return A sorted Array of the questions held by the AllQuestions
	 * model based on whether or not they have a question
	 */
	public ArrayList<Question> sortByPicture() {
		this.sortByDate();
		Collections.sort(allQuestions.getAllQuestions(),
				new Comparator<Question>() {
					public int compare(Question q1, Question q2) {
						if (q1.hasPicture() == true && q2.hasPicture() == false) {
							return -1;
						} else if (q1.hasPicture() == false
								&& q2.hasPicture() == true) {
							return 1;
						} else {
							return 0;
						}
					}
				});

		return allQuestions.getAllQuestions();
	}
	/**
	 * Sorts all of the questions in the AllQuestions model by their 
	 * number of upvotes.
	 * @return An Array of the questions held by the AllQuestions 
	 * model sorted by upvotes (Most first)
	 */
	public ArrayList<Question> sortByUpvote() {
		// TODO Auto-generated method stub
		Collections.sort(allQuestions.getAllQuestions(),
				new Comparator<Question>() {

					public int compare(Question q1, Question q2) {
						Integer v1 = q1.getUpvotes();
						Integer v2 = q2.getUpvotes();
						return v2.compareTo(v1);
			
					}
				});
		return allQuestions.getAllQuestions();
		
	}
	/**
	 * Searches the elastic search (All questions by all users) server 
	 * for a question or answer containing the specified search string 
	 * @param string The keyword the user is using to search the questions
	 * and answers in elastic search (All questions by all users)
	 * @return
	 */
	public ArrayList<Question> search(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Returns all of the questions that are currently available in the
	 * AllQuestions model
	 * @return All currently available questions in the model in no 
	 * particular order.
	 * @see ca.ualberta.cs.corgFuModels.AllQuestions
	 */
	public ArrayList<Question> getAllQuestions() {
		return allQuestions.getAllQuestions();
	}
	/**
	 * Adds the specified question to the AllQuestions model making it 
	 * available
	 * @param Q A Question object that the user wants to add
	 * @see ca.ualberta.cs.corgFuModels.AllQuestions
	 */ 
	public void addQuestion(Question Q) {
		allQuestions.addQuestion(Q);
		//ES.addQuestion(Q);
	}
	/**
	 * Gets the question from the AllQuestions model that has the specified
	 * id
	 * @param id The psuodo random number given to a question to help identify
	 * it.
	 * @return The question that has the id matching the specified id. Null if 
	 * no such matching question is found.
	 * @see ca.ualberta.cs.corgFuModels.AllQuestions
	 */
	public Question getQuestionById(int id){
		ArrayList<Question> questions = allQuestions.getAllQuestions();
		for (Question q:questions){
			if (q.getId() == id){
				return q;
			}
		}
		return null;
	}

}
