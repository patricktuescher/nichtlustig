/**
	 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
	 * All rights reserved.
	 * 
	 * @function 
	 * @author 
	 */

package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener extends Thread {

	private ServerModel model;
	private ServerSocket serverSocket;
	private boolean stopThread = false;

	/**
	 * Constructor
	 * 
	 * @param model
	 * @param port
	 * @throws IOException
	 */
	
	public Listener(ServerModel model, int port) throws IOException {
		super("Listener:" + port);
		this.model = model;
		serverSocket = new ServerSocket(port);
	}

	/**
	 * Closing serverSocket
	 */
	public void stopListening() {
		if (!this.stopThread) {
			this.stopThread = true;
			try {
				serverSocket.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Accepting the sockets from clients and opening a new ClientConnection
	 */
	
	public void run() {
		while (!stopThread) {
			Socket socket;
			try {
				socket = serverSocket.accept();
				ClientConnection newClient = new ClientConnection(model, socket);
				model.addClient(newClient);
				newClient.start();
			}
			catch (IOException e) {
				stopThread = true;
			}
		}
	}

}
