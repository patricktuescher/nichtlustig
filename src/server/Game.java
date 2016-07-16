package server;

import client.Account;

public class Game {
	Account PL1;
	Account PL2;
	
	
	public Game(){
		
	}
	
	public void joinPlayer(Account player){
		if(this.PL1 == null){
			this.PL1 = player;
		}
		else if(this.PL2 == null){
			this.PL2 = player;
		}
	}
	
	public boolean isGameAvailabe(){
		if(this.PL1 != null && this.PL2 == null){
			return true;
		}
		else return false;
	}

}
