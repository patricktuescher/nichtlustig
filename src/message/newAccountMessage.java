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

/**
 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
 * All rights reserved.
 * 
 */

public class newAccountMessage implements Serializable{
	
	private static final long serialVersionUID = 5104845804236928955L;
	public Account account;
	
	public newAccountMessage(Account acc){
		this.account = acc;
	}
	
	public Account getAccount(){
		return this.account;
	}

}
