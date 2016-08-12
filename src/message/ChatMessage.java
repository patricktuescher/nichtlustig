/**
	 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
	 * All rights reserved.
	 * 
	 * @function 
	 * @author 
	 */

package message;

/**
 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
 * All rights reserved.
 * 
 */
import java.io.Serializable;

public class ChatMessage implements Serializable{
	
	private static final long serialVersionUID = 6059145399295545383L;
	private String msg;
	
	public ChatMessage(String msg){
		this.msg = msg;
	}
	
	public String getMessage(){
		return msg;
	}



}
