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
	private static final String AuthorOffline = "AuthorOffline.save";
	private static final String saveString = "saving tracker"; // for debuging
	private final static int favouritesChoice = 0;
	private final static int cacheChoice =1;
	private final static int readLaterChoice = 2;
	private final static int MyQuestionsChoice = 3;
	private final static int AuthoredOfflineChoice = 4;
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
		Log.i(saveString,"start");
		try {
			Log.i(saveString, "fail1");
			FileOutputStream fos =null;
			if (choice ==favouritesChoice){
				fos =  context.openFileOutput(FavouritesFile,Context.MODE_PRIVATE);
			}else if(choice ==cacheChoice){
				fos =  context.openFileOutput(CacheFile,Context.MODE_PRIVATE);
			}else if(choice == readLaterChoice){
				fos = context.openFileOutput(ReadLater, Context.MODE_PRIVATE);
			}else if(choice == MyQuestionsChoice){
				fos = context.openFileOutput(MyQuestions, Context.MODE_PRIVATE);
			}else if (choice == AuthoredOfflineChoice){
				fos = context.openFileOutput(AuthorOffline, Context.MODE_PRIVATE);
			}
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
	
		// load the author offline
		// if offline QID doesn not exsist make question
		// if offline QID == an online then we assume its adding a answer
		// if offline AID == and online AID then we are adding a reply 
	
	// load based on choice 0 = fav, 1 =cache, 2= later
	@SuppressWarnings("unchecked")
	public ArrayList<Question> loadFavouritesFromFile(int choice){
		try{
			File fh =null;
			if(choice == favouritesChoice){
				fh = new File(context.getFilesDir(), DataManager.FavouritesFile);
			}else if (choice ==cacheChoice){
				fh = new File(context.getFilesDir(), DataManager.CacheFile);
			}else if (choice==readLaterChoice){
				fh = new File(context.getFilesDir(), DataManager.ReadLater);
			}else if (choice == MyQuestionsChoice){
				fh = new File(context.getFilesDir(), DataManager.MyQuestions);
			}else if (choice == AuthoredOfflineChoice){
				fh = new File(context.getFilesDir(), DataManager.AuthorOffline);
			}
			
			Log.i("banana", "load1");
			if (!fh.exists() ){
				Log.i("banana","creat new");
				ArrayList<Question> aq = new ArrayList<Question>();
				return aq;
			}
			FileInputStream fis =null;
			if(choice == favouritesChoice){
				fis = context.openFileInput(DataManager.FavouritesFile);
			}else if (choice ==cacheChoice){
				fis = context.openFileInput(DataManager.CacheFile);
			}else if (choice==readLaterChoice){
				fis = context.openFileInput(DataManager.ReadLater);
			}else if (choice == MyQuestionsChoice){
				fis = context.openFileInput(DataManager.MyQuestions);
			}else if (choice == AuthoredOfflineChoice){
				fis = context.openFileInput(DataManager.AuthorOffline);
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
