/**
	 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
	 * All rights reserved.
	 * 
	 * @function 
	 * @author 
	 */

package client;

import java.util.ArrayList;

public class ClientModel {
	
	private int playerRollCounter = 0;
	protected int cardCounter;
	
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
		cardCounter = 0;
		
		while(w<wuerfelAL.size()){
			if(!wuerfelAL.get(w).isUsed()){
			summe = summe + wuerfelAL.get(w).getAktAugenzahl();
			}
			w++;
		}
		
		for(int x = 0;x<31;x++){
			if(cardAL.get(x).check(wuerfelAL, summe)){
				cardCounter++;
			};
		}
		
		if(cardCounter != 0){
			for(int x = 0; x<31;x++){
				if(cardAL.get(x).getType().equals(cardType.Tod.toString()))
					cardAL.get(x).getImage().setOpacity(0.5);
							}
		}
		

		
	}
	
	public void aktivateCards(ArrayList<Card> cardAL, ArrayList<Würfel> wuerfelAL){
		int summe = 0;
		int w = 0;
		
		while(w<wuerfelAL.size()){
			if(!wuerfelAL.get(w).isUsed()){
			summe = summe + wuerfelAL.get(w).getAktAugenzahl();
			}
			w++;
		}
		
		for(int x = 0;x<31;x++){
			if(cardCounter > 1){
				if(cardAL.get(x).check(wuerfelAL, summe)){
					if(cardAL.get(x).getType().equals(cardType.Tod.toString())){
						cardAL.get(x).getImage().setOpacity(0.5);
					}else{
					cardAL.get(x).getImage().setDisable(false);
					}
				}
			}else if(cardAL.get(x).check(wuerfelAL, summe)){
				cardAL.get(x).getImage().setDisable(false);
				
			}
		}	
	}
	
	
	public void aktivateGewerteteCards(ArrayList<Card> cardAL, cardType cType){
		for(int x = 0;  x<31; x++){
			if(cardAL.get(x).getOwner().equals(ClientController.clientOwner) && cardAL.get(x).getStatus().equals(Status.gewertet.toString()) && cardAL.get(x).getType().equals(cType.toString())){
				cardAL.get(x).getImage().setDisable(false);
				cardAL.get(x).getImage().setOpacity(1);
			}
			
		}
		
	}

	
	public boolean checkLemmingCards(ArrayList<Card> cardAL){
		int countLemming = 0;
		for(int x = 0;  x<31; x++){
			if(cardAL.get(x).getOwner().equals(ClientController.clientOwner) && cardAL.get(x).getStatus().equals(Status.gewertet.toString()) && cardAL.get(x).getType().equals(cardType.Lemming.toString())){
			countLemming++;
			}
			}
		
		if(countLemming== 0){
			return false;
		}else{
			return true;
		}
		
	}
	
	
	
	
	
	
	
	

	


}