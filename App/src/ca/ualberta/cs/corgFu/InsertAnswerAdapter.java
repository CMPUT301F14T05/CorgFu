package ca.ualberta.cs.corgFu;

import java.lang.annotation.Inherited;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFuModels.Answer;
import ca.ualberta.cs.corgFuModels.Question;
import ca.ualberta.cs.corgFuModels.Reply;

/**
 * The custom ArrayAdapter that allows a custom view to be inserted
 * in an android ListView. The custom view used in this case displays
 * the question text, number of answers to the question, and the
 * number of upvotes the question has.
 * @author Anthony
 */
public class InsertAnswerAdapter extends ArrayAdapter<Answer> {
	
	private Context myContext;
	private ArrayList<Answer> myObjects;
	private static int answerTVId = R.id.answerText;
	private static int myResource = R.layout.answer_item_layout;
	private static int upvoteCount = R.id.upvoteAnswerCount;
	private static int dateTVId = R.id.date;
	private static int authorTVId = R.id.author;
	
	/**
	 * Builds the custom adapter that is used to insert a custom layout
	 * into the listview that shows the list of current questions.
	 * @param context Information on the global application environment
	 * @param objects The items that are going to be parsed into the custom
	 * view and displayed in a listview.
	 */
	public InsertAnswerAdapter(Context context, ArrayList<Answer> objects) {
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
	public View getView(int position, View convertView, ViewGroup parent){
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater) myContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(myResource, parent, false);
		}
		UserName user = UserName.getInstance();
				
		TextView questionTV = (TextView) convertView.findViewById(answerTVId);
		questionTV.setText(myObjects.get(position).getAnswerString());
		
		TextView upvoteCountTV = (TextView) convertView.findViewById(upvoteCount);
		upvoteCountTV.setText(String.valueOf(myObjects.get(position).getVotes()));
		
		TextView authorTV = (TextView) convertView.findViewById(authorTVId);
		authorTV.setText(String.valueOf(myObjects.get(position).stringAuthor()));
		
		TextView dateTV = (TextView) convertView.findViewById(dateTVId);
		dateTV.setText(String.valueOf(myObjects.get(position).stringDate()));
		
		
		return convertView;
	}
	
}