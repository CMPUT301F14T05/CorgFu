package ca.ualberta.cs.corgFuControllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFu.ElasticSearch;
import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Answer;
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
	ElasticSearch ES = new ElasticSearch();
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
		AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
		question = AQC.getQuestionById(question.getId());
		question.upvote();
		AQC.addQuestion(question);
	}


	public void addAnswer(Answer answer){
		AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
		question = AQC.getQuestionById(question.getId());
		question.addAnswer(answer);
		AQC.addQuestion(question);
		
	}
	public ArrayList<Answer> getAnswersByDate(){
		Collections.sort(question.getAnswers(),
				new Comparator<Answer>() {

			
					@Override
					public int compare(Answer lhs, Answer rhs) {
						// TODO Auto-generated method stub
						return rhs.getDate().compareTo(lhs.getDate());
						
					}
				});

		return  question.getAnswers(); 
	}
	public ArrayList<Answer> getAnswersByUpVotes(){
		// TODO Auto-generated method stub
		this.getAnswersByDate();
		Collections.sort(question.getAnswers(),
			new Comparator<Answer>() {

				public int compare(Answer A1, Answer A2) {
					Integer v1 = A1.getVotes();
					Integer v2 = A2.getVotes();
					return v2.compareTo(v1);
					
				}
			});
		return question.getAnswers();
				
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
