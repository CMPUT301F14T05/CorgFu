package ca.ualberta.cs.corgFu;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFuModels.Question;

public class InsertQuestionAdapter extends ArrayAdapter<Question> {
	
	private Context myContext;
	private static int myResource = R.layout.question_item_layout;
	private static int questionTVId = R.id.QuestionTextTextView;
	private static int answerCountTVId = R.id.AnswerCount;
	private static int questionImageIVId = R.id.QuestionImagePreview;
	
	private ArrayList<Question> myObjects;
	
	public InsertQuestionAdapter(Context context, ArrayList<Question> objects) {
		super(context, myResource, objects);
		myContext = context;
		myObjects = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater) myContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(myResource, parent, false);
		}
		TextView questionTV = (TextView) convertView.findViewById(questionTVId);
		questionTV.setText(myObjects.get(position).toString());
		TextView answerCountTV = (TextView) convertView.findViewById(answerCountTVId);
		answerCountTV.setText(String.valueOf(myObjects.get(position).getAnswerCount()));
		ImageView questionImageTV = (ImageView) convertView.findViewById(questionImageIVId);
		questionImageTV.setImageBitmap(myObjects.get(position).getImage());
		
		
		return convertView;
	}
	
}
