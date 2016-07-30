/**
	 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
	 * All rights reserved.
	 * 
	 * @function 
	 * @author 
	 */

package message;

import java.io.Serializable;

import client.Card;

public class CardClick implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9088399730588893988L;
	private Card card;
	
	public CardClick(Card card){
		this.card = card;
	}
	
	public Card getCard(){
		return card;
	}

}
