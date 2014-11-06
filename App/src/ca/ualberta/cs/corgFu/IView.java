package ca.ualberta.cs.corgFu;

/**
 * @author wrflemin
 *
 * @param <M> A model that extends the Model class.
 */
public interface IView<M> {
	/**
	 * A function that will be called by the model on 
	 * the view when the model's data changes. 
	 * @param model The model that extends the Model class
	 * that is calling update on the view.
	 */
	public void update(M model);
}
