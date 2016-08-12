package message;

import java.io.Serializable;

import client.Card;

/**
 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
 * All rights reserved.
 * 
 */
public class CardDeath implements Serializable{

	private static final long serialVersionUID = -8670921119168441657L;
	private Card card;
	
	public CardDeath(Card card){
		this.card = card;
	}
	
	public Card getCard(){
		return card;
	}
	

}
