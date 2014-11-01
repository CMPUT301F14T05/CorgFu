package ca.ualberta.cs.corgFu;

import android.app.Application;
import ca.ualberta.cs.corgFuControllers.AllQuestionsController;
import ca.ualberta.cs.corgFuControllers.AuthoredOfflineController;
import ca.ualberta.cs.corgFuModels.AllQuestions;
import ca.ualberta.cs.corgFuModels.AuthoredOffline;

public class AllAuthoredOfflineApplication extends Application{
	//singleton
	transient private static AuthoredOffline authoredOffline = null;
	
	public static AuthoredOffline getAuthoredOffline() {
		if (authoredOffline == null){
			authoredOffline = new AuthoredOffline();
		}
		return authoredOffline;
	}
	
	transient private static AuthoredOfflineController  authoredOfflineController= null;
	
	public static AuthoredOfflineController getAuthoredOfflineController(){
		if (authoredOfflineController == null){
			authoredOfflineController = new AuthoredOfflineController(getAuthoredOffline());
		}
		return authoredOfflineController;
	}

}


