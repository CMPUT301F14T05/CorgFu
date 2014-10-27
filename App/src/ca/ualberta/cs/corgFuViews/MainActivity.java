package ca.ualberta.cs.corgFuViews;

import ca.ualberta.corgfuapp.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Typeface customTypeFace = Typeface.createFromAsset(getAssets(), "fonts/26783.ttf");//creates a custom typeface from the textview
		Button myProfileButton = (Button)findViewById(R.id.MyProfileButton);
		myProfileButton.setTypeface(customTypeFace);//sets the button to obtain that specific typeface
		//myProfileButton.setTextColor(Color.argb(255,4,193,210));//sets the colour according to the argb values used in the storyboard, RBGA code: 04c2d2ff, #4c1d2
		TextView TV = (TextView)findViewById(R.id.MainQuestionText);//grabs the text view to be displayed
		Button answersButton = (Button)findViewById(R.id.GoToAnswer);
		answersButton.setTypeface(customTypeFace);
		TV.setTypeface(customTypeFace);//sets the textview to obtain that specific typeface
		//TV.setTextColor(Color.argb(255,4,193,210));//sets the colour according to the argb values used in the storyboard
		
	}
	
	public void toBrowseItems(View view){
    	Intent intent = new Intent(this,BrowseItems.class);
    	startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
