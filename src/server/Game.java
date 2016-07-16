package server;

import client.Account;

public class Game {
	Account PL1;
	Account PL2;
	
	public Game(Account player){
		
			this.PL1 = player;
	}
	
	public void joinPlayer(Account player){
		this.PL2 = player;
	}
	
	public boolean isGameAvailabe(){
		if(this.PL1 != null && this.PL2 == null){
			return true;
		}
		else return false;
	}

}
