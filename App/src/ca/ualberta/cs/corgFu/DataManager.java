package ca.ualberta.cs.corgFu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.StreamCorruptedException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import ca.ualberta.cs.corgFuModels.OfflineData;
import ca.ualberta.cs.corgFuModels.Question;
import ca.ualberta.cs.corgFuViews.MainActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DataManager {
	private static final String FavouritesFile = "Favourites.save";
	private static final String CacheFile = "CacheFile.save";
	private static final String ReadLater = "ReadLater.save";
	private static final String MyQuestions = "MyQuestions.save";
	private static final String toBePushed = "Unpushed.save";
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
			FileOutputStream fos =  context.openFileOutput(choice,Context.MODE_PRIVATE);
			Gson gson = new Gson();
			Log.i(saveString, "fail2");
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			Log.i(saveString, "fail4");
			gson.toJson(dataList,osw);
			Log.i(saveString, "fail5");
			osw.flush();
			Log.i(saveString, "fail6");
			fos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
		
	
	// load based on choice 0 = fav, 1 =cache, 2= later
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

			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			
			Log.i("Loading", "load2");
			Gson gson = new Gson();
		
			Log.i("Loading", "load3");
			Type listType = new TypeToken<ArrayList<Question>>(){}.getType();
			//favourites = Favourites.getInstance();
			Log.i("Loading", "load4");
			favouriteList = gson.fromJson(in, listType);
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
		} 
		return favouriteList;
	}
	
}
