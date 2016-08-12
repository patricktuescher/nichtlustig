/**
	 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
	 * All rights reserved.
	 * 
	 * @function 
	 * @author 
	 */

package message;


import java.io.Serializable;

import client.Account;


public class ClientLogin implements Serializable {
	
	Account acc;

	private static final long serialVersionUID = 8835177916571805574L;
	
	public ClientLogin(Account acc){
		this.acc = acc;
	}
	
	public Account getAccount(){
		return this.acc;
	}

}
