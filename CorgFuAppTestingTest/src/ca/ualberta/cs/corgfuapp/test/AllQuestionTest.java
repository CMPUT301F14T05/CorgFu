package ca.ualberta.cs.corgfuapp.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFu.ElasticSearch;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.Question;

public class AllQuestionTest extends TestCase {

	private ArrayList<Integer> qAdded;
	private ElasticSearch ES;
	
	public AllQuestionTest() {
		super();
		ES = new ElasticSearch();
		qAdded = new ArrayList<Integer>();
	}

	public void testGetQuestionById(){
		AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
		Question q1 = new Question("Testing if we get the same object to modify");
		AQC.addQuestion(q1);
		qAdded.add(q1.getId());
		Question q2 = AQC.getQuestionById(q1.getId());
		qAdded.add(q2.getId());
		
		assertEquals("Testing if we get the same object to modify",q1,q2);
		
		q2.upvote();
		assertEquals("Modify object from getQuestionById should be same as actual object",q2.getUpvotes(),
				AQC.getQuestionById(q1.getId()).getUpvotes());

	}
	public void testAddQuestion(){
		AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
		Question q1 = new Question("test adding question");
		AQC.addQuestion(q1);
		qAdded.add(q1.getId());
		assertEquals("Tests add question", q1, AQC.getQuestionById(q1.getId()));
	}
	
	public void testGetAllQuestions(){
		AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
		Question Q1 = new Question("A question added only to elastic search");
		Question Q2 = new Question("A question added normally.");
		ES.addQuestion(Q1);
		AQC.addQuestion(Q2);
		
		qAdded.add(Q1.getId());
		qAdded.add(Q2.getId());
		
		try{
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ArrayList<Question> expected = ES.searchQuestion("", null);
		AQC.updateAllQuestions();
		ArrayList<Question> actual = AQC.getAllQuestions();
		
		assertEquals("Testing if all questions are got properly", expected.size(), actual.size());
	}
	
	@Override
	public void tearDown(){
		AllQuestionsApplication.destroy();
		for (int id : qAdded){
			ES.deleteQuestion(id);
		}
		qAdded.clear();
		AllQuestionsApplication.destroy();
	}

}
