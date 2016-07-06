package client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientModel {


	public ClientModel(){
			
	}
	
	public String browse(String ipAddress, Integer port, Integer n) {
        Socket s = null;
        ObjectOutputStream out = null;
        BufferedReader inReader = null;
        String lineIn;
        StringBuffer urlContent = new StringBuffer();

        // Network errors are always possible
        try {
            // Set up the socket
            s = new Socket(ipAddress, port);

            // Send our request, using the HTTP 1.0 protocol
            out = new ObjectOutputStream(s.getOutputStream());
            out.writeObject(n);
            out.flush();

            // Set up the reader classes
            //ObjectInputStream erlaubt das Einlesen von binär serialisierten Objekten:
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
          
            //Hier wird ein String-Objekt erwartet:  
            Integer f = (Integer) in.readObject();
            
            return f.toString();
        }

        // If an error occurred, show the error message in txtInhalt
        catch (Exception err) {
            return "ERROR: " + err.toString();
        } finally {
            try {
                if (out != null)
                    out.close();
                if (inReader != null)
                    inReader.close();
                if (s != null)
                    s.close();
            } catch (Exception e) {
            }
        }
    }



}