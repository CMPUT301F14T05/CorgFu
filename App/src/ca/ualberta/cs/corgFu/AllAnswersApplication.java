/*Based on Abram Hindle's FillerCreepApplication available at 
https://github.com/abramhindle/FillerCreepForAndroid 
  
Copyright 2013 Abram Hindle

Licensed under the Apache License, Version 2.0 (the "License"); you may not use 
this file except in compliance with the License. You may obtain a copy of the 
License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed 
under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
CONDITIONS OF ANY KIND, either express or implied. See the License for the specific 
language governing permissions and limitations under the License.
 */


package ca.ualberta.cs.corgFu;

import android.app.Application;
import ca.ualberta.cs.corgFuControllers.AllAnswersController;
import ca.ualberta.cs.corgFuModels.AllAnswers;

/**
 * The singleton class that allows for a singular allAnswers model to be
 * accessed and updated by a singular controller.
 * @author ajwu
 */
public class AllAnswersApplication extends Application {
	//singleton
	transient private static AllAnswers allAnswers = null;
	
	/**
	 * Returns the single local copy of all the answers that
	 * are currently available.
	 * <p>
	 * @return	The object that holds all available answers
	 */
	public static AllAnswers getAllAnswers() {
		if (allAnswers == null){
			allAnswers = new AllAnswers();
		}
		return allAnswers;
	}
	
	transient private static AllAnswersController  allAnswersController= null;
	/**
	 * Returns the single controller that is used to manipulate the 
	 * current copy of allAnswers that is currently available
	 * <p>
	 * @return the controller for the allAnswers object
	 */
	public static AllAnswersController getAllAnswersController(){
		if (allAnswersController == null){
			allAnswersController = new AllAnswersController(getAllAnswers());
		}
		return allAnswersController;
	}
	/**
	 * Sets the controller and model of allAnswers to be null.
	 * Used to empty the current copy of answers that is available,
	 * or for testing purposes.
	 */
	public static void destroy(){
		allAnswers = null;
		allAnswersController = null;
	}

}