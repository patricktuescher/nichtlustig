package message;

import java.io.Serializable;

import client.Card;

public class CardGewertet implements Serializable {

	private static final long serialVersionUID = 8284540237795620590L;
	private Card card;
	
	public CardGewertet(Card card){
		this.card = card;
	}
	
	public Card getCard(){
		return card;
	}
}
