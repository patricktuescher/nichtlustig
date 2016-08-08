package message;

import java.io.Serializable;

import client.Card;

public class CardTod implements Serializable{

	private static final long serialVersionUID = -8670921119168441657L;
	private Card card;
	
	public CardTod(Card card){
		this.card = card;
	}
	
	public Card getCard(){
		return card;
	}
	

}
