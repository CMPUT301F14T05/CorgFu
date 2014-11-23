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
	
	public Question getQuestionById(int id, int choice){
		ArrayList<Question> questions = getData(choice);
		for (Question q:questions){
			if (q.getId() == id){
				return q;
			}
		}
		return null;
	}
	
	public void clearData(int choice){
		ArrayList<Question> newList = new ArrayList<Question>();
		mdm.saveFavouritesToFile(newList, choice);
	}

	// Warning: throws a runTimeException

	public void saveData(ArrayList<Question> dataToSave,int choice){
		mdm.saveFavouritesToFile(dataToSave, choice);
	}
	
	public ArrayList<Question> getData(int choice){
		dataList = mdm.loadFavouritesFromFile(choice);
		
		return dataList;
	}
	
	// add data to file
	// 0 = fav
	// 1 = cache
	// 2 = read later
	// 3 = asked
	// adds data and updates it if it exsits.
	public void addData( Question q,int choice) {
		Log.i("apple","got here");
		Log.i("Question", q.getQuestionText());
		dataList = mdm.loadFavouritesFromFile(choice);
		Log.i("AF", "before add");
		boolean addControl =false;
		int i=0;
		
		if (dataList.size()!=0){ // if its not empty
			for(Question o: dataList){ // if the data is in the list 
				if (o.getId() ==q.getId()){ // then update the list
					dataList.set(i, q);
					addControl=true;
					break;
				} 
				i++;
			}
		}	
		// add if not in it
		if (!addControl){
			dataList.add(q);
		} // if it already exsist just save
		//changed
		Log.i("AF", "passed add");
		mdm.saveFavouritesToFile(dataList,choice);
	}
}
