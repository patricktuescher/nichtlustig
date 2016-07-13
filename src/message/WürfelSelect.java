package message;

import java.io.Serializable;
import java.util.ArrayList;

import client.Würfel;

public class WürfelSelect implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8993725853828317933L;
	private ArrayList<Würfel> würfel;
	
	public WürfelSelect(ArrayList<Würfel> würfel){
		this.würfel = würfel;
	}
	
	public ArrayList<Würfel> getWürfel(){
		return this.würfel;
	}

}
