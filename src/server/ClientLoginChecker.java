package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import client.Account;
import message.ClientLogin;

public class ClientLoginChecker {
	protected static ArrayList <Account> accountList = new ArrayList<Account>();
		
	public ClientLoginChecker(){
	}
	
	public static boolean check(ClientLogin clientLogin){
		File file = new File("src/server/AccountDB.txt");
		Scanner scan;
		try {
			scan = new Scanner(file);
			scan.useDelimiter(",");
			while(scan.hasNext()){
				Account acc = new Account(scan.next(), scan.next());
				System.out.println(acc);
				accountList.add(acc);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(accountList.contains(clientLogin.getAccount())){
			return true;
		}
		return false;
	
		
		
		
	}
	public static ArrayList<Account> getAccountList(){
		check(new ClientLogin(new Account("","")));
		return accountList;
	}

}
