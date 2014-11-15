package ca.ualberta.cs.corgFuControllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
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

	private AllQuestions allQuestions;
	private ArrayList<Question> results;
	private ElasticSearch ES;
	// Thread to update adapter after an operation
	private Runnable doReturnResults = new Runnable() {
		public void run() {
		}
	};
	
	/**
	 * Initializes the controller with the model that it will be updating
	 * and modifying.
	 * @param aQ The AllQuestions model that the controller will be editing
	 */
	public AllQuestionsController(AllQuestions aQ) {
		allQuestions = aQ;
		ES = new ElasticSearch();
		results = new ArrayList<Question>();

	}

	/**
	 * Sorts all of the questions in the AllQuestions model by their 
	 * creation date.
	 * @return An Array of the questions held by the AllQuestions 
	 * model sorted by date (Freshest first)
	 */
	public ArrayList<Question> sortByDate() {

		Collections.sort(getAllQuestions(),
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
		Collections.sort(getAllQuestions(),
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
		Collections.sort(getAllQuestions(),
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
		results.clear();
		Thread thread = new SearchThread(string);
		thread.start();
		return results;
	}
	/**
	 * Returns all of the questions that are currently available in the
	 * AllQuestions model
	 * @return All currently available questions in the model in no 
	 * particular order.
	 * @see ca.ualberta.cs.corgFuModels.AllQuestions
	 */
	public ArrayList<Question> getAllQuestions() {
		results.clear();
		Thread thread = new SearchThread("");
		thread.start();
		//allQuestions.setAllQuestions(results);
		return allQuestions.getAllQuestions();
	}
	/**
	 * Adds the specified question to the AllQuestions model making it 
	 * available
	 * @param Q A Question object that the user wants to add
	 * @see ca.ualberta.cs.corgFuModels.AllQuestions
	 */ 
	public void addQuestion(Question Q) {
		Thread thread = new AddThread(Q);
		thread.start();
		allQuestions.addQuestion(Q);
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
	
	/**
	 * Gets the most recent question from the AllQuestions model
	 * 
	 * @return The question that has the latest specified date
	 *  null if no question is found
	 * @see ca.ualberta.cs.corgFuModels.AllQuestions
	 */
	public Question getRecentQuestion() {
		ArrayList<Question> questions = allQuestions.getAllQuestions();
		// Pick the latest added question
		int lastIndex = questions.size() - 1; 
		Question q = sortByDate().get(lastIndex);
		if (questions.isEmpty()) {
			return null;
		}
		return q;
	}
	

	/*
	 * Retrieved from Victor Guana's github 
	 * (https://github.com/guana/elasticsearch) on November 10th, 2014
	 */
	/**
	 * A class that gives elastic search time to add the question before the activity is 
	 * paused by moving to the next intent.
	 * @author wrflemin
	 *
	 */
	class AddThread extends Thread {
		private Question Q;

		public AddThread(Question Q) {
			this.Q = Q;
		}

		@Override
		public void run() {
			ES.addQuestion(Q);
			
			// Give some time to get updated info
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	class SearchThread extends Thread {
		private String search;
		
		public SearchThread(String s) {
			search = s;
		}

		@Override
		public void run() {
			results.clear();
			results.addAll(ES.searchQuestion(search, null));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
