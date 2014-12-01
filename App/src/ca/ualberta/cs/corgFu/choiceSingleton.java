package ca.ualberta.cs.corgFu;

import android.util.Log;

public class choiceSingleton {
	private static choiceSingleton instance = null;
	private String choice;
	public choiceSingleton(){
		
	}
	public static choiceSingleton getInstance(){
		if (instance == null){
			instance = new choiceSingleton();	
		}
		return instance;
	}
	public void setChoice(String myChoice){
		Log.i("cs", myChoice);
		instance.choice = myChoice;
	}
	public String getChoice(){
		return instance.choice;
	}
}
