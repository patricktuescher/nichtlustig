package client;

import javafx.stage.Stage;

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
	


}