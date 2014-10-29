package ca.ualberta.cs.corgFu;

public class UserName {

	private String currUserName;
	
	public UserName(String currUserName) {
		super();
		this.currUserName=currUserName;
		
	}
	
	public void saveUserName(){
		
	}

	public UserName loadUserName(){
		return null;
	}
	
	public String getUserName(){
			return this.currUserName;
	}
	
	public void setUserName(String username){
		this.currUserName=currUserName;
	}
	
}
