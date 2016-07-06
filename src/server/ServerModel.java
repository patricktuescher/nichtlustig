package server;

import javafx.scene.shape.Circle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import javafx.concurrent.Task;

public class ServerModel {
    private Integer port;
    private final Logger logger = Logger.getLogger("");
    
    final Task<Void> serverTask = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            try {
                ServerSocket listener = new ServerSocket(port, 10, null);
                logger.info("Listening on port " + port);

                while (true) {
                    // The "accept" method waits for a request, then creates a socket
                    // connected to the requesting client
                    Socket client = listener.accept();
                    int n;

                    logger.info("Request from client "
                            + client.getInetAddress().toString() + " for server "
                            + client.getLocalAddress().toString());

                    // Create input and output streams to talk to the client
                    BufferedReader inClient = new BufferedReader(new InputStreamReader(client
                            .getInputStream()));
                    PrintWriter outClient = new PrintWriter(client.getOutputStream());

                    // Send our reply using HTTP 1.0 - we could also use the "write" method
                    outClient.print("HTTP/1.0 200 \n"); // Version and status
                    outClient.print("Content-Type: text/plain\n");
                    outClient.print("\n");

                    // Read request from client and send it straight back
                    // An empty string (length 0) is the end of an HTTP request
                    StringBuilder received = new StringBuilder();
                    String inString;
                    while ((inString = inClient.readLine()) != null && inString.length() != 0) {
                        received.append(inString + "\n");
                    }
                    String outString = received.toString() + "hallo";
                    
                    outClient.print(outString);
                    logger.info("Request contents:\n" + outString);
                    
                    outClient.flush(); // Be safe, always "flush"
                    outClient.close();
                    inClient.close();
                    client.close();
                }
            } catch (Exception e) {
                System.err.println(e);
            }
            return null;
        }
    };    
    
    /**
     * Called by the controller, to start the task, to serve web content
     */
    public void serveContent(Integer port) {
        this.port = port;
        new Thread(serverTask).start();
    }
}

