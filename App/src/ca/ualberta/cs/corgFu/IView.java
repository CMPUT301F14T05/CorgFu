package ca.ualberta.cs.corgFu;


/**
 * Interface that allows for an MVC design. This class allows for the 
 * view portion of MVC and requires that implementing classes implement
 * the update function.
 * @author wrflemin
 */
public interface IView {
	/**
	 * A function that will be called by the model on 
	 * the view when the model's data changes. 
	 */
	public void update();
}
