package ca.ualberta.cs.corgFuViews;

import ca.ualberta.corgfuapp.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ViewQuestionAndAnswers extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_question_and_answers);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_question_and_answers, menu);
		return true;
	}

}
