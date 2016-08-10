package message;

import java.io.Serializable;

import client.Card;

public class CardValued implements Serializable {

	private static final long serialVersionUID = 8284540237795620590L;
	private Card card;
	
	public CardValued(Card card){
		this.card = card;
	}
	
	public Card getCard(){
		return card;
	}
}
