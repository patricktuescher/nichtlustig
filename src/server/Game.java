package server;

import java.util.ArrayList;

import client.Account;
import client.Würfel;

public class Game {
	Account PL1;
	Account PL2;
	ArrayList<Würfel> würfelPL1;
	ArrayList<Würfel> würfelPL2;
	
	
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
	
	public void setWürfel(ArrayList<Würfel> würfel){
		if(this.würfelPL1 == null){
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
	public Account firstPlayer(){
		int PunktePL1 = 0;
		int PunktePL2 = 0;
		
		for(int x = 0; x < würfelPL1.size();x++){
			PunktePL1 += würfelPL1.get(x).getAktAugenzahl();
			PunktePL2 += würfelPL2.get(x).getAktAugenzahl();
		}
		if(PunktePL1 > PunktePL2)
			return this.PL1;
		else
			return this.PL2;
	}

}
