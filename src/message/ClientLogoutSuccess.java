package message;

import java.io.Serializable;

public class ClientLogoutSuccess implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6285999042052320061L;
	private boolean b = true;
	
	public ClientLogoutSuccess(boolean b){
		this.b = b;
	}
	public boolean getSuccess(){
		return this.b;
	}

}
