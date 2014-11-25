package ca.ualberta.cs.corgFu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import ca.ualberta.cs.corgFuModels.OfflineData;
import ca.ualberta.cs.corgFuModels.Question;
import ca.ualberta.cs.corgFuViews.LoginActivity;
import ca.ualberta.cs.corgFuViews.MainActivity;

public class DataManager {
	private static final String FavouritesFile = "Favourites.save";
	private static final String CacheFile = "CacheFile.save";
	private static final String ReadLater = "ReadLater.save";
	private static final String MyQuestions = "MyQuestions.save";
	private static final String saveString = "saving tracker";
	private Context context;
	private static DataManager INSTANCE = null;
	public ArrayList<Question> favouriteList;
	public static Boolean control = true;
	public static OfflineData favourites;
	public DataManager(){
		
		this.context = MainActivity.context;
		
	}
	//data manager singleton
	public static DataManager getInstance(){
		if (INSTANCE == null){
			INSTANCE = new DataManager();
		}
		return INSTANCE;
	}
	
	//save and load data from file
	// saves data as an array of questions, the choice indicates if its a 
	// favourite, read later, cache  (0,1,2) respectively
	public void saveFavouritesToFile(ArrayList<Question> dataList,String choice){
		Log.i(saveString,"start");
		try {
			Log.i(saveString, "fail1");
			FileOutputStream fos =null;
			
			fos =  context.openFileOutput(choice,Context.MODE_PRIVATE);
			
			Log.i(saveString, "fail2");
			ObjectOutputStream osw = new ObjectOutputStream(fos);
			Log.i(saveString, "fail4");
			osw.writeObject(dataList);
			Log.i(saveString, "fail5");
			osw.close();
			Log.i(saveString, "fail6");
			fos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
		
	
	// load based on choice 0 = fav, 1 =cache, 2= later
	@SuppressWarnings("unchecked")
	public ArrayList<Question> loadFavouritesFromFile(String choice){
		try{
			File fh = new File(context.getFilesDir(), choice);
			
			Log.i("Loading", "load1");
			if (!fh.exists() ){
				Log.i("Loading","create new");
				ArrayList<Question> aq = new ArrayList<Question>();
				return aq;
			}
			FileInputStream fis = context.openFileInput(choice);
			
			Log.i("Loading", "load2");
			ObjectInputStream in;
			
			in = new ObjectInputStream(fis);
		
			Log.i("Loading", "load3");
			//favourites = Favourites.getInstance();
			Log.i("Loading", "load4");
			favouriteList = (ArrayList<Question>) in.readObject();
			Log.i("Loading", "load5");
			
			
			in.close();
			fis.close();
			Log.i("Loading", "Passed");
			
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		return favouriteList;
	}
	
}
