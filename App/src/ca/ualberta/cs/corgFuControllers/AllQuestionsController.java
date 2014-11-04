package ca.ualberta.cs.corgFuControllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Question;

public class AllQuestionsController {

	AllQuestions allQuestions;
	ArrayList<Question> allQuestionsArray;

	// Comparator<Question> dateSorter = null;

	public AllQuestionsController(AllQuestions aQ) {
		allQuestions = aQ;

	}

	public ArrayList<Question> sortByDate() {

		Collections.sort(allQuestions.getAllQuestions(),
				new Comparator<Question>() {

					public int compare(Question q1, Question q2) {
						return q1.getDate().compareTo(q2.getDate());
					}
				});
		return allQuestions.getAllQuestions();

	}

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

	public ArrayList<Question> sortByUpvote() {
		// TODO Auto-generated method stub
		Collections.sort(allQuestions.getAllQuestions(),
				new Comparator<Question>() {

					public int compare(Question q1, Question q2) {
						Integer v1 = q1.getVotes();
						Integer v2 = q2.getVotes();
						return v2.compareTo(v1);
			
					}
				});
		return allQuestions.getAllQuestions();
		
	}

	public ArrayList<Question> search(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Question> getAllQuestions() {
		return allQuestions.getAllQuestions();
	}

	public void addQuestion(Question Q) {
		allQuestions.addQuestion(Q);
	}
	
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
