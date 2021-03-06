/**
	 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
	 * All rights reserved.
	 * 
	 * @function 
	 * @author 
	 */

package message;

import java.io.Serializable;
import java.util.ArrayList;

import client.Würfel;

public class EvaluateFirstPlayer implements Serializable{

	private ArrayList<Würfel> würfel;
	private static final long serialVersionUID = -8611920176794530185L;
	
	public EvaluateFirstPlayer(ArrayList<Würfel> würfel){
		this.würfel = würfel;
	}
	public ArrayList<Würfel> getWürfel(){
		return this.würfel;
	}

}
