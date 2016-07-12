package message;

import java.io.Serializable;

import client.Account;

public class ClientLogin implements Serializable {
	
	Account acc;

	/**
	 * 
	 */
	private static final long serialVersionUID = 8835177916571805574L;
	
	public ClientLogin(Account acc){
		this.acc = acc;
	}
	
	public Account getAccount(){
		return this.acc;
	}

}
