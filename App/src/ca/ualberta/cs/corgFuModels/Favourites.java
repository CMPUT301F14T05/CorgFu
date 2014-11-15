package ca.ualberta.cs.corgFuModels;

import java.util.ArrayList;

import ca.ualberta.cs.corgFu.Listener;

public class Favourites {
	//private static final long serialVersionUID = 6673446047991058932L; // look up																	// what
	//looked up																	
	protected ArrayList<Question> favouriteList = null;
	protected transient ArrayList<Listener> listeners = null;

	public void FavouriteList() {
		favouriteList = new ArrayList<Question>();
		listeners = new ArrayList<Listener>();
	}

	public ArrayList<Question> getFavouriteList() {
		return favouriteList;
	}

	public void addListener(Listener l) {
		 getListeners().add(l);
	}

	public void addFavourite(Question q) {
		favouriteList.add(q);
		notifyListeners();

	}
	public void removeListener(Listener l) {
		getListeners().remove(l);
	}
	private void notifyListeners() {
		for (Listener  listener : getListeners()) {
			listener.update();
		}
	}
	private ArrayList<Listener> getListeners() {
		if (listeners == null ) {
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}
}


