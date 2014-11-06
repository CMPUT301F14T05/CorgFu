package ca.ualberta.cs.corgFu;

import java.util.ArrayList;
import ca.ualberta.cs.corgFu.IView;

/**
 * A class that allows for MVC design and allows for the implementation of
 * the model portion of MVC. 
 * @author wrflemin
 *
 * @param <V> A view that conforms to the MVC type view by implementing
 * the IView class
 */
public class Model<V extends IView> {
	private ArrayList<V> views;
	
	/**
	 * Builds the Model which has an empty list of views to update.
	 */
	public Model(){
		views = new ArrayList<V>();
	}
	/**
	 * Adds a view to the model's list that the model will call 
	 * update on when it changes
	 * @param view A view that wants to be notified when the data
	 * of the model changes
	 */
	public void addView(V view){
		if (!views.contains(view)){
			views.add(view);
		}
	}
	/**
	 * Removes a view from the models list, meaning that the
	 * specified view will no longer be updated when the data
	 * of the model changes
	 * @param view The view that is to be removed from the model's 
	 * list of views to update
	 */
	public void deleteView(V view){
		views.remove(view);
	}
	/**
	 * Calls update on any of the views that want to be notified if 
	 * the data in the model changes.
	 */
	public void notifyViews(){
		for (V view : views) {
			view.update(this);
		}
	}
}
