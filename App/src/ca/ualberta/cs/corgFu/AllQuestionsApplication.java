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
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuModels.AllQuestions;

/**
 * The singleton class that allows for a singular allQuestions model to be
 * accessed and updated by a singular controller.
 * @author wrflemin
 */
public class AllQuestionsApplication extends Application {
	//singleton
	transient private static AllQuestions allQuestions = null;
	
	/**
	 * Returns the single local copy of all the questions that
	 * are currently available.
	 * <p>
	 * @return	The object that holds all available questions
	 */
	public static AllQuestions getAllQuestions() {
		if (allQuestions == null){
			allQuestions = new AllQuestions();
		}
		return allQuestions;
	}
	
	transient private static AllQuestionsController  allQuestionsController= null;
	/**
	 * Returns the single controller that is used to manipulate the 
	 * current copy of allQuestions that is currently available
	 * <p>
	 * @return the controller for the allQuestions object
	 */
	public static AllQuestionsController getAllQuestionsController(){
		if (allQuestionsController == null){
			allQuestionsController = new AllQuestionsController(getAllQuestions());
		}
		return allQuestionsController;
	}
	/**
	 * Sets the controller and model of allQuestions to be null.
	 * Used to empty the current copy of questions that is available,
	 * or for testing purposes.
	 */
	public static void destroy(){
		allQuestions = null;
		allQuestionsController = null;
	}

}
