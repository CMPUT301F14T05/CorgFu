package ca.ualberta.cs.corgFu;

import ca.ualberta.cs.corgFuControllers.DataController;
import android.content.Context;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.util.Log;

public class ConnectedManager {
	private static ConnectedManager instance=null;
	Context context;
	DataController DC = new DataController();
	public ConnectedManager(){
		
	}
	
	public static ConnectedManager getInstatnce(){
		if(instance == null){
			instance = new ConnectedManager();
		}
		return instance;
	}
	public void setContext(Context mainContext){
		context = mainContext;
	}
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
