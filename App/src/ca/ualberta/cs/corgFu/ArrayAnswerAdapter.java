package ca.ualberta.cs.corgFu;

import java.util.HashMap;
import java.util.List;

import ca.ualberta.corgfuapp.R;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuControllers.QAController;
import ca.ualberta.cs.corgFuModels.Question;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
 
public class ArrayAnswerAdapter extends BaseExpandableListAdapter {
	 
    private Context myContext;
    private List<String> answerHeader;                  // answerHeader titles
    private HashMap<String, List<String>> answerChild;  // answerChild titles
    private Question myQuestion; 						 // current question
 
    public ArrayAnswerAdapter(Context context, List<String> listDataHeader,
            HashMap<String, List<String>> listChildData, Question question) {
        this.myContext = context;
        this.answerHeader = listDataHeader;
        this.answerChild = listChildData;
        this.myQuestion = question;
    }
 
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.answerChild.get(this.answerHeader.get(groupPosition))
                .get(childPosititon);
    }
 
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    @SuppressLint("InflateParams")
	@Override
    public View getChildView(int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
 
        final String childText = (String) getChild(groupPosition, childPosition);
 

        LayoutInflater infalInflater = (LayoutInflater) this.myContext
                   	.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = infalInflater.inflate(R.layout.answer_item_layout, null);
        
        return convertView;
    }
 
    @Override
    public int getChildrenCount(int groupPosition) {
        return this.answerChild.get(this.answerHeader.get(groupPosition))
                .size();
    }
 
    @Override
    public Object getGroup(int groupPosition) {
        return this.answerHeader.get(groupPosition);
    }
 
    @Override
    public int getGroupCount() {
        return this.answerHeader.size();
    }
 
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
    	String headerTitle;
    	String noAnswersString = "No Answers";
    	// get string of answer id
    	String answerIdString = (String) getGroup(groupPosition);
    	// check if there are any answers
    	if(answerIdString == noAnswersString) {
    		// no answers, display No Answers string
    		headerTitle = noAnswersString;
    	}
    	else {
        	// convert answer id string to integer
            Integer answerId = Integer.parseInt(answerIdString);
            // get text of the answer by its Id
    		QAController QAC = new QAController(myQuestion);
    		headerTitle = QAC.getAnswerById(answerId).getAnswerString();
            //String headerTitle = myQuestion.getAnswerById(answerId).getAnswerString();
    	}

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.myContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.answer_header_relative, null);
        }
 
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.answerHeader);
		lblListHeader.setText(headerTitle);
 
        return convertView;
    }
 
    @Override
    public boolean hasStableIds() {
        return false;
    }
 
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
