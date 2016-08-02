package message;

import java.io.Serializable;

public class Registration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3905115075548104682L;
	private boolean check;
	
	
	public Registration(boolean check){
		this.check = check;
	}
	
	public boolean getCheck(){
		return check;
	}

	
	
}
