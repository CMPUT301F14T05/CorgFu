package ca.ualberta.cs.corgfuapp.test;

import java.util.Date;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFuModels.Answer;
//testing UC-05
public class MakeAnswerTest extends TestCase {
	public MakeAnswerTest(){
		
	}
	String A1String = "Question to view";
	String A2String = "second question in view";
	Answer A1 = new Answer(A1String);
	Date D1 = new Date();
	
	Answer A2 = new Answer(A2String);
	public void testMakeAnswerString() {
		
		assertEquals(
				"Testing if QuestionController returns list in correct order",
				A1String, A1.getAnswerString());
	}	
	//tests uc-12
	public void testMakeQuestionVotes() {
		int voteTest = 12;
		A1.setVotes(12);
		int cnt = 0;
		for(int i =0; i<12;i++){
			A2.upvote();
			cnt++;
		}
		assertEquals("testing for direct vote set",voteTest, A1.getVotes());
		assertEquals("testing for indirect upvoting",cnt, A2.getVotes());
	}
	public void testSetDate(){
		assertEquals("dates aren't equal", A1.getDate(),D1);	// these read the same in the test. 
	}
	
}
