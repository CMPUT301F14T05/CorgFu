package ca.ualberta.cs.corgFuModels;

import java.util.ArrayList;
import java.util.Date;

import android.graphics.Bitmap;

import ca.ualberta.cs.corgFu.Reply;


public class Question
{
	private int upvotes;
	private String questionText;
	private Bitmap genericPic;
	private ArrayList<Answer> answers;
	private ArrayList<Reply> replies;
	private Date date;

	public Question(String Text){
		upvotes = 0;
		questionText = Text;
		answers = new ArrayList<Answer>();
		replies = new ArrayList<Reply>();
		date = new Date();
	}
	
	public void upvote(){
		upvotes += 1; // increments the current amount of upvotes
	}
	
	public void addAnswer(Answer answer) {
		answers.add(answer);
		/* TODO create a new answer object with the text string and append it
		to the answers list*/
		
	}
	public void addReply(Reply reply){
		replies.add(reply);
	}

	public String getQuestionString() {
		// TODO Auto-generated method stub
		return questionText; // return the string that has been passed as a question text
	}

	public void setVotes(int i) {
		// TODO Auto-generated method stub
		upvotes = i;//sets the current number of upvotes to the passed int i
	}

	public int getVotes() {
		// TODO Auto-generated method stub
		return upvotes;//returns the current number of upvotes logged for this question
	}

	public Date getDate() {
		return date;
		// TODO Auto-generated method stub
	}

	public ArrayList<Reply> getReplies() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Bitmap getImage(){
		return genericPic;
	}
	
	public void setImage(Bitmap Image){
		genericPic = Image;
	}

	public Answer getAnswer() {
		// TODO Auto-generated method stub
		return null;
	}
}
