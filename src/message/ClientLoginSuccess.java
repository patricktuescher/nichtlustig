/**
	 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
	 * All rights reserved.
	 * 
	 * @function 
	 * @author 
	 */

package message;

import java.io.Serializable;

public class ClientLoginSuccess implements Serializable{

	private static final long serialVersionUID = 4060621748129726024L;
	private boolean success;
	
	public ClientLoginSuccess(boolean success){
		this.success = success;
	}
	
	public boolean getSuccess(){
		return this.success;
	}

}
