/**
	 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
	 * All rights reserved.
	 * 
	 * @function This class represents a Game with two Accounts
	 * @author Nicola Burri
	 */

package server;

import java.util.ArrayList;
import java.util.logging.Logger;
import client.Account;
import client.ServiceLocator;
import client.Würfel;

public class Game {
	Account PL1;
	Account PL2;
	ArrayList<Würfel> würfelPL1;
	ArrayList<Würfel> würfelPL2;
	int AugenzahlPunktePL1 = 0;
	int AugenzahlPunktePL2 = 0;
	boolean PL1finished = false;
	boolean PL2finished = false;
	Logger logger = ServiceLocator.getServiceLocator().getLogger();
	
	public Game(){
		
	}
	/**
	 * 
	 * @param player which joins the game. Only two players can be added to the game.
	 */
	
	public void joinPlayer(Account player){
		if(this.PL1 == null){
			this.PL1 = player;
			logger.info("First player logs in to the game. Name: " + player.getAccName());
		}
		else if(this.PL2 == null){
			this.PL2 = player;
			logger.info("Second player logs in to the game. Name: " + player.getAccName());
		}
	}
	/**
	 * 
	 * @return if two players have joined the game
	 */
	
	public boolean isGameAvailabe(){
		if(this.PL1 != null && this.PL2 == null){
			return true;
		}
		else return false;
	}
	
	/** 
	 * 
	 * @param würfel die of the player
	 * @param Player corresponding account
	 */
	
	public void setWürfel(ArrayList<Würfel> würfel, Account Player){
		if(this.PL1.getAccName().equals(Player.getAccName())){
			this.würfelPL1 = würfel;
		}
		else this.würfelPL2 = würfel;
	}
	public boolean würfelComplete(){
		if(würfelPL1 != null && würfelPL2 != null){
			return true;
		}
		else return false;
	}
	
	/**
	 * 
	 * @return the player, which has the higher amount of numbers
	 * @throws Exception if the numbers are equal
	 */
	
	public Account firstPlayer() throws Exception{
		AugenzahlPunktePL1 = 0;
		AugenzahlPunktePL2 = 0;
		
		for(int x = 0; x < würfelPL1.size();x++){
			AugenzahlPunktePL1 += würfelPL1.get(x).getAktAugenzahl();
			AugenzahlPunktePL2 += würfelPL2.get(x).getAktAugenzahl();
		}
		if(AugenzahlPunktePL1 == AugenzahlPunktePL2){
			throw new Exception();
		}
		if(AugenzahlPunktePL1 > AugenzahlPunktePL2)
			return this.PL1;
		else
			return this.PL2;
	}
	public void setGameFinished(Account acc){
		if(PL1.equals(acc))
			PL1finished = true;
		if(PL2.equals(acc))
			PL2finished = true;
	}
	public boolean bothPlayersFinished(){
		if(PL1finished && PL2finished)
			return true;
		else return false;
	}

}
