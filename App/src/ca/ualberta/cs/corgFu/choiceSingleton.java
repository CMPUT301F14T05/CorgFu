package ca.ualberta.cs.corgFu;

import android.util.Log;
/**
 * This is a singleton that allows for the calling of the choice
 * outide of the class for data management. This is used by any of
 * the data classes such as questions, replies and answers
 * @author Ahmed
 *
 */
public class choiceSingleton {
	private static choiceSingleton instance = null;
	private String choice;
	public choiceSingleton(){
		
	}
	/**
	 * Returns the single local copy of all the choices that
	 * are currently available 
	 * @return The object that holds all available choices
	 */
	public static choiceSingleton getInstance(){
		if (instance == null){
			instance = new choiceSingleton();	
		}
		return instance;
	}
	/**
	 * this sets the choice across classes
	 * @param myChoice setter for the choice
	 */
	public void setChoice(String myChoice){
		Log.i("cs", myChoice);
		instance.choice = myChoice;
	}
	/**
	 * get the choice that has been set previously
	 * @return The object that is the choice requested
	 */
	public String getChoice(){
		return instance.choice;
	}
}
