package ca.ualberta.cs.corgFuModels;

import java.util.ArrayList;
import java.util.Date;

import android.graphics.Bitmap;

import ca.ualberta.cs.corgFu.Reply;


public class Answer
{
	private int upvotes;
	private String answerText;
	private Date date;
	private ArrayList<Reply> replies;
	private Bitmap genericPic;


	public Answer(String Text){
		upvotes = 0;
		answerText = Text;
		replies = new ArrayList<Reply>();
		date = new Date();
	}
	
	// Adds reply to the answer
	public void addReply(Reply reply){
		replies.add(reply);
	}
	
	// Increments vote counter
	public void upvote(){
		upvotes += 1;
	}
	
	// getReplyString method exists in Reply class
	// can we remove this?
	public String getReplyString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// Do we need to set votes? I don't think so
	public void setVotes(int i) {
		upvotes = i;
	}
	
	// Returns current number of votes for the answer
	public int getVotes() {
		return upvotes;
	}
	
	public Date getDate() {
		return date;
	}
	
	
	public ArrayList<Reply> getReplies() {
		return replies;
	}
	
	public String getAnswerString() {
		return answerText;
		
	}
	
	// Returns Picture associated with the answer
	public Bitmap getPicture(){
		return genericPic;
	}
	
	public void setPicture(Bitmap picture){
		genericPic = picture;
	}
}
