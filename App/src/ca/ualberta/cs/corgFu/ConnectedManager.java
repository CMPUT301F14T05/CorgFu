package ca.ualberta.cs.corgFu;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import ca.ualberta.cs.corgFuControllers.DataController;

public class ConnectedManager {
	private Context myContext;
	private DataController DC;
	public ConnectedManager(Context context){
		myContext = context;
		DC = new DataController();
		attemptToPushOfflineContent();
	}
	
	public Boolean isConnexted(){
		//Connectivity Check
		ConnectivityManager connectivityManager = (ConnectivityManager) myContext.getSystemService(Context.CONNECTIVITY_SERVICE);
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
