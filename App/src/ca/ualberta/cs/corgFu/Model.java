package ca.ualberta.cs.corgFu;

import java.util.ArrayList;
import ca.ualberta.cs.corgFu.IView;

public class Model<V extends IView> {
	private ArrayList<V> views;
	
	public Model(){
		views = new ArrayList<V>();
	}
	
	public void addView(V view){
		if (!views.contains(view)){
			views.add(view);
		}
	}
	public void deleteView(V view){
		views.remove(view);
	}
	public void notifyViews(){
		for (V view : views) {
			view.update(this);
		}
	}
}
