package ca.ualberta.cs.corgfuapp.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Answer;
import ca.ualberta.cs.corgFuModels.Question;

public class SearchQuestionsAndAnswersTest extends TestCase {

	public SearchQuestionsAndAnswersTest(){
		super();
	}

	public void testSearchQAs(){
		Question mQ1 = new Question("Searchable question");
		Question mQ2 = new Question("Another question");
		
		AllQuestions aQ = new AllQuestions();
		aQ.addQuestion(mQ2);
		aQ.addQuestion(mQ1);
		
		Answer A1 = new Answer("Answer 1");
		Answer A2 = new Answer("Another Answer");
		
		mQ1.addAnswer(A1);
		mQ2.addAnswer(A2);
		
		AllQuestionsController aQC = new AllQuestionsController(aQ);
		
		ArrayList<Question> expected = new ArrayList<Question>();
		expected.add(mQ1);
		
		
		
		assertEquals("Testing search of a Question",expected,aQC.search("Searchable"));
		assertEquals("Testing search of a Question",expected,aQC.search("Answer"));

		assertFalse("Testing search of an Answer",(expected==aQC.search("random")));
		
	}
}
