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
	public void saveFavouritesToFile(ArrayList<Question> dataList,int choice){
		Log.i("apple","start");
		try {
			Log.i("apple", "fail1");
			FileOutputStream fos =null;
			if (choice ==0){
				fos =  context.openFileOutput(FavouritesFile,Context.MODE_PRIVATE);
			}else if(choice ==1){
				fos =  context.openFileOutput(CacheFile,Context.MODE_PRIVATE);
			}else if(choice == 2){
				fos = context.openFileOutput(ReadLater, Context.MODE_PRIVATE);
			}else if(choice == 3){
				fos = context.openFileOutput(MyQuestions, Context.MODE_PRIVATE);
			}
			Log.i("apple", "fail2");
			ObjectOutputStream osw = new ObjectOutputStream(fos);
			Log.i("apple", "fail4");
			osw.writeObject(dataList);
			Log.i("apple", "fail5");
			osw.close();
			Log.i("apple", "fail6");
			fos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
		
	
	// load based on choice 0 = fav, 1 =cache, 2= later
	@SuppressWarnings("unchecked")
	public ArrayList<Question> loadFavouritesFromFile(int choice){
		try{
			File fh =null;
			if(choice == 0){
				fh = new File(context.getFilesDir(), DataManager.FavouritesFile);
			}else if (choice ==1){
				fh = new File(context.getFilesDir(), DataManager.CacheFile);
			}else if (choice==2){
				fh = new File(context.getFilesDir(), DataManager.ReadLater);
			}else if (choice == 3){
				fh = new File(context.getFilesDir(), DataManager.MyQuestions);
			}
			Log.i("banana", "load1");
			if (!fh.exists() ){
				Log.i("banana","creat new");
				ArrayList<Question> aq = new ArrayList<Question>();
				return aq;
			}
			FileInputStream fis =null;
			if(choice == 0){
				fis = context.openFileInput(DataManager.FavouritesFile);
			}else if (choice ==1){
				fis = context.openFileInput(DataManager.CacheFile);
			}else if (choice==2){
				fis = context.openFileInput(DataManager.ReadLater);
			}else if (choice == 3){
				fis = context.openFileInput(DataManager.MyQuestions);
			}
			Log.i("banana", "load2");
			ObjectInputStream in;
			
			in = new ObjectInputStream(fis);
		
			Log.i("banana", "load3");
			//favourites = Favourites.getInstance();
			Log.i("banana", "load4");
			favouriteList = (ArrayList<Question>) in.readObject();
			Log.i("banana", "load5");
			
			
			Log.i("banana", "load6");
			
			in.close();
			fis.close();
			Log.i("banana", "load6");
			
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
