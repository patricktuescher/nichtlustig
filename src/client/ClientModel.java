package client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientModel {


	public ClientModel(){
			
	}
	
	public String browse(String ipAddress, Integer port, int n) {
        Socket s = null;
        OutputStreamWriter out = null;
        BufferedReader inReader = null;
        String lineIn;
        StringBuffer urlContent = new StringBuffer();

        // Network errors are always possible
        try {
            // Set up the socket
            s = new Socket(ipAddress, port);

            // Send our request, using the HTTP 1.0 protocol
            out = new OutputStreamWriter(s.getOutputStream());
            out.write(n);
            out.flush();

            // Set up the reader classes
            InputStream in1 = s.getInputStream();
            InputStreamReader in2 = new InputStreamReader(in1);
            inReader = new BufferedReader(in2);

            while ((lineIn = inReader.readLine()) != null) {
                urlContent.append(lineIn + "\n");
            }

            return urlContent.toString();
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