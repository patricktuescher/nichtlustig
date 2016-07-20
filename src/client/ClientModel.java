package client;

import java.util.ArrayList;

public class ClientModel {
	
	private int playerRollCounter = 0;
	
	public int getPlayerRollCounter(){
		return this.playerRollCounter;
	}
	public void incrementPlayerRoll(){
		playerRollCounter++;
	}
	public void resetPlayerRoll(){
		playerRollCounter = 0;
	}
	public boolean allWürfelSelected(ArrayList<Würfel> würfel){
		boolean b = true;
		for(int x = 0; x < würfel.size(); x++){
			if(!würfel.get(x).isSelected())
				return false;
		}
		return b;
	}
	
	

	


}