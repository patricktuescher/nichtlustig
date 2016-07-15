package message;

import java.io.Serializable;

public class GameAvailableMessage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7179519019155023027L;
	public boolean available;
	
	public GameAvailableMessage(boolean availabe){
		this.available = available;
	}
	public boolean getAvailibility(){
		return this.available;
	}

}
