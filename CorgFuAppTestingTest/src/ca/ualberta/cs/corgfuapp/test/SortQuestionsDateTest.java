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
	public class DateComparator implements Comparator<Question>{
		@Override
		public int compare(Question lhs, Question rhs) {
			return lhs.getDate().compareTo(rhs.getDate());
		}
		
	}
	
	public void testViewHasCorrectDateOrder(){
		Question mQ1 = new Question("Question 1");//add question text
		Question mQ2 = new Question("Question 2");//add question text
		Question mQ3 = new Question("Question 3");//add question text
//		AllQuestions mAQ = new AllQuestions();
//		mAQ.addQuestion(mQ1);
//		mAQ.addQuestion(mQ3);
//		mAQ.addQuestion(mQ2);
		ArrayList<Question> sortQList = new ArrayList<Question>();
		//AllQuestionsController mQC = new AllQuestionsController(mAQ);
		
		// expected output should arrange questions by most recent to latest according to date  
		ArrayList<Question> expected = new ArrayList<Question>();
		expected.add(mQ1);
		expected.add(mQ2);
		expected.add(mQ3);
		sortQList.add(mQ1);
		sortQList.add(mQ3);
		sortQList.add(mQ2);
		for (Question question:sortQList){
			System.out.println(question.getQuestionString());
		}
		Collections.sort(sortQList, new DateComparator() {
			public int compare(Question lhs, Question rhs) {
				if(lhs.getDate().before(rhs.getDate())){
					return 1;
				}else if (lhs.getDate().after(rhs.getDate())){
					return -1;
				}
				return 0;
				}
			}
		);
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
