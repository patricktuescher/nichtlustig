package message;

import java.io.Serializable;

public class PointUpdate implements Serializable{
	
	private static final long serialVersionUID = 3575688533983935341L;
	private int points1, points2;
	
	public PointUpdate(int points1, int points2){
		this.points1 = points1;
		this.points2 = points2;
	}
	
	public int getPoints1(){
		return points1;
	}
	
	public int getPoints2(){
		return points2;
	}

}
