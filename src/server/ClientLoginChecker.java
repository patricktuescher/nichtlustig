package server;

import message.ClientLogin;

public class ClientLoginChecker {
	
	public ClientLoginChecker(){
	}
	
	public static boolean check(ClientLogin clientLogin){
		String[] logins = {"Burri", "Patrick", "Kevin", "Marco"};
		for(String s: logins){
			if(clientLogin.getAccount().getAccName().equals(s)){
				return true;
			}
		}
		
		return false;
	}

}
