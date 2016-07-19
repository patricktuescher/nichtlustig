package message;

import java.io.Serializable;

public class ChatMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6059145399295545383L;
	private String msg;
	
	public ChatMessage(String msg){
		this.msg = msg;
	}
	
	public String getMessage(){
		return msg;
	}



}
