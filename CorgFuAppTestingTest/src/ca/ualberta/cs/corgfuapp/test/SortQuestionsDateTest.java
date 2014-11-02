package ca.ualberta.cs.corgfuapp.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Question;

public class SortQuestionsDateTest extends TestCase {
	AllQuestions allQuestions;
	ArrayList<Question> allQuestionsArray;
	
	public void testController(){
		Question mQ1 = new Question("Question 1");//add question text
		try {
			Thread.sleep(5*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Question mQ2 = new Question("Question 2");//add question text
		try {
			Thread.sleep(5*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Question mQ3 = new Question("Question 3");
		try {
			Thread.sleep(5*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Question mQ4 = new Question("Question 4");
		AllQuestions mAQ = new AllQuestions();
		mAQ.addQuestion(mQ4);
		mAQ.addQuestion(mQ2);
		mAQ.addQuestion(mQ3);
		mAQ.addQuestion(mQ1);
		
		AllQuestionsController mQC = new AllQuestionsController(mAQ);
		
		// expected output should arrange questions by most recent to latest according to date  
		ArrayList<Question> expected = new ArrayList<Question>();
		expected.add(mQ1);
		expected.add(mQ2);
		expected.add(mQ3);
		expected.add(mQ4);

		
		assertEquals("Testing if QuestionController returns list in correct date order", mQC.sortByDate()
				,expected);
	}
	
	
	
	public void testSortList(){
		Question mQ1 = new Question("Question 1");//add question text
		try {
			Thread.sleep(5*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Question mQ2 = new Question("Question 2");//add question text
		try {
			Thread.sleep(5*60*10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Question mQ3 = new Question("Question 3");//add question text
		
		ArrayList<Question> sortQList = new ArrayList<Question>();
		sortQList.add(mQ1);

		sortQList.add(mQ3);
		sortQList.add(mQ2);
		
		// expected output should arrange questions by most recent to latest according to date  
		ArrayList<Question> expected = new ArrayList<Question>();
		expected.add(mQ1);
		expected.add(mQ2);
		expected.add(mQ3);
		
		for (Question question:sortQList){
			System.out.println(question.getQuestionString());
		}
		// sorts list
		Collections.sort(sortQList, new Comparator<Question>(){
			public int compare (Question q1, Question q2)
			{
				return q1.getDate().compareTo(q2.getDate());
			}
		});
		
	
	
		
		
		for (Question l2:sortQList){
			System.out.println(l2.getQuestionString());
		}
		/*sortQList = mQC.sortByDate();
		for (Question question:sortQList){
				System.out.println(question.getQuestionString());
		}*/
		assertEquals("Testing if QuestionController returns list in correct date order", 
				expected,sortQList);
		
	}
}
