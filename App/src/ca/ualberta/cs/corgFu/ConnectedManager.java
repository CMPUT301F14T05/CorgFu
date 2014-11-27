package ca.ualberta.cs.corgFu;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import ca.ualberta.cs.corgFuControllers.DataController;

public class ConnectedManager {
	private Context myContext;
	private DataController DC;
	private Context context;
	private static ConnectedManager instance=null;
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
			return true;
		}else{
			return false;
		}
	}
	public void attemptToPushOfflineContent(){
		boolean isConnect = isConnexted();
		if (isConnect )
		{
			DC.pushOfflineContent();
		}
		
	}
}
