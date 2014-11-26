package ca.ualberta.cs.corgFuControllers;

import java.util.ArrayList;

import android.util.Log;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFu.DataManager;
import ca.ualberta.cs.corgFuModels.Answer;
import ca.ualberta.cs.corgFuModels.Question;
import ca.ualberta.cs.corgFuModels.Reply;

public class DataController {

	
	//private static OfflineData Data;
	//should this be a singleton?
	private static ArrayList<Question> dataList;
	public static DataManager mdm;
	AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
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

	public void pushOfflineContent() {
		// TODO Auto-generated method stub
		ArrayList<Question> offlineList = mdm.loadFavouritesFromFile("Unpushed.save");
		for(Question q: offlineList){
			Log.i("push offline",q.getQuestionText());
			boolean keepGoing = pushQuestion(q);
			if(keepGoing){
				pushQuestionReplies(q);
				pushAnswers(q);
			}
			setToPushed(q);
		}
		clearData("Unpushed.save");
	}
	private void pushAnswers(Question q) {
		for(Answer a: q.getAnswers()){
			if(a.isPushed()==false){
				for(Reply r: a.getReplies()){
					r.setPushed(true);
				}
				QAController QAC = new QAController(q);
				QAC.addAnswer(a);
				
				//push answer using AQC
			}else{
				pushAnswerReplies(a);
			}
		}
	}
	private void pushAnswerReplies(Answer a){
		for(Reply r: a.getReplies()){
			if (r.isPushed()==false){
				
				//push reply
			}
		}
	}
	private void pushQuestionReplies(Question q){
		ArrayList<Reply> replies = q.getReplies();
		for(Reply r: replies){
			if(r.isPushed()==false){
				r.setPushed(true);
				//push reply r
			}
		}
	}
	private boolean pushQuestion(Question q) {
		if(q.getIsPushed()==false){
			setToPushed(q);
			AQC.addQuestion(q);
			return false;
		}else{
			return true;
		}
	}
	private void setToPushed(Question q){
		q.setIsPushed(true);
		for(Reply r:q.getReplies()){
			r.setPushed(true);
		}
		ArrayList<Answer> answerList = q.getAnswers();
		for(Answer a: answerList){
			a.setPushed(true);
			for(Reply r: a.getReplies()){
				r.setPushed(true);
			}
		}
	}

}
