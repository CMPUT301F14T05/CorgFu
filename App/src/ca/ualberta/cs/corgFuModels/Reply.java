package ca.ualberta.cs.corgFuModels;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is an object that represents a reply that has been
 * submitted by the user in response to a question/answer. This 
 * includes a reply text by other users to provide feedback 
 * on a certain answer/question.  
 * 
 * @author Anthony Wu
 * @author Oleksii Shevchenko
 * @see ca.ualberta.cs.corgFuModels.Question
 * @see ca.ualberta.cs.corgFuModels.Answer
 */
public class Reply implements Serializable{
	private String replyString;
	private String author;
	private Date date;

	private boolean isPushed;
	private static final long serialVersionUID = -8270344298863725879L;
	/**
	 * Builds an Reply based on the question/answer text
	 * provided.
	 * @param reply
	 */
	public Reply(String reply){
		this.replyString = reply;
		this.date = new Date();
		this.isPushed = false;
		
	}
	public boolean isPushed() {
		return isPushed;
	}
	public void setPushed(boolean isPushed) {
		this.isPushed = isPushed;
	}
	/**
	 * Returns the reply text.
	 * @return reply Text. 
	 */
	public String getReplyString() {
		return replyString;
	}
	/**
	 * Gets the date the Reply was authored on. More specifically the
	 * date/time the user submitted their answer, regardless of internet
	 * connectivity.
	 * @return The date of the Reply.
	 */
	public Date getDate(){
		return date;
	}
	
	/**
	 * Gets the date of the Reply in a string format
	 * @return
	 */
	public String stringDate(){
		Format month = new SimpleDateFormat("MMM dd, yyyy");
		Format time = new SimpleDateFormat("hh:mm");
		String strMonth = month.format(this.date);
		String strTime = time.format(this.date);
		return  (strMonth + " at " + strTime);
	}
	
	/**
	 * Allows for the Author of the Reply to be set.
	 * @param author The name of the author of the Reply
	 */
	public void setAuthor(String author){
		this.author = author;
	}
	
	/**
	 * Gets the author of the Reply
	 * @return The string containing the author's 
	 * username
	 */
	public String stringAuthor(){
		return this.author;

	}
	
}