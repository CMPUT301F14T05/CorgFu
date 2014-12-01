package ca.ualberta.cs.corgFuControllers;

import java.util.ArrayList;

import android.util.Log;
import ca.ualberta.cs.corgFu.AllQuestionsApplication;
import ca.ualberta.cs.corgFu.DataManager;
import ca.ualberta.cs.corgFuModels.Answer;
import ca.ualberta.cs.corgFuModels.Question;
import ca.ualberta.cs.corgFuModels.Reply;

/**
 * This is the controller for all data manipulation, and all data that is passed
 * to the data manager. That is questions, cached, favorited, and unpushed content.
 * @author Ahmed
 *
 */
public class DataController {

	
	//private static OfflineData Data;
	QAController QACA;
	//should this be a singleton?
	private static ArrayList<Question> dataList;
	public static DataManager mdm;
	AllQuestionsController AQC = AllQuestionsApplication.getAllQuestionsController();
	public DataController(){
		mdm = DataManager.getInstance();
	}
	/**
	 * This gets the question by id based on the id passed and the file type you want
	 * to receive it from
	 * @param id is the ID of the question
	 * @param choice is the file type you want to retrieve the question from
	 * @return the question based off the id
	 */
	public Question getQuestionById(int id, String choice){
		ArrayList<Question> questions = getData(choice);
		for (Question q:questions){
			if (q.getId() == id){
				return q;
			}
		}
		return null;
	}
	
	/**
	 * This clears all of the data of the file of a specific data type
	 * @param choice is the file type you would like to clear (favorites, cached, etc.)
	 */
	public void clearData(String choice){
		ArrayList<Question> newList = new ArrayList<Question>();
		mdm.saveDataToFile(newList, choice);
	}

	/**
	 * This uses the data manager and the data and file type and then saves that
	 * data to the file using the data manager
	 * @param dataToSave is the Arraylist of Questions that is willing to be saved
	 * @param choice is the file type of data
	 */
	public void saveData(ArrayList<Question> dataToSave,String choice){
		mdm.saveDataToFile(dataToSave, choice);
	}
	
	/**
	 * Returns the Array list from the data manager of the Questions from a
	 * specific file type
	 * @param choice is the file data type you would like data from
	 * @return
	 */
	public ArrayList<Question> getData(String choice){
		dataList = mdm.loadDataToFile(choice);
		
		return dataList;
	}
	
	/**
	 * adding data (a question) to a specific file type based on a choice
	 * @param q is a question that is passed
	 * @param choice is the file type that you are adding data to
	 */
	public void addData( Question q,String choice) {
		Log.i("apple","got here");
		Log.i("Question", q.getQuestionText());
		dataList = mdm.loadDataToFile(choice);
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
		mdm.saveDataToFile(dataList,choice);
	}
	
	/**
	 * when called, pushes all of the offline data that is in the unpushed file type
	 * online and then clears the unpushed save file
	 */
	public void pushOfflineContent() {
		// TODO Auto-generated method stub
		ArrayList<Question> offlineList = mdm.loadDataToFile("Unpushed.save");
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
	/**
	 * pushes all of the answers of a question online
	 * @param q the question in which you would like the answer to be pushed online for
	 */
	private void pushAnswers(Question q) {
		QACA = new QAController(q);
		for(Answer a: q.getAnswers()){
			if(a.isPushed()==false){
				for(Reply r: a.getReplies()){
					r.setPushed(true);
				}
				a.setPushed(true);
				QACA.addAnswer(a);
				
				//push answer using AQC
			}else{
				pushAnswerReplies(a);
			}
		}
	}
	/**
	 * pushes all of the replies of an answer online
	 * @param a the question in which you would like the answer to be pushed online for
	 */
	private void pushAnswerReplies(Answer a){
		for(Reply r: a.getReplies()){
			if (r.isPushed()==false){
				r.setPushed(true);
				QACA.addReplyToAnswer(r, a.getId());
				//push reply
			}
		}
	}
	
	/**
	 * pushes all of the replies of an answer online
	 * @param q the question in which you would like the answer to be pushed online for
	 */
	private void pushQuestionReplies(Question q){
		QAController QACC = new QAController(q);
		ArrayList<Reply> replies = q.getReplies();
		for(Reply r: replies){
			if(r.isPushed()==false){
				r.setPushed(true);
				QACC.addReply(r);
				//push reply r
			}
		}
	}
	/**
	 * pushes a question online and sees whether or not it was pushed
	 * @param q is the question that is being pushed
	 * @return whether or not it was pushed by a boolean
	 */
	private boolean pushQuestion(Question q) {
		if(q.getIsPushed()==false){
			setToPushed(q);
			AQC.addQuestion(q);
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * if a question has been pushed online, the question is set to pushed being true
	 * @param q the question that has been pushed
	 */
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
