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
	ArrayList<Würfel> diesPL1;
	ArrayList<Würfel> diesPL2;
	int numberofeyesPointsPL1 = 0;
	int numberofeyesPointsPL2 = 0;
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
	
	public void setDies(ArrayList<Würfel> würfel, Account Player){
		if(this.PL1.getAccName().equals(Player.getAccName())){
			this.diesPL1 = würfel;
		}
		else this.diesPL2 = würfel;
	}
	public boolean diesComplete(){
		if(diesPL1 != null && diesPL2 != null){
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
		numberofeyesPointsPL1 = 0;
		numberofeyesPointsPL2 = 0;
		
		for(int x = 0; x < diesPL1.size();x++){
			numberofeyesPointsPL1 += diesPL1.get(x).getCurrentNumberofeyes();
			numberofeyesPointsPL2 += diesPL2.get(x).getCurrentNumberofeyes();
		}
		if(numberofeyesPointsPL1 == numberofeyesPointsPL2){
			throw new Exception();
		}
		if(numberofeyesPointsPL1 > numberofeyesPointsPL2)
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
