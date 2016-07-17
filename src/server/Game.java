package server;

import java.util.ArrayList;

import client.Account;
import client.Würfel;

public class Game {
	Account PL1;
	Account PL2;
	ArrayList<Würfel> würfelPL1;
	ArrayList<Würfel> würfelPL2;
	int AugenzahlPunktePL1 = 0;
	int AugenzahlPunktePL2 = 0;
	
	
	public Game(){
		
	}
	
	public void joinPlayer(Account player){
		if(this.PL1 == null){
			this.PL1 = player;
			System.out.println("erster Spieler = " + player.getAccName());
		}
		else if(this.PL2 == null){
			this.PL2 = player;
			System.out.println("zweiter Spieler = " + player.getAccName());
		}
	}
	
	public boolean isGameAvailabe(){
		if(this.PL1 != null && this.PL2 == null){
			return true;
		}
		else return false;
	}
	
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

}
