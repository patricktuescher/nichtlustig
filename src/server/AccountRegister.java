package server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import client.Account;
import message.ClientLogin;

/**
 * This class represents an object, which checks new accounts and writes into the database
 * @author Nicola Burri
 *
 */

public class AccountRegister {
	Account acc;
	
	/**
	 * Checks, if there is already an account registred with the same credentials
	 * @param acc Account
	 * @return if already exists == true; if doesn't exist == false
	 */
	
	private boolean check(){
		if(ClientLoginChecker.check(new ClientLogin(acc)))
				return false;
		return true;	
	}
	public void writeNewAccount(Account acc){
		this.acc = acc;
		System.out.println("hier 1");
		if(check()){
			try {
				PrintWriter pw = new PrintWriter(new FileOutputStream(new File("src/server/AccountDB.txt"),true));
				pw.println("\n" + acc.getAccName());
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	

}
