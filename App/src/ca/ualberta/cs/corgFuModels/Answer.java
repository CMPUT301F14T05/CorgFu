package ca.ualberta.cs.corgFuModels;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;

import android.graphics.Bitmap;


/**
 * This is an object that represents an answer that has been
 * submitted by the user in response to a question. This may 
 * include answer text, a picture, the upvotes that the answer
 * has recieved and if the answer has been favourited/cached  
 * by other users which may include upvotes, a picture, and replies.
 * 
 * @author Anthony Wu
 * @author Oleksii Shevchenko
 * @see ca.ualberta.cs.corgFuModels.Question
 * @see ca.ualberta.cs.corgFuModels.Reply
 * @see ca.ualberta.cs.corgFu.Picture
 */
public class Answer implements Serializable
{
	private static final long serialVersionUID = 1109155948964525667L;
	private int upvotes;
	private String answerText;
	private Date date;
	private ArrayList<Reply> replies;
	private Bitmap genericPic;
	private Boolean hasAPicture = false;
	private int id;
	private int tempId;
	private boolean isPushed;
	private String author;

	/**
	 * Builds an Answer based on the question text
	 * provided.
	 * @param Text The text that makes up the Answer.
	 */
	public Answer(String text){
		upvotes = 0;
		answerText = text;
		replies = new ArrayList<Reply>();
		date = new Date();
		isPushed=false;
		
		Random rand = new Random();
		id = rand.nextInt(100000);
	}
	
	/**
	 * A method that returns whether there is a picture 
	 * attached to the Answer or not
	 * @return A boolean indicating whether or not a picture is attached.
	 */
	public boolean hasPicture() {
		return hasAPicture;
	}

	/**
	 * A function that increments the number of upvotes a Answer has.
	 */
	public void upvote(){
		this.upvotes += 1; 	// Increments vote counter
	}
	
	/**
	 * Adds a reply object to Answer
	 * @param reply The reply object containing the reply text to 
	 * the Answer
	 */
	public void addReply(Reply reply){
		this.replies.add(reply);
	}

	/**
	 * Gets the question text held by the Answer object.
	 * @return The string that is the Answer text
	 */
	public String getReplyString() {
		// getReplyString method exists in Reply class
		// can we remove this?
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Gets the number of upvotes that the Answer currently
	 * has.
	 * @return The total number of upvotes the Answer has.
	 */
	public int getVotes() {
		return upvotes;
	}
	
	/**
	 * Gets the date the Answer was authored on. More specifically the
	 * date/time the user submitted their answer, regardless of internet
	 * connectivity.
	 * @return The date of the Answer 
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Returns all the replies to the question in an ArrayList.
	 * @return The ArrayList of replies to the Answer.
	 */
	public ArrayList<Reply> getReplies() {

		Collections.sort(this.replies, new Comparator<Reply>() {

						public int compare(Reply R1, Reply R2) {
							return R2.getDate().compareTo(R1.getDate());
						}
					});

		
		return this.replies;
		
	}
	
	/**
	 * Returns the Answer.
	 * @return The Answer text.
	 */
	public String getAnswerString() {
		return answerText;
		
	}
	
	/**
	 * Returns the image that is attached to the Answer.
	 * @return The bitmap of the picture attached to the Answer.
	 */
	public Bitmap getPicture(){
		return genericPic;
	}
	
	/**
	 * Allows for setting of the picture attached to the Answer.
	 * @param Image The bitmap of the picture to be attached to the
	 * Answer.
	 */
	public void setPicture(Bitmap picture){
		hasAPicture = true;
		genericPic = picture;
	}
	/**
	 * Sets the number of upvotes for the answer which is used for
	 * testing and for building a question from an elasticsearch
	 * response
	 * @param i The number of upvotes to be set for the answer
	 */
	public void setVotes(int i) {
		upvotes = i;
	}
	
	/**
	 * Returns the id that is assigned to the answer that helps
	 * uniquely identify it.
	 * @return The id of the answer which is 0<= id <= 100000
	 */
	public int getId(){
		return id;
	}
	public void setId(int newId){
		id = newId;
	}
	
	/**
	 * Returns the id that is temporary assigned to the answer
	 * to identify it within the same question.
	 * @return The TempId of the answer which is 0<= id <= 100000
	 */
	public int getTempId(){
		return tempId;
	}
	
	public void setTempId(int newId){
		tempId = newId;
	}

	public boolean isPushed() {
		return isPushed;
	}

	public void setPushed(boolean isPushed) {
		this.isPushed = isPushed;
	}
		
	public String stringDate(){
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return formatter.format(this.date);
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	
	public String stringAuthor(){
		return this.author;

	}
}
