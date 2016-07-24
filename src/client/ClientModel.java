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
		int summe = 0;
		int w = 0;
		
		while(w<wuerfelAL.size()){
			if(!wuerfelAL.get(w).isUsed()){
			summe = summe + wuerfelAL.get(w).getAktAugenzahl();
			}
			w++;
		}
		
		for(int x = 0;x<31;x++){
			cardAL.get(x).check(wuerfelAL, summe);
		}
		
		
		}
	
	

	


}