package server;

import message.ClientLogin;

public class ClientLoginChecker {
	
	public ClientLoginChecker(){
	}
	
	public static boolean check(ClientLogin clientLogin){
		if(clientLogin.getAccount().getAccName().equals("Burri")){
			return true;
		}
		else return false;
	}

}
