package ca.ualberta.cs.corgFu;

import java.util.Date;

public class Reply {
	private String replyString;
	private Date date;
	public Reply(String reply){
		this.replyString = reply;
		this.date = new Date();
	}

	public String getReplyString() {
		return replyString;
	}
	public Date getDate(){
		return date;
	}

}
