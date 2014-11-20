package ca.ualberta.cs.corgFu;




public class UserName {
	private static UserName instance = null;
	private String uN;
	public UserName(){
		
	}
	public static UserName getInstance(){
		if (instance == null){
			instance = new UserName();
		}
		return instance;
	}
	public void setUserName(String uNameString){
		instance.uN = uNameString; 
	}
	
	public String getUserName(){
		return instance.uN;
	}

	
}
