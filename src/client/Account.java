package client;

import java.io.Serializable;

public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 821929046461650238L;
	private String accName;
	private String accPassword;
	
	public Account(String accName, String accPassword){
		this.accName = accName;
		this.accPassword = accPassword;
	}
	public String getAccName(){
		return this.accName;
	}
	public String getPassword(){
		return this.accPassword;
	}
	
	public boolean equals(Object obj){
			Account otherAccount = (Account) obj;
			if(accName.equals(otherAccount.getAccName()) && accPassword.equals(otherAccount.getPassword()))
				return true;
			else return false;
		}
	public String toString(){
		return "Account: " + this.accName;
	}

}
