/**
	 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
	 * All rights reserved.
	 * 
	 * @function 
	 * @author 
	 */

package message;

import java.io.Serializable;

public class ClientTurn implements Serializable { 
	
	private static final long serialVersionUID = 4414195638483571650L;
		
		private Boolean yturn;
		
		public ClientTurn(Boolean yturn){
		this.yturn = yturn;
		}
		
		public Boolean getTurn(){
			return yturn;
		}

	}



