package ca.ualberta.cs.corgfuapp.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuControllers.QAController;
import ca.ualberta.cs.corgFuES.ElasticSearch;
import ca.ualberta.cs.corgFuModels.Answer;
import ca.ualberta.cs.corgFuModels.Question;

public class ViewMostUpvotedAnswerTest extends TestCase{
	private ArrayList<Integer> qAdded;
	private ElasticSearch ES;
	
	public ViewMostUpvotedAnswerTest() {
	super();
	ES = new ElasticSearch();
	setUp();
	qAdded = new ArrayList<Integer>();
	}
	
	public void setUp(){
		ES.clearQuestions();
	}

	public void testAnswerViewHasCorrectOrder(){
		
	Question mQ1 = new Question("Question 1");//add question text
	Answer a1 = new Answer("Answer 1");
	
	try {
		Thread.sleep(10);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	mQ1.addAnswer(a1);
	Answer a2 = new Answer("Answer 2");//add question text
	a1.upvote();
	try {
		Thread.sleep(10);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Answer a3 = new Answer("Answer 3");
	a3.upvote();
	a3.upvote();
	
	AllQuestionsController AQController = AllQuestionsApplication.getAllQuestionsController();
	AQController.addQuestion(mQ1);
	ArrayList<Answer> expected = new ArrayList<Answer>();
	expected.add(a1);
	expected.add(a2);
	expected.add(a3);
	QAController QAC = new QAController(mQ1);
	ArrayList<Answer> actual = QAC.getAnswersByUpVotes();
	assertEquals("Testing if QuestionController returns list in correct order", expected.get(0).getAnswerString(),actual.get(0).getAnswerString()); // will and should fail, no answer sort function in controller
	tearDown();
	}
	@Override
	public void tearDown(){
		AllQuestionsApplication.destroy();
		for (int id : qAdded){
			ES.deleteQuestion(id);
		}
		qAdded.clear();
	}
}
