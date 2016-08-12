package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;

import client.Account;
import message.HighscoreUpdate;

/**
 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
 * All rights reserved.
 * 
 * @function This class handles Highscore functions
 * @author Nicola Burri
 */

public class Highscore {

	private BufferedReader br;
	private static Logger logger = ServiceLocator.getServiceLocator().getLogger();
	
/**
 * 
 * @return HighscoreUpdate (ArrayLists) for the Highscore table
 */
	
	public static HighscoreUpdate getHighscoreUpdate(){
		   BufferedReader br = null;
		   ArrayList <String> nameValues = new ArrayList();
		   ArrayList<String> scoreValues = new ArrayList();
		   ArrayList<String> dateValues = new ArrayList();
		    try {
		        br = new BufferedReader(new FileReader(new File("src/server/Highscore.txt")));
				   
		      
		        String line = null;
		        
		        while((line = br.readLine()) != null) {
		        	
		            String[] parts = line.split(",");
		            nameValues.add(parts[0]);
		            scoreValues.add(parts[1]);
		            dateValues.add(parts[2]);
		     
		           
		     
		        }
		       
		        

		        
		    } catch(FileNotFoundException e) {
		        e.printStackTrace();
		    } catch(IOException e) {
		        e.printStackTrace();
		    } finally {
		        if(br != null) {
		            try {
		                br.close();
		            } catch(IOException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		    
           return new HighscoreUpdate(scoreValues, nameValues, dateValues);
           
	}
	/**
	 * this method writes a new score into the database
	 * @param score new Score
	 * @param player Player
	 */
	public static synchronized void writeScore(int score, Account player){
		HighscoreUpdate hu = getHighscoreUpdate();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String today = df.format(new Date());
		if(!hu.getNameValues().contains(player.getAccName())){
			write(player.getAccName(), score+"", today);
		}
		else{
			int rowIndex = hu.getNameValues().indexOf(player.getAccName());
			if(Integer.parseInt(hu.getScoreValues().get(rowIndex)) < score){
				deleteLine(player.getAccName());
				write(player.getAccName(), score+"", today);
			}
		}
	}
	private static void deleteLine(String accName) {
		File file = new File("src/server/Highscore.txt");
		Scanner scan;
		try {
			scan = new Scanner(file);
			String newOutput = "";
			while(scan.hasNextLine() && scan.hasNext()){
				String input = scan.next();
				
				System.out.println(input);
				if(input.contains(accName)){
					System.out.println("hier cedi");
					continue;
				}
				else
					newOutput = newOutput + "\n" + input;
			}
			newOutput = newOutput.replaceFirst("\\s", "");
			System.out.println("new Output: "+ newOutput);
			writeNew(newOutput);;
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static void write(String name, String score, String date){
			try {
				PrintWriter pw = new PrintWriter(new FileOutputStream(new File("src/server/Highscore.txt"),true));
				pw.println(name + "," + score + "," + date);
				logger.info("New highscore from: " + name + ". Score: " + score);
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	private static void writeNew(String s){
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(new File("src/server/Highscore.txt"),false));
			pw.println(s);
			pw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
