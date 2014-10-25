package ca.ualberta.cs.corgFuViews;

import ca.ualberta.corgfuapp.R;
import ca.ualberta.corgfuapp.R.layout;
import ca.ualberta.corgfuapp.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AttachPicture extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attach_picture);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.attach_picture, menu);
		return true;
	}

}
