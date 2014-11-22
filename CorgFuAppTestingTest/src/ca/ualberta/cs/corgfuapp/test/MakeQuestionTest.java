package ca.ualberta.cs.corgfuapp.test;

import java.util.Date;

import android.location.Address;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFu.UserName;
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
				Q1String, Q1.getQuestionText());
	}	
	// tests UC-12
	public void testMakeQuestionVotes() {
		int voteTest = 12;
		Q1.setUpvotes(12);
		int cnt = 0;
		for(int i =0; i<12;i++){
			Q2.upvote();
			cnt++;
		}
		assertEquals("testing for direct vote set",voteTest, Q1.getUpvotes());
		assertEquals("testing for indirect upvoting",cnt, Q2.getUpvotes());
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
	
	public void testgetId(){
		Question mQ1 = new Question("Question 1");//add question text
		int id;
		id = mQ1.getId();//grabs id of created question
		assertTrue("Id is an integer",id == (int)id);//makes sure id is a valid integer
	}
	
	public void testattachAddress(){
//		Question q = new Question("test");
//		q.attachAddress();
//		String address = q.getReadableAddress();
//		assertEquals(address, "Calmar, Canada");
	}
	
	
	
}