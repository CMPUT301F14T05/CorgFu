package ca.ualberta.cs.corgFuViews;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFu.UserName;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		TextView TV = (TextView)findViewById(R.id.initPrompt);//grabs the text view to be displayed
		Typeface customTypeFace = Typeface.createFromAsset(getAssets(), "fonts/26783.ttf");//creates a custom typeface from the textview
		TV.setTypeface(customTypeFace);//sets the textview to obtain that specific typeface
		TV.setTextColor(Color.argb(255,4,193,210));//sets the colour according to the argb values used in the storyboard
		final Button btn = (Button) findViewById(R.id.LoginButton);
		btn.setOnClickListener(new UserLoginListner());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
	private final class UserLoginListner implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			EditText uId = (EditText) findViewById(R.id.UIDEditText);
			String UserNameString = uId.getText().toString();
			int ulen = UserNameString.length();
			if (ulen <= 0 ){
				Toast.makeText(getApplicationContext(), "User Name Cant be empty", Toast.LENGTH_LONG).show();
			}else{
				UserName NameOfUser = new UserName(UserNameString);
				Toast.makeText(getApplicationContext(), NameOfUser.getUserName(), Toast.LENGTH_LONG).show();
				//Intent mainIntent = new Intent(this, MainActivity.class);
				//startActivity(mainIntent);
			}
			
			
		}
		
	}
}
