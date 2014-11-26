package ca.ualberta.cs.corgFu;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;

public class ConnectedManager {
	private static ConnectedManager instance=null;
	Context context;
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
}
