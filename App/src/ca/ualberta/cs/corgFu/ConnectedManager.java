package ca.ualberta.cs.corgFu;

import ca.ualberta.cs.corgFuControllers.DataController;
import android.content.Context;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.util.Log;

/**
 * This is the class that is called by all activities to determine whether
 * or not the application has connection. Any activity switch will invoke
 * a connection check and this will return whether or not we are on or offline
 * @author Ahmed
 *
 */
public class ConnectedManager {
	private static ConnectedManager instance=null;
	Context context;
	DataController DC = new DataController();
	public ConnectedManager(){
		
	}
	/**
	 * Returns the single instance that is used to manipulate the 
	 * current copy of getInstance that is currently available
	 * @return
	 */
	public static ConnectedManager getInstatnce(){
		if(instance == null){
			instance = new ConnectedManager();
		}
		return instance;
	}
	/**
	 * setting the main Context that is used
	 * @param mainContext is the main Context that is set
	 */
	public void setContext(Context mainContext){
		context = mainContext;
	}
	/**
	 * Determine whether or not we are connected by using the connectivity manager and
	 * using to connectivity of the phone.
	 * @return a boolean result of whether or not we are connected
	 */
	public Boolean isConnexted(){
		//Connectivity Check
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();

		if(activeNetInfo!=null){
			attemptToPushOfflineContent();
			return true;
		}else{
			return false;
		}
	}
	public void attemptToPushOfflineContent(){
		Log.i("Main Attempt", "start push");
			
		DC.pushOfflineContent();
		
	}
}
