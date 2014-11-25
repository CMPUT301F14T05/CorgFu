package ca.ualberta.cs.corgFuControllers;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import ca.ualberta.cs.corgFu.DataManager;
import ca.ualberta.cs.corgFuModels.Question;

public class DataController {

	
	//private static OfflineData Data;
	//should this be a singleton?
	private static ArrayList<Question> dataList;
	public static DataManager mdm;
	
	public DataController(){
		mdm = DataManager.getInstance();
		//Data = OfflineData.getInstance();
		// Warning: throws a runTimeException
	}
	
	public Question getQuestionById(int id, String choice){
		ArrayList<Question> questions = getData(choice);
		for (Question q:questions){
			if (q.getId() == id){
				return q;
			}
		}
		return null;
	}
	
	public void clearData(String choice){
		ArrayList<Question> newList = new ArrayList<Question>();
		mdm.saveFavouritesToFile(newList, choice);
	}

	// Warning: throws a runTimeException

	public void saveData(ArrayList<Question> dataToSave,String choice){
		mdm.saveFavouritesToFile(dataToSave, choice);
	}
	
	public ArrayList<Question> getData(String choice){
		dataList = mdm.loadFavouritesFromFile(choice);
		
		return dataList;
	}
	
	// add data to file
	// 0 = fav
	// 1 = cache
	// 2 = read later
	// 3 = asked
	// adds data and updates it if it exsits.
	public void addData( Question q,String choice) {
		Log.i("apple","got here");
		Log.i("Question", q.getQuestionText());
		dataList = mdm.loadFavouritesFromFile(choice);
		Log.i("AF", "before add");
		boolean addControl =false;
		int i=0;
		for(Question o: dataList){
			if (o.getId() ==q.getId()){
				dataList.set(i, q);
				addControl=true;
				break;
			} 
			i++;
		}
		if (!addControl){
			dataList.add(q);
		}
		//changed
		Log.i("AF", "passed add");
		mdm.saveFavouritesToFile(dataList,choice);
	}
}
