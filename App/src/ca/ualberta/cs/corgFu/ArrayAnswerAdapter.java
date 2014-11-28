package ca.ualberta.cs.corgFu;

import java.util.HashMap;
import java.util.List;

import ca.ualberta.corgfuapp.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
 
public class ArrayAnswerAdapter extends BaseExpandableListAdapter {
	 
    private Context myContext;
    private List<String> answerHeader;                  // answerHeader titles
    private HashMap<String, List<String>> answerChild;  // answerChild titles
 
    public ArrayAnswerAdapter(Context context, List<String> listDataHeader,
            HashMap<String, List<String>> listChildData) {
        this.myContext = context;
        this.answerHeader = listDataHeader;
        this.answerChild = listChildData;
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
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.myContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.answer_header, null);
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
