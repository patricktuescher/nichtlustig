package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import message.HighscoreUpdate;

public class Highscore {
	private BufferedReader br;
	
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

}
