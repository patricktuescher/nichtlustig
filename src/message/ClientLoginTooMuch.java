package message;

import java.io.Serializable;

public class ClientLoginTooMuch implements Serializable {
	/**
	 * 
	 */
	
	private boolean success;
	
	public ClientLoginTooMuch(boolean success){
		this.success = success;
	}
	
	public boolean getSuccess(){
		return this.success;
	}

}

