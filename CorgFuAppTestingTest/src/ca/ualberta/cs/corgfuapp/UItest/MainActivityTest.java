package ca.ualberta.cs.corgfuapp.UItest;

import java.util.ArrayList;

import android.R;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.UiThreadTest;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Question;
import ca.ualberta.cs.corgFuViews.BrowseItems;
import ca.ualberta.cs.corgFuViews.MainActivity;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity>{

	public MainActivityTest(){
		super(MainActivity.class);
		setActivityInitialTouchMode(true);
	}
	@UiThreadTest
	public void testAddQuestionButton(){
		AllQuestionsController AQController = AllQuestionsApplication.getAllQuestionsController();//the app allQuestion singleton
		Question q = new Question("Test one");
		AllQuestions aq = new AllQuestions();//controlled all questions object 
		aq.addQuestion(q);
		MainActivity activity = getActivity();
		EditText et = (EditText)activity.findViewById(ca.ualberta.corgfuapp.R.id. EnterQuestionBox);
		Button AskButton = (Button)activity.findViewById(ca.ualberta.corgfuapp.R.id.AskQuestionButton);
		et.setText("Test one");//sets the edit text box to a string which we can use to make sure the edit text is correctly linked to the ask button
		AskButton.performClick();//clicks the ask button adding question to AQ controller
		ArrayList<Question> first = aq.getAllQuestions();
		ArrayList<Question> second = AQController.getAllQuestions();
		assertEquals(first.get(0).toString(), second.get(0).toString());//makes sure the added question text is equal to the expected question text 
	}
}
