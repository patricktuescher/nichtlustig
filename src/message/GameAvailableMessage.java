/**
	 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
	 * All rights reserved.
	 * 
	 * @function 
	 * @author 
	 */

package message;

import java.io.Serializable;

public class GameAvailableMessage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7179519019155023027L;
	private boolean available;
	private String s;
	
	public GameAvailableMessage(boolean available){
		this.available = available;
	}
	public GameAvailableMessage(boolean available, String s){
		this.available = available;
		this.s = s;
	}
	public boolean getAvailibility(){
		return this.available;
	}
	public String getOtherConnectionName(){
		return this.s;
	}

}
