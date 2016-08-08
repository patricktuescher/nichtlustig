/**
	 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
	 * All rights reserved.
	 * 
	 * @function This class represents an object, which checks new accounts and writes into the database
	 * @author Nicola Burri
	 */

package server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Logger;
import client.Account;
import message.ClientLogin;


public class AccountRegister {
	private Account acc;
	protected Logger logger = ServiceLocator.getServiceLocator().getLogger();
	
	/**
	 * Checks, if there is already an account registred with the same name
	 * @param acc Account
	 * @return if already exists == true; if doesn't exist == false
	 */
	
	public boolean check(Account acc){
		this.acc = acc;
		boolean b = true;
		ArrayList <Account>accList = ClientLoginChecker.getAccountList();
		if(acc.getAccName().length() < 3 || acc.getPassword().length() < 3){
			return false;
		}
		for(int x = 0; x < accList.size(); x++){
			if(accList.get(x).getAccName().equals(acc.getAccName())){
				b = false;
				break;
			}
		}
		
		return b;
	}
	public void writeNewAccount(Account acc){
		this.acc = acc;
//		if(check()){
			try {
				PrintWriter pw = new PrintWriter(new FileOutputStream(new File("src/server/AccountDB.txt"),true));
				pw.print("," + acc.getAccName() + "," + acc.getPassword());
				logger.info("New account \"" + acc.getAccName()+"\" has been added to the database");
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//	}
	
}
