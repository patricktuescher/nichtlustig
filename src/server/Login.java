package server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Login {
	
	
	protected void logCredentials (String credentials){
	
	try{
	File file = new File("Credentials.txt");
	FileWriter fw = new FileWriter(file.getAbsoluteFile());
	BufferedWriter bw = new BufferedWriter(fw);
	bw.write(credentials+" "); 
	
	// nur wenn falls credentials noch nicht in file vorhanden sind
	
	bw.close();
	} 
	
	catch (IOException e){
	e.printStackTrace();
	}
	
}

//http://www.mkyong.com/java/how-to-write-to-file-in-java-bufferedwriter-example/


	protected boolean checkCredentials( boolean b){
	
	Path credentialsFile = Paths.get("server/credentials", "Credentials.txt");

  	Charset charset = Charset.forName("ISO-8859-1");
  	
  	try {
	List<String> lines = Files.readAllLines(credentialsFile, charset);
	
	// ist String Credentials in lines vorhanden?? --> muss überprüft werden
	
  	} 
	    
  	catch (IOException e){
	System.out.println(e);
  	}
		
  	return b;
	}
}
	





