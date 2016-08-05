package message;

import java.io.Serializable;

public class newScore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5628868750138625248L;
	private int score;
	
	public newScore(int score){
		this.score = score;
	}
	public int getScore(){
		return this.score;
	}
}
