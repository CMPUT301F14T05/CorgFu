package ca.ualberta.cs.corgFuModels;

import java.io.Serializable;
import java.util.ArrayList;


public class OfflineData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1775745417333881785L;
	private ArrayList<Question> favouriteList = new ArrayList<Question>();
	private static OfflineData instance = null;
	//protected transient ArrayList<Listener> listeners = null;
	private OfflineData(){
		
	}
	public static OfflineData getInstance(){
		if (instance == null){
			instance = new OfflineData();
		}
		return instance;
	}
//	public void FavouriteList() {
//		favouriteList = new ArrayList<Question>();
//		//listeners = new ArrayList<Listener>();
//	}
	
	public ArrayList<Question> getFavouriteList() {
		return instance.favouriteList;
	}

//	public void addListener(Listener l) {
//		 getListeners().add(l);
//	}

	public static void addFavourite(Question q) {
		instance.favouriteList.add(q);
		//notifyListeners();
	}
	
//	public void removeListener(Listener l) {
//		getListeners().remove(l);
//	}
	
//	private void notifyListeners() {
//		for (Listener  listener : getListeners()) {
//			listener.update();
//		}
//	}
	
//	private ArrayList<Listener> getListeners() {
//		if (listeners == null ) {
//			listeners = new ArrayList<Listener>();
//		}
//		return listeners;
//	}
	
}


