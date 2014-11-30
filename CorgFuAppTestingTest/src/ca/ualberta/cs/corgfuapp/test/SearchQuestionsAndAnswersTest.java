package ca.ualberta.cs.corgfuapp.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFu.ElasticSearch;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.Answer;
import ca.ualberta.cs.corgFuModels.Question;

public class SearchQuestionsAndAnswersTest extends TestCase {

	private ArrayList<Integer> qAdded;
	private ElasticSearch ES;
	public SearchQuestionsAndAnswersTest(){
		super();
		qAdded = new ArrayList<Integer>();
		ES = new ElasticSearch();
	}

	public void testSearchQAs(){
		Question mQ1 = new Question("Searchable question");
		Question mQ2 = new Question("Another question");	
		Answer A1 = new Answer("Answer 1");
		Answer A2 = new Answer("Another Answer");
		
		AllQuestionsController aQC = AllQuestionsApplication.getAllQuestionsController();
		mQ1.addAnswer(A1);
		mQ2.addAnswer(A2);
		
		aQC.addQuestion(mQ2);
		aQC.addQuestion(mQ1);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		qAdded.add(mQ2.getId());
		qAdded.add(mQ1.getId());
		
		Question expected = mQ1;
		
		assertEquals("Making sure searchable question made it to the server",
				mQ1.toString(),ES.getQuestion(mQ1.getId()).toString());
		ArrayList<Question> actual = null;
		for (int i = 0;i<5;i++){
			actual = aQC.search("Searchable", null);
			if (actual.size()>0){
				break;
			}
		}
		assertEquals("Testing only one question was returned for searchable", 1, actual.size());
		assertEquals("Testing search of a Question",expected.toString(), actual.get(0).toString());
		for (int i = 0;i<5;i++){
			actual = aQC.search("Searchable", null);
			if (actual.size()>0){
				break;
			}
		}
		assertEquals("Testing only one question was returned for answer", 1, actual.size());
		actual = aQC.search("random", null);
		assertEquals("Testing search of an Answer",0,actual.size());
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
