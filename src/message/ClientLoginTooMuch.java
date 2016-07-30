/**
	 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
	 * All rights reserved.
	 * 
	 * @function 
	 * @author 
	 */

package message;

import java.io.Serializable;

public class ClientLoginTooMuch implements Serializable {
	
	private boolean success;
	
	public ClientLoginTooMuch(boolean success){
		this.success = success;
	}
	
	public boolean getSuccess(){
		return this.success;
	}

}

