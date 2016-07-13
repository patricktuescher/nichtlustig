package message;

import java.io.Serializable;

public class ClientTurn implements Serializable { 
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 11111111;
		private Boolean yturn;
		
		public ClientTurn(Boolean yturn){
		this.yturn = yturn;
		}
		
		public Boolean getTurn(){
			return yturn;
		}

		}



