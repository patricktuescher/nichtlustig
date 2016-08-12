package message;

import java.io.Serializable;

import client.Card;

/**
 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
 * All rights reserved.
 * 
 */
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
