package ca.ualberta.cs.corgFuControllers;

import java.io.IOException;
import java.util.ArrayList;

import ca.ualberta.cs.corgFu.FavouriteListManager;
import ca.ualberta.cs.corgFuModels.Favourites;
import ca.ualberta.cs.corgFuModels.Question;
import ca.ualberta.cs.corgFu.Listener;
public class OfflineDataController {

	static ArrayList<Question> authored;
	static ArrayList<Question> favourites;
	public ArrayList<Question> getOfflineAuthoredQs() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Question> getViewOfflineQs() {
		return null;
	}

	public static ArrayList<Question> addAuthoredQuestion(Question mQ1) {
		// TODO Auto-generated method stub
		return authored;
	}

	public void addFavouriteQuestion(Question q1) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Question> getFavourites() {
		// TODO Auto-generated method stub
		return favourites;
	}
}
