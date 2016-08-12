package message;

import java.io.Serializable;
/**
 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
 * All rights reserved.
 * 
 */


public class Registration implements Serializable {
	private static final long serialVersionUID = -3905115075548104682L;
	private boolean check;
	
	
	public Registration(boolean check){
		this.check = check;
	}
	
	public boolean getCheck(){
		return check;
	}

	
	
}
