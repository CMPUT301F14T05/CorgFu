package ca.ualberta.cs.corgFuModels;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Singleton method for offline data that allows for data manipulation
 * of offline data to be called once
 * @author Ahmed
 *
 */
public class OfflineData implements Serializable {

	private static final long serialVersionUID = 1775745417333881785L;
	private ArrayList<Question> favouriteList = new ArrayList<Question>();
	private static OfflineData instance = null;
	private OfflineData(){
		
	}
	
	/**
	 * gets the instance of the offline data that is to be manipulated
	 * @return the instance that is to be manipulated, and only gets it once
	 */
	public static OfflineData getInstance(){
		if (instance == null){
			instance = new OfflineData();
		}
		return instance;
	}
	
	/**
	 * gets the instance of the favourite list of data
	 * @return the favourite list
	 */
	public ArrayList<Question> getFavouriteList() {
		return instance.favouriteList;
	}

	/**
	 * addition of a favourite to the instance of a favourite
	 * @param q is the question that is being favourited
	 */
	public static void addFavourite(Question q) {
		instance.favouriteList.add(q);
	}
	
}


