package client;

import java.util.ArrayList;

import com.sun.media.jfxmedia.logging.Logger;

public class cardChecker {
	
	ClientController controller;
	
	public cardChecker(){
		
	}
	
	public ArrayList <Card> cardCheckforDisable(ArrayList<Card> cardAL, ArrayList<Würfel> wuerfelAL){
		ArrayList<Card> allCards = cardAL;
		ArrayList<Würfel> aktuelleWuerfel = wuerfelAL;
		
		for(int x = 0;x<31;x++){
		
			if(!allCards.get(x).check(aktuelleWuerfel)){
				System.out.println(allCards.get(x) + "geht nicht");
			}else{
			allCards.get(x).getImage().setRotate(90);
			System.out.println(allCards.get(x) + "geht");
		}
		}
		return allCards;
				
	}
	
	
	
	
	

}
