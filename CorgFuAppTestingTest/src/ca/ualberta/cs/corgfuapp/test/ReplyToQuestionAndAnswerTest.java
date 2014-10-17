package ca.ualberta.cs.corgfuapp.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFu.Reply;
import ca.ualberta.cs.corgFuModels.Answer;
import ca.ualberta.cs.corgFuModels.Question;

public class ReplyToQuestionAndAnswerTest extends TestCase {
	public ReplyToQuestionAndAnswerTest(){
		super();
	}
	String Q1String = "Question to view";
	String Q2String = "second question in view";
	Question Q1 = new Question(Q1String);
	Question Q2 = new Question(Q2String);
	Reply Q1Reply = new Reply("this is a reply");
	Reply Q1Reply2 = new Reply("this is the second reply");
	ArrayList<Reply> expectedAL= new ArrayList<Reply>();
	Reply A1Reply = new Reply("this is an answerReply");
	//tests UC-o6
	public void testAddReplyToQuestion(){
		Q1.addReply(Q1Reply);
		Q1.addReply(Q1Reply2);
		expectedAL.add(Q1Reply);
		expectedAL.add(Q1Reply2);
		ArrayList<Reply> returnList = Q1.getReplies();
		
		assertEquals("they arent equal", expectedAL,returnList);
		
	}
	public void testAddReplyToAnswer(){
		
		Answer Q1A = new Answer("this is an answer for Q1"); 
		Q1.addAnswer(Q1A);
		Q1A.addReply(A1Reply);
		//check if this is what you expect
	}
}
