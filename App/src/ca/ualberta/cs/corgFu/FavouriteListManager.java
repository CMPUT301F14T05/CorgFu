package ca.ualberta.cs.corgFu;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;
import ca.ualberta.cs.corgFuModels.Favourites;


public class FavouriteListManager {
	static final String prefFile = "FavouriteList";
	static final String slKey = "favList"; 
	
	Context context;
	
	static private FavouriteListManager favouriteListManager = null;
	public static void initManager(Context context){
		if (favouriteListManager ==null){
			if (context ==null){
				throw new RuntimeException("missing context for favlist");
			}
			favouriteListManager = new FavouriteListManager(context);
		}
	}
	public static FavouriteListManager getManager(){
		if (favouriteListManager == null){
			throw new RuntimeException("Did not initialize Manager");
		}
		return favouriteListManager;
	}
	
	public FavouriteListManager(Context context) {
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	public Favourites loadFavouriteList() throws ClassNotFoundException, IOException {
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String favouriteListData = settings.getString(slKey, "");
		if(favouriteListData.equals("")){
			return new Favourites();
		}else{
			return favouriteListFromString(favouriteListData);
		}
	}

	static public Favourites favouriteListFromString(String favouriteListData) throws ClassNotFoundException,IOException{
		ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(favouriteListData, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (Favourites)oi.readObject();
	}
	static public String favouriteListToString(Favourites fl) throws IOException{
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(fl);
		oo.close();
		byte bytes[] = bo.toByteArray();
		return Base64.encodeToString(bytes,Base64.DEFAULT);
	}
	public void managerSaveFavList(Favourites favouriteList) throws IOException {
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(slKey, favouriteListToString(favouriteList));
		editor.commit();
		
		// TODO Auto-generated method stub
		
	}

	
}
