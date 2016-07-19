package message;

import java.io.Serializable;

public class ClientTurn implements Serializable { 
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 4414195638483571650L;
		/**
		 * 
		 */
	
		private Boolean yturn;
		
		public ClientTurn(Boolean yturn){
		this.yturn = yturn;
		}
		
		public Boolean getTurn(){
			return yturn;
		}

		}



