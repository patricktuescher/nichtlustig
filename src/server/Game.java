package server;

import client.Account;

public class Game {
	Account PL1;
	Account PL2;
	
	public Game(Account player){
		if(this.PL1 == null)
			this.PL1 = player;
		else
			this.PL2 = player;
	}
	
	public boolean isGameAvailabe(){
		if(this.PL1 != null){
			return true;
		}
		else return false;
	}

}
