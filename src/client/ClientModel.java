package client;

import java.util.ArrayList;

import javafx.application.Platform;

public class ClientModel {
	
	private int playerRollCounter = 0;
	
	cardChecker cc = new cardChecker();
	
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
	
	public void startCardChecker(ArrayList<Card> cardAL, ArrayList<Würfel> wuerfelAL){
		ArrayList<Würfel> wuerfeltoTest = new ArrayList<Würfel>();
		wuerfeltoTest.addAll(wuerfelAL);
		for(int x = 0;x<31;x++){
			cardAL.get(x).check(wuerfeltoTest);
		}
		
		
		}
	
	

	


}