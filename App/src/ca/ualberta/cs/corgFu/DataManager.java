package ca.ualberta.cs.corgFu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import ca.ualberta.cs.corgFuModels.Question;
import ca.ualberta.cs.corgFuViews.MainActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
/**
 * This is a class that handles all of our data, saving each respective
 * type of data in a particular save directory. Whether the type is favorites,
 * cached, read later, my questions or unpushed data
 * @author Ahmed
 *
 */
public class DataManager {
	private static final String FavouritesFile = "Favourites.save";
	private static final String CacheFile = "CacheFile.save";
	private static final String ReadLater = "ReadLater.save";
	private static final String MyQuestions = "MyQuestions.save";
	private static final String toBePushed = "Unpushed.save";
	private static final String saveString = "saving tracker";
	private Context context;
	private static DataManager INSTANCE = null;
	public ArrayList<Question> questionList;
	public static Boolean control = true;
	public DataManager(){
		
		this.context = MainActivity.context;
		
	}
	/**
	 * singleton
	 * Returns the single instance that is used to manipulate the 
	 * current copy of getInstance that is currently available
	 * @return the instance to be manipulated 
	 */
	public static DataManager getInstance(){
		if (INSTANCE == null){
			INSTANCE = new DataManager();
		}
		return INSTANCE;
	}
	
	/**
	 * save and load data from files 
	 * saves data as an array of questions, the choice indicates if its a 
	 * favourite, read later, cache respectively
	 * @param dataList is an array list of Questions for the data being saved
	 * @param choice is the choice name of the file we are saving to
	 */
	public void saveDataToFile(ArrayList<Question> dataList,String choice){
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
			Log.i(saveString, "passed");
			fos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
		
	
	/**
	 * load based on choice favourites, cache,read later, unpushed and questions
	 * from the files that were saved
	 * @param choice is the string of the file we are loading from
	 * @return the data in the file that we are currently loading from
	 */
	public ArrayList<Question> loadDataToFile(String choice){
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
			questionList = gson.fromJson(in, listType);
			for(Question q:questionList){
				Log.i("loaded q", q.getQuestionText());
			}
			Log.i("Loading", "load5");
			
			
			in.close();
			fis.close();
			Log.i("Loading", "Passed");
			
		} catch (FileNotFoundException e){
			Log.i("Loading", "Filenotfound");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return questionList;
	}
	
}
