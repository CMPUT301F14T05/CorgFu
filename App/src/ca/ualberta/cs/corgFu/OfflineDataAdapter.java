package ca.ualberta.cs.corgFu;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuControllers.DataController;
import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.Question;
import ca.ualberta.cs.corgFuViews.LoginActivity;
import ca.ualberta.cs.corgFuViews.ViewQuestionAndReplies;

public class OfflineDataAdapter extends ArrayAdapter<Question> {
	private Context myContext;
	private static int myResource = R.layout.question_item_layout;
	private static int questionTVId = R.id.QuestionTextTextView;
	private static int answerCountTVId = R.id.AnswerCount;
	private ArrayList<Question> myObjects;
	
	
	/**
	 * Builds the custom adapter that is used to insert a custom layout
	 * into the listview that shows the list of current questions.
	 * @param context Information on the global application environment
	 * @param objects The items that are going to be parsed into the custom
	 * view and displayed in a listview.
	 */
	public OfflineDataAdapter(Context context, ArrayList<Question> objects) {
		super(context, myResource, objects);
		myContext = context;
		myObjects = objects;
	}

	/**
	 * Inserts specific elements from objects into their respective views
	 * to create a custom row in a listview.
	 * @param position Indicates the position of the item being inserted
	 * in the list
	 * @param convertView The view that is passed in to be recycled (if
	 * possible) to allow for fast scrolling through listviews
	 * @param parent The listview that the element is being inserted into
	 */
	@Override
	public View getView(final int position, View convertView, ViewGroup parent){
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater) myContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(myResource, parent, false);
		}
		
		TextView questionTV = (TextView) convertView.findViewById(questionTVId);
		
		questionTV.setText(myObjects.get(position).toString());
		TextView answerCountTV = (TextView) convertView.findViewById(answerCountTVId);
		answerCountTV.setText(String.valueOf(myObjects.get(position).getAnswerCount()));
		convertView.setOnClickListener(new OnClickListener(){
			public void onClick(View view){
				Log.i("OFA", "got to on click");
				
				Question Q = myObjects.get(position);
				
				Log.i("OFA",Q.getQuestionText());
				goToQuestion(Q);
			}
		});
		return convertView;
	}
	
//	private void setListViewListener(){
//		final OnItemClickListener mMessageClickedHandler = new OnItemClickListener() {
//		    public void onItemClick(AdapterView parent, View v, int position, long id) {
//		    	Question question = (Question) parent.getItemAtPosition(position);
//		    	int qId = question.getId();
//		    	goToQuestion(qId);
//		    }
//		};
//		
//		ListView listView = (ListView) FavouriteView.findViewById(R.id.browseFavouriteListView);
//		listView.setOnItemClickListener(mMessageClickedHandler);
//	}
	
	private void goToQuestion(Question myQuestion){
		
		Intent intent = new Intent( myContext, ViewQuestionAndReplies.class);
		
		int qId = myQuestion.getId();
		AllQuestions AllQuestions = AllQuestionsApplication.getAllQuestions();
		AllQuestionsController AQC = new AllQuestionsController(AllQuestions);
		ArrayList<Question> AQAL = AQC.getAllQuestions();
		Boolean addControl = true;
		for (Question o : AQAL){
			if (qId == o.getId()){
				addControl=false; // checks and see if the question is already in the controller
			}
		}
		if(addControl){
			AQAL.add(myQuestion); // if not add it!
		}
		
		
		Log.i("gotoquestion", String.valueOf(qId));
    	intent.putExtra("@string/idExtraTag", qId);
    
    	myContext.startActivity(intent);
	}
	
}
