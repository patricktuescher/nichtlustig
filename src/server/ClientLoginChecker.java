package server;

import message.ClientLogin;

public class ClientLoginChecker {
	private static String[] logins = {"Burri", "Patrick", "Kevin", "Marco"};
		
	public ClientLoginChecker(){
	}
	
	public static boolean check(ClientLogin clientLogin){
		
		boolean sucess = true;
		
		
		for(String s: logins){
			if(clientLogin.getAccount().getAccName().equals(s)){
				sucess = true;
			}else{
				sucess = false;
			}
		}
		
		return sucess;
	
		
		
		
	}

}
