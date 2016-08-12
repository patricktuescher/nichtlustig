package message;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
 * All rights reserved.
 * 
 */

public class HighscoreUpdate implements Serializable{

	private static final long serialVersionUID = -6723499748449442911L;
	
	private ArrayList <String> scoreValues;
	private ArrayList <String> nameValues;
	private ArrayList <String> dateValues;
	
	public HighscoreUpdate(){
		
	}
	public HighscoreUpdate(ArrayList<String> scoreValues, ArrayList<String> nameValues, ArrayList<String> dateValues){
		this.scoreValues = scoreValues;
		this.nameValues = nameValues;
		this.dateValues = dateValues;
	}
	public ArrayList<String> getScoreValues(){
		return scoreValues;
	}
	public ArrayList<String> getNameValues(){
		return nameValues;
	}
	public ArrayList<String> getDateValues(){
		return dateValues;
	}

}
