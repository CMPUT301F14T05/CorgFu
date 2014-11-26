package ca.ualberta.cs.corgFu;

import java.util.HashMap;
import java.util.List;

import ca.ualberta.corgfuapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
 
public class InsertReplyAdapter extends BaseExpandableListAdapter {
	 
    private Context myContext;
    private List<String> replyHeader;                  // replyHeader titles
    private HashMap<String, List<String>> replyChild;  // replyChild titles
 
    public InsertReplyAdapter(Context context, List<String> listDataHeader,
            HashMap<String, List<String>> listChildData) {
        this.myContext = context;
        this.replyHeader = listDataHeader;
        this.replyChild = listChildData;
    }
 
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.replyChild.get(this.replyHeader.get(groupPosition))
                .get(childPosititon);
    }
 
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    @Override
    public View getChildView(int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
 
        final String childText = (String) getChild(groupPosition, childPosition);
 
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.myContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.reply_item, null);
        }
 
        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.replyItem);
 
        txtListChild.setText(childText);
        return convertView;
    }
 
    @Override
    public int getChildrenCount(int groupPosition) {
        return this.replyChild.get(this.replyHeader.get(groupPosition))
                .size();
    }
 
    @Override
    public Object getGroup(int groupPosition) {
        return this.replyHeader.get(groupPosition);
    }
 
    @Override
    public int getGroupCount() {
        return this.replyHeader.size();
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
