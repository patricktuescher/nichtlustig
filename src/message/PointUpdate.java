package message;

import java.io.Serializable;

public class PointUpdate implements Serializable{
	
	private static final long serialVersionUID = 3575688533983935341L;
	private int points;
	
	public PointUpdate(int points){
		this.points = points;
	}
	
	public int getPoints(){
		return points;
	}

}
