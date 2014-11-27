package ca.ualberta.cs.corgFu;

import java.lang.annotation.Inherited;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFuModels.Answer;
import ca.ualberta.cs.corgFuModels.Question;
import ca.ualberta.cs.corgFuModels.Reply;

/**
 * The custom ArrayAdapter that allows a custom expandable view 
 * to be inserted in an android ListView. The custom view 
 * used in this case displays upvote answer button, view picture
 * button and expandable list of replies to this answer.
 * @author Oleksii Shevchenko
 */
public class InsertAnswerAdapter extends ArrayAdapter<Answer> {
	
	private Context myContext;
	private static int myResource = R.layout.answer_item_layout;
	private static int answerTVId = R.id.answerText;
	private static int upvoteCount = R.id.upvoteAnswerCount;
	private static int answerRepliesExpId = R.id.answerReplies;
	private static int answerImageId = R.id.imageAnswer;

	
	/** Adapter that handles replies to each Answer */
	private InsertReplyAdapter replyAnswerAdapter;

	List<String> replyHeader;;
	HashMap<String, List<String>> replyChild;
	
	private ArrayList<Answer> myObjects;
	
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

		Answer answer = myObjects.get(position);

		//Set Answer Text
		TextView answerTV = (TextView) convertView.findViewById(answerTVId);
		answerTV.setText(answer.getAnswerString());
		TextView upvoteCountTV = (TextView) convertView.findViewById(upvoteCount);
		upvoteCountTV.setText(String.valueOf(answer.getVotes()));
		
		// Set Answer Picture
		ImageView imageAnswer = (ImageView) convertView.findViewById(answerImageId);
		imageAnswer.setImageBitmap(answer.getPicture());
		
		//Populate Replies to Answer Expandable
		ExpandableListView expListView = (ExpandableListView) convertView.findViewById(answerRepliesExpId);

		prepareReplyData(answer);

		// setting replyAdapter
		replyAnswerAdapter = new InsertReplyAdapter(myContext, replyHeader, replyChild);
		expListView.setAdapter(replyAnswerAdapter);
		return convertView;
	}
	
	/** populates header and children of expandable view
	 * for replies to Answer
	 * @param replyHeader - header of expandable view
	 * @param replyChild - HashMap of expandable view
	 */
    private void prepareReplyData(Answer answer) {
    	// re-initializing replyHeader and replyChild
		replyHeader = new ArrayList<String>();
		replyChild = new HashMap<String, List<String>>();
		replyHeader.add("Replies");
        List<String> replies = new ArrayList<String>();
    	
        //int repliesNumber = answer.getReplies().size();
        int repliesNumber = 0;
        if (repliesNumber == 0) {
    		// no replies to attach
            replies.add("No replies ... ");
    	}
    	else {
    		// populate replies attached to Question
    		for (Reply reply : answer.getReplies()) {
    			replies.add(reply.getReplyString());
    		}
    	}
    	// add the last child that has reply_add view 
        replies.add(" ");

        replyChild.put(replyHeader.get(0), replies);
        
    }

}
