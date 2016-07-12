package server;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JOptionPane;




public class ServerModel extends Observable {
	
	private Listener listener;
	private static final int portNumber = 8080;
	private boolean isServerRunning = false;
	private String ipAddress;
	private ArrayList<ClientConnection> clientList = new ArrayList<>();
	
	
	
	public void startServer() {
		try {
			listener = new Listener(this, portNumber);
			listener.start();
			isServerRunning = true;
			ipAddress = InetAddress.getLocalHost().getHostAddress();
	
	
		}catch (IOException e) {

		}
		
	}
	
	public void addClient(ClientConnection newClient) {
		synchronized (clientList) {
			clientList.add(newClient);
		}
	}
	
	
	public void broadcast(Object obj) {
		synchronized (clientList) {
			for (ClientConnection client : clientList) {
				client.sendObject(obj);
			}
		}
	}
	
}
	

