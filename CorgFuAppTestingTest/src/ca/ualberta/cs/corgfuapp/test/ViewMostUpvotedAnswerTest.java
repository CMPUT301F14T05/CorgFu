package ca.ualberta.cs.corgfuapp.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Answer;
import ca.ualberta.cs.corgFuModels.Question;

public class ViewMostUpvotedAnswerTest 
extends TestCase{

public ViewMostUpvotedAnswerTest() {
	super();
}

public void testAnswerViewHasCorrectOrder(){
	Question mQ1 = new Question("Question 1");//add question text
	Answer a1 = new Answer("Answer 1");
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
	AllQuestions mAQ = new AllQuestions();
	mAQ.addQuestion(mQ1);
	
	ArrayList<Answer> expected = new ArrayList<Answer>();
	expected.add(a1);
	expected.add(a2);
	expected.add(a3);
	
	
	assertEquals("Testing if QuestionController returns list in correct order", expected
			,mQ1.getAnswerByVotes());
	}
}
