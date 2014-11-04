package ca.ualberta.cs.corgfuapp.UItest;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;
import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.Question;
import ca.ualberta.cs.corgFuViews.BrowseItems;
import ca.ualberta.cs.corgFuViews.ViewQuestionAndAnswers;

public class ViewQuestionAndAnswersTest extends
		ActivityInstrumentationTestCase2<ViewQuestionAndAnswers> {
	public ViewQuestionAndAnswersTest(){
		super(ViewQuestionAndAnswers.class);
	}
	
	public void testViewQuestionText(){
		AllQuestionsController AQController = AllQuestionsApplication.getAllQuestionsController();
		Question Q1 = new Question("Test one");
		AQController.addQuestion(Q1);
		
		Intent intent = new Intent();
    	intent.putExtra("@string/idExtraTag", Q1.getId());
    	setActivityIntent(intent);
		ViewQuestionAndAnswers activity = (ViewQuestionAndAnswers) getActivity();
		
		TextView questionText = (TextView) activity.findViewById(R.id.questionText);
		assertEquals("Testing question text makes it to view","Test one", questionText.getText());
		cleanup();
	}
	
	public void testUpvoteText(){
		AllQuestionsController AQController = AllQuestionsApplication.getAllQuestionsController();
		Question Q1 = new Question("Test one");
		Q1.upvote();
		AQController.addQuestion(Q1);
		
		Intent intent = new Intent();
    	intent.putExtra("@string/idExtraTag", Q1.getId());
    	setActivityIntent(intent);
		ViewQuestionAndAnswers activity = (ViewQuestionAndAnswers) getActivity();
		
		TextView upvoteCount = (TextView) activity.findViewById(R.id.upvoteCount);
		assertEquals("Testing question text makes it to view","1", upvoteCount.getText());
		cleanup();
	}
	
	private void cleanup(){
		AllQuestionsApplication.destroy();
	}
	
	
}
