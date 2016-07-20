package client;

import java.util.ArrayList;



public class cardChecker {
	
	ClientController controller;
	
	public cardChecker(){
		
	}
	
	public void cardCheckforDisable(ArrayList<Card> cardAL, ArrayList<Würfel> wuerfelAL){
		ArrayList<Card> allCards = cardAL;
		ArrayList<Würfel> aktuelleWuerfel = wuerfelAL;
		
		for(int x = 0;x<31;x++){
		
			if(!allCards.get(x).check(aktuelleWuerfel)){
				allCards.get(x).getImage().setRotate(0);
			}else{
			allCards.get(x).getImage().setRotate(90);
		}
		}
		
				
	}
	
	
	
	
	

}
