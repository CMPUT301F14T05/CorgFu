package ca.ualberta.cs.corgFuModels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;

import android.graphics.Bitmap;
import android.location.Address;
import ca.ualberta.cs.corgFu.IView;
import ca.ualberta.cs.corgFu.Model;
import ca.ualberta.cs.corgFu.UserName;
/**
 * The object that represents a question that has been asked
 * by a user. This may include question text, a picture, the 
 * upvotes that the question has received, the replies to the 
 * question, the location from which the question was attached 
 * if desired and the answers that have been added to the question 
 * by other users which may include upvotes, a picture, and replies.
 * 
 * @see ca.ualberta.cs.corgFuModels.Answer
 * @see ca.ualberta.cs.corgFuModels.Reply
 * @see ca.ualberta.cs.corgFu.Picture
 * @author wrflemin
 * @author Alex Makepeace
 *
 *@version 2.0 Nov.22,2014
 */
public class Question extends Model<IView> implements Comparable<Question>, Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5952661765874184488L;
	private int upvotes;
	private String questionText;
	private Bitmap genericPic;
	private ArrayList<Answer> answers;
	private ArrayList<Reply> replies;
	private Date date;
	private Boolean hasAPicture;
	private int id;
	private Address address;
	private String readableAddress = "No Location Available.";
	private Boolean isPushed;
	
	/**
	 * Builds a question based on the question text
	 * provided.
	 * @param Text The text that makes up the question.
	 */
	public Question(String Text) {
		upvotes = 0;
		questionText = Text;
		answers = new ArrayList<Answer>();
		replies = new ArrayList<Reply>();
		date = new Date();
		hasAPicture = false;
		Random rand = new Random();
		id = rand.nextInt(100000);
		attachAddress();
		isPushed=false;
		setReadableAddress();
	}
	
	public Boolean getIsPushed() {
		return isPushed;
	}

	public void setIsPushed(Boolean isPushed) {
		this.isPushed = isPushed;
	}
	
	/**
	 * 
	 * A method that returns whether there is a picture 
	 * attached to the question or not
	 * @return A boolean indicating whether or not a picture is attached.
	 */
	public boolean hasPicture() {
		return hasAPicture;
	}
	/**
	 * A function that increments the number of upvotes a question has.
	 */
	public void upvote() {
		upvotes += 1; // increments the current amount of upvotes
	}
	/**
	 * Adds the provided answer to the answers the question
	 * currently has
	 * @param answer The answer object that is to be added to the
	 * list of answers to the question.
	 */
	public void addAnswer(Answer answer) {
		answers.add(answer);
	}
	/**
	 * Adds a reply object to question
	 * @param reply The reply object containing the reply text to 
	 * the question
	 */
	public void addReply(Reply reply) {
		replies.add(reply);
	}
	
	/**
	 * Gets the question text held by the Question object.
	 * @return The string that is the question text
	 */
	public String getQuestionText() {
		return questionText; // return the string that has been passed as a
								// question text
	}

	@Override
	public String toString() {
		return questionText;
	}
	/**
	 * Allows for setting up upvotes when either testing or 
	 * building a question from an elastic search response.
	 * @param i
	 */
	public void setUpvotes(int i) {
		upvotes = i;// sets the current number of upvotes to the passed int i
	}
	/**
	 * Gets the number of upvotes that the question currently
	 * has.
	 * @return The total number of upvotes the question has.
	 */
	public int getUpvotes() {
		return upvotes;// returns the current number of upvotes logged for this
						// question
	}
	/**
	 * Gets the date the question was authored on. More specifically the
	 * date/time the user submitted their answer, regardless of internet
	 * connectivity.
	 * @return
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * Allows for setting of the date to a specific date/time. Used
	 * for testing or building a question from an elasticsearch 
	 * response.
	 * @param d The date/time the question was authored on
	 */
	public void setDate(Date d) {
		date = d;
	}
	/**
	 * Returns all the replies to the question in an ArrayList.
	 * @return The ArrayList of replies to the question.
	 */
	public ArrayList<Reply> getReplies() {

		Collections.sort(replies, new Comparator<Reply>() {

						public int compare(Reply R1, Reply R2) {
							return R2.getDate().compareTo(R1.getDate());
						}
					});

		
		return replies;
	}
	/**
	 * Returns the image that is attached to the question.
	 * @return The bitmap of the picture attached to the question.
	 */
	public Bitmap getImage() {
		return genericPic;
	}
	/**
	 * Allows for setting of the picture attached to the question.
	 * @param Image The bitmap of the picture to be attached to the
	 * question.
	 */
	public void setImage(Bitmap image) {
		genericPic = image;
		hasAPicture = true;
	}
	/**
	 * Returns the list of Answer objects that are associated with
	 * the question
	 * @return The ArrayList of Answer objects (answers to the question)
	 */
	public ArrayList<Answer> getAnswers() {
		return answers;
	}
	
	public Answer getAnswerById(int id) {
		for (Answer a:answers){
			if (a.getId() == id){
				return a;
			}
		}
		return null;
	}
	
	public void setAnswers(ArrayList<Answer> newAnswers){
		answers = newAnswers;
	}

	public ArrayList<Answer> getAnswerByVotes() {
		return null;
	}


	/**
	 * Returns the number of replies to the question
	 * @return The size of the answers array, which is the number of 
	 * answers to the question.
	 */

	public int getRepyCount() {
		return replies.size();
	}
	
	/**
	 * Returns the number of answers to the question
	 * @return The size of the answers array, which is the number of 
	 * answers to the question.
	 */

	public int getAnswerCount() {
		return answers.size();
	}
	/**
	 * Comparator used to sort the question by date.
	 * @return Integer indicating which one is larger (newer)
	 */
	public int compareTo(Question q) {
		return this.date.compareTo(q.getDate());
	}
	/**
	 * Returns the id that is assigned to the question that helps
	 * uniquely identify it.
	 * @return The id of the question which is 0<= id <= 100000
	 */
	public int getId(){
		return id;
	}
	/**Sets the Question id to a new integer
	 * @param newId is the new desired id number
	 */
	public void setId(int newId){
		id = newId;
	}

	/**Attaches the address that has been genarted and attached to the username
	 * 
	 */
	public void attachAddress() {
		// TODO Auto-generated method stub
		UserName myUserName = UserName.getInstance();
		Address adr = myUserName.getAddress();
		this.address = adr;
	}
	/**Returns the questions associated address
	 * 
	 * @return the address object that has been attached to the question on creation
	 */
	public Address getAddress(){
		return this.address;
	}

	/**Converts the associated address object to a readable 
	 * string text that has the city name and country name
	 * of the place the question has been posted from
	 */
	public void setReadableAddress() {
		// TODO Auto-generated method stub
		UserName myUserName = UserName.getInstance();
		String addr = myUserName.getFormattedAddress();
		this.readableAddress = addr;
	}
	
	/**Method that returns the string representation of the address
	 * 
	 * @return the easily readable string representation of the address
	 */
	public String getReadableAddress(){
			return this.readableAddress;
		
	}
}
