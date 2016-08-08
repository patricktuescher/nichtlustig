/**
	 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
	 * All rights reserved.
	 * 
	 * @function 
	 * @author 
	 */

package client;

import java.util.ArrayList;

import message.CardGewertet;

public class ClientModel {
	
	private int playerRollCounter = 0;
	protected int cardCounter;
	protected ServerListener server;
	
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
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
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
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public void aktivateCards(ArrayList<Card> cardAL, ArrayList<Würfel> wuerfelAL, Account acc){
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
		
		if(cardCounter == 0){
	
			chooseTodCard(cardAL, wuerfelAL, acc);
			
		};
	}
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public void aktivateGewerteteCards(ArrayList<Card> cardAL, cardType cType){
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
	public boolean checkCardsToChooseTod(ArrayList<Card> cardAL){
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
	public boolean checkTodCardWürfel(ArrayList<Card> cardAL, ArrayList<Würfel> wuerfelAL, Account acc){
		boolean availableTodCardWürfel = false;
		for(int x = 0; x<31; x++){
			if(wuerfelAL.get(0).getAktAugenzahl() == cardAL.get(x).getAugenzahl() && cardAL.get(x).getType().equals(cardType.Tod.toString()) && cardAL.get(x).getOwner() == null){
				
				availableTodCardWürfel = true;
				cardAL.get(x).getImage().setDisable(false);
				cardAL.get(x).getImage().setOpacity(1);
				break;
			
			}else if(wuerfelAL.get(0).getAktAugenzahl() == cardAL.get(x).getAugenzahl() && cardAL.get(x).getType().equals(cardType.Tod.toString()) && !cardAL.get(x).getOwner().equals(acc)){
				
				availableTodCardWürfel = true;
				cardAL.get(x).getImage().setDisable(false);
				cardAL.get(x).getImage().setOpacity(1);
				break;
			
			}
			
		}
		
		if(availableTodCardWürfel){
			return true;
		}else{
		return false;
	}
	}
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public void removeCardTod(ArrayList<Card> cardAL, Card cardTod){
		for(int x = 0; x<31; x++){
			if(cardAL.get(x).getcardTod() == null){
				
			}else if(cardAL.get(x).getcardTod().getAugenzahl() == cardTod.getAugenzahl()){
				cardAL.get(x).setcardTod(null);
				cardAL.get(x).setStatus(Status.gewertet);
				cardAL.get(x).getImage();
				server.sendObject(new CardGewertet(cardAL.get(x)));
			}
		}
	}
	
	
	/**
	 * @author Patrick Tüscher
	 * 
	 */
	public boolean checkAvailableTodCards(ArrayList<Card> cardAL){
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
	public void chooseTodCard(ArrayList<Card> cardAL, ArrayList<Würfel> wuerfelAL, Account acc){
		if(checkTodCardWürfel(cardAL, wuerfelAL, acc)){
			checkTodCardWürfel(cardAL, wuerfelAL, acc);
		}else if(checkAvailableTodCards(cardAL)){
			checkAvailableTodCards(cardAL);
			}else{
				for(int x = 0; x<31; x++){
					if(cardAL.get(x).getType().equals(cardType.Tod.toString())){
						cardAL.get(x).getImage().setDisable(false);
						cardAL.get(x).getImage().setOpacity(1);
					}
					
				}
			}
			
	}
	
	
	
	
	
	
	
	

	


}