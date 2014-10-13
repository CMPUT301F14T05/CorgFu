package ca.ualberta.cs.corgFuModels;

import java.util.ArrayList;
import java.util.Date;

import ca.ualberta.cs.corgFu.Reply;


public class Answer
{
	private int upvotes;
	private String answerText;
	private Date date;
	private ArrayList<Reply> replies;

	public Answer(String Text){
		upvotes = 0;
		answerText = Text;
		replies = new ArrayList<Reply>();
		date = new Date();
	}
	public void addReply(Reply reply){
		replies.add(reply);
	}
	public void upvote(){
		upvotes += 1;
	}
	public String getReplyString() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setVotes(int i) {
		// TODO Auto-generated method stub
		
	}
	public int getVotes() {
		int x=0;
		// TODO Auto-generated method stub
		return x;
	}
	public Date getDate() {
		return date;
		// TODO Auto-generated method stub
	}
	public ArrayList<Reply> getReplies() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getAnswerString() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
