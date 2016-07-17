package client;

import java.io.Serializable;

public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 821929046461650238L;
	private String accName;
	private String accPassword;
	
	public Account(String accName){
		this.accName = accName;
	}
	public String getAccName(){
		return this.accName;
	}
	public boolean equals(Account otherAccount){
		if(accName.equals(otherAccount.getAccName()))
			return true;
		else return false;
	}

}
