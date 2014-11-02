package ca.ualberta.cs.corgFuModels;

import java.util.ArrayList;
import java.util.Date;

import android.graphics.Bitmap;
import ca.ualberta.cs.corgFu.Reply;

public class Question implements Comparable<Question> {
	private int upvotes;
	private String questionText;
	private Bitmap genericPic;
	private ArrayList<Answer> answers;
	private ArrayList<Reply> replies;
	private Date date;
	private Boolean hasAPicture;

	public Question(String Text) {
		upvotes = 0;
		questionText = Text;
		answers = new ArrayList<Answer>();
		replies = new ArrayList<Reply>();
		date = new Date();
		hasAPicture = false;
	}

	public boolean hasPicture() {
		return hasAPicture;
	}

	public void upvote() {
		upvotes += 1; // increments the current amount of upvotes
	}

	public void addAnswer(Answer answer) {
		answers.add(answer);
		/*
		 * TODO create a new answer object with the text string and append it to
		 * the answers list
		 */

	}

	public void addReply(Reply reply) {
		replies.add(reply);
	}

	public String getQuestionString() {
		// TODO Auto-generated method stub
		return questionText; // return the string that has been passed as a
								// question text
	}

	@Override
	public String toString() {
		return questionText;
	}

	public void setVotes(int i) {
		// TODO Auto-generated method stub
		upvotes = i;// sets the current number of upvotes to the passed int i
	}

	public int getVotes() {
		// TODO Auto-generated method stub
		return upvotes;// returns the current number of upvotes logged for this
						// question
	}

	public Date getDate() {
		return date;
		// TODO Auto-generated method stub
	}
	public void setDate(Date d) {
		date = d;
	}

	public ArrayList<Reply> getReplies() {
		// TODO Auto-generated method stub
		return null;
	}

	public Bitmap getImage() {
		return genericPic;
	}

	public void setImage(Bitmap Image) {
		genericPic = Image;
		hasAPicture = true;
	}

	public Answer getAnswer() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getAnswerCount() {
		return answers.size();
	}

	public int compareTo(Question q) {
		return this.date.compareTo(q.getDate());
	}

}
