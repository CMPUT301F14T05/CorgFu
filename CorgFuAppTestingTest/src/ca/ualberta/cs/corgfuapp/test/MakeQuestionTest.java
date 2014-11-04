package ca.ualberta.cs.corgfuapp.test;

import java.util.Date;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFuModels.Answer;
import ca.ualberta.cs.corgFuModels.Question;
// this will test making a question UC-04
public class MakeQuestionTest extends TestCase {
	public MakeQuestionTest() {
		super();
	}
	String Q1String = "Question to view";
	String Q2String = "second question in view";
	Question Q1 = new Question(Q1String);
	Date D1 = new Date();
	
	Question Q2 = new Question(Q2String);
	public void testMakeQuestionString() {
		
		assertEquals(
				"Testing if QuestionController returns list in correct order",
				Q1String, Q1.getQuestionString());
	}	
	// tests UC-12
	public void testMakeQuestionVotes() {
		int voteTest = 12;
		Q1.setVotes(12);
		int cnt = 0;
		for(int i =0; i<12;i++){
			Q2.upvote();
			cnt++;
		}
		assertEquals("testing for direct vote set",voteTest, Q1.getVotes());
		assertEquals("testing for indirect upvoting",cnt, Q2.getVotes());
	}
	public void testGetId(){
		
	}
	
	public void testSetDate(){
		assertEquals("dates aren't equal", Q1.getDate(),D1);	
	}
	
	public void testGetAnswerCount(){
		Question mQ1 = new Question("Question 1");//add question text
		Answer a1 = new Answer("Answer 1");
		mQ1.addAnswer(a1);
		
		assertEquals("Testing answer count",1,mQ1.getAnswerCount());
		
		Answer a2 = new Answer("Answer 2");
		mQ1.addAnswer(a2);
		
		assertEquals("Testing answer count",2,mQ1.getAnswerCount());

	}
	
	
}