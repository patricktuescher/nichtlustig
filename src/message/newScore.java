package message;

import java.io.Serializable;
/**
 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
 * All rights reserved.
 * 
 */

public class newScore implements Serializable {

	private static final long serialVersionUID = 5628868750138625248L;
	private int score;
	
	public newScore(int score){
		this.score = score;
	}
	public int getScore(){
		return this.score;
	}
}
