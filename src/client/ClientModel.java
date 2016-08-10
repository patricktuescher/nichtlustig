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
	protected ServerListener server;
	protected int countDeath;

	
	public int getPlayerRollCounter(){
		return this.playerRollCounter;
	}
	public void incrementPlayerRoll(){
		playerRollCounter++;
	}
	public void resetPlayerRoll(){
		playerRollCounter = 0;
	}
	public boolean allDiesSelected(ArrayList<Würfel> dies){
		boolean b = true;
		for(int x = 0; x < dies.size(); x++){
			if(!dies.get(x).isSelected())
				return false;
		}
		return b;
	}
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public void startCardChecker(ArrayList<Card> cardAL, ArrayList<Würfel> diesAL){
		int summe = 0;
		int w = 0;
		cardCounter = 0;
		
		while(w<diesAL.size()){
			if(!diesAL.get(w).isUsed()){
			summe = summe + diesAL.get(w).getCurrentNumberofeyes();
			}
			w++;
		}
		
		for(int x = 0;x<31;x++){
			if(cardAL.get(x).check(diesAL, summe)){
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
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public void activateCards(ArrayList<Card> cardAL, ArrayList<Würfel> diesAL, Account acc){
		int summe = 0;
		int w = 0;

		
		while(w<diesAL.size()){
			if(!diesAL.get(w).isUsed()){
			summe = summe + diesAL.get(w).getCurrentNumberofeyes();
			}
			w++;
		}
		
		for(int x = 0;x<31;x++){
			if(cardCounter > 1){
				if(cardAL.get(x).check(diesAL, summe)){
					if(cardAL.get(x).getType().equals(cardType.Tod.toString())){
						cardAL.get(x).getImage().setOpacity(0.5);
					}else{
					cardAL.get(x).getImage().setDisable(false);
					}
				}
			}else if(cardAL.get(x).check(diesAL, summe)){
				cardAL.get(x).getImage().setDisable(false);
				
			}
		}
		
		if(cardCounter == 0){
	
			chooseDeathCard(cardAL, diesAL, acc);
			
		};
	}
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public void activateValuedCards(ArrayList<Card> cardAL, cardType cType){
		for(int x = 0;  x<31; x++){
			if(cardAL.get(x).getOwner().equals(ClientController.clientOwner) && cardAL.get(x).getStatus().equals(Status.gewertet.toString()) && cardAL.get(x).getType().equals(cType.toString())){
				cardAL.get(x).getImage().setDisable(false);
				cardAL.get(x).getImage().setOpacity(1);
			}
			
		}
		
	}

	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public boolean checkCardsToChooseDeath(ArrayList<Card> cardAL){
		int countLemming = 0;
		int countOther = 0;
		for(int x = 0;  x<31; x++){
			if(cardAL.get(x).getOwner() == null){
			
			}else if(cardAL.get(x).getOwner().equals(ClientController.clientOwner) && cardAL.get(x).getStatus().equals(Status.gewertet) && cardAL.get(x).getType().equals(cardType.Lemming.toString())){
			countLemming++;
			cardAL.get(x).getImage().setDisable(false);
			cardAL.get(x).getImage().setOpacity(1);
			}
		}
			
		
		if(countLemming== 0){
			for(int y = 0; y<31; y++){
				if(cardAL.get(y).getOwner() == null){
				}else if(cardAL.get(y).getOwner().equals(ClientController.clientOwner) && cardAL.get(y).getStatus().equals(Status.gewertet) && (cardAL.get(y).getType().equals(cardType.Prof.toString()) || cardAL.get(y).getType().equals(cardType.Rieb.toString()) || cardAL.get(y).getType().equals(cardType.Yeti.toString()) )){
					countOther++;
					cardAL.get(y).getImage().setDisable(false);
					cardAL.get(y).getImage().setOpacity(1);
				}
			}
		}
			

		
		if(countLemming == 0 && countOther == 0){
			return false;
		}else{
			return true;
		}
		
		
		
	}
	
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public boolean checkDeathCardDies(ArrayList<Card> cardAL, ArrayList<Würfel> diesAL, Account acc){
		boolean availableDeathCardDies = false;
		for(int x = 0; x<31; x++){
			if(diesAL.get(0).getCurrentNumberofeyes() == cardAL.get(x).getNumberofeyes() && cardAL.get(x).getType().equals(cardType.Tod.toString()) && cardAL.get(x).getOwner() == null){
				
				availableDeathCardDies = true;
				cardAL.get(x).getImage().setDisable(false);
				cardAL.get(x).getImage().setOpacity(1);
				break;
			
			}else if(diesAL.get(0).getCurrentNumberofeyes() == cardAL.get(x).getNumberofeyes() && cardAL.get(x).getType().equals(cardType.Tod.toString()) && !cardAL.get(x).getOwner().equals(acc)){
				
				availableDeathCardDies = true;
				cardAL.get(x).getImage().setDisable(false);
				cardAL.get(x).getImage().setOpacity(1);
				break;
			
			}
			
		}
		
		if(availableDeathCardDies){
			return true;
		}else{
		return false;
	}
	}
	

	
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public boolean checkAvailableDeathCards(ArrayList<Card> cardAL){
		boolean availableTodCard = false;
		for(int x = 0; x<31; x++){
			if(cardAL.get(x).getType().equals(cardType.Tod.toString()) && cardAL.get(x).getOwner() == null){
				availableTodCard = true;
				cardAL.get(x).getImage().setDisable(false);
				cardAL.get(x).getImage().setOpacity(1);
			}
		}
		return availableTodCard;
	}
	
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public void chooseDeathCard(ArrayList<Card> cardAL, ArrayList<Würfel> wuerfelAL, Account acc){
		countDeath = 0;
		if(checkDeathCardDies(cardAL, wuerfelAL, acc)){
			checkDeathCardDies(cardAL, wuerfelAL, acc);
		}else if(checkAvailableDeathCards(cardAL)){
			checkAvailableDeathCards(cardAL);
			}else{
				for(int x = 0; x<31; x++){
					if(cardAL.get(x).getType().equals(cardType.Tod.toString()) && !cardAL.get(x).getOwner().equals(acc)){
						cardAL.get(x).getImage().setDisable(false);
						cardAL.get(x).getImage().setOpacity(1);
						countDeath++;
					}
					
				}

			}
		
					
	}
	
	
	
	
	
	
	
	

	


}