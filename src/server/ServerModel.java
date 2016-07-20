package server;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import message.ClientLoginSuccess;

public class ServerModel {
	
	private Listener listener;
	private static final int portNumber = 8080;
	private boolean isServerRunning = false;
	private String ipAddress;
	private ArrayList<ClientConnection> clientList = new ArrayList<>();
	private Game game;
	
	
	
	public void startServer() {
		try {
			listener = new Listener(this, portNumber);
			listener.start();
			isServerRunning = true;
			ipAddress = "127.0.0.1";
//			ipAddress = InetAddress.getLocalHost().getHostAddress();
	
	
		}catch (IOException e) {

		}
		
	}
	
	public void addClient(ClientConnection newClient) {
		synchronized (clientList) {
			if(clientList.size() < 2){
			clientList.add(newClient);
			}
			else{
				newClient.closeSocket();
				newClient.sendObject(new ClientLoginSuccess(false));
			}
		}
	}
	
	
	public void broadcast(Object obj) {
		synchronized (clientList) {
			for (ClientConnection client : clientList) {
				client.sendObject(obj);
			}
		}
	}
	public void sendToOtherClients(Object obj, ClientConnection current){
		synchronized (clientList) {
			for (ClientConnection client : clientList) {
				if(client != current){
					client.sendObject(obj);
				}
			}
		}
	}
	public ClientConnection getOtherClient(ClientConnection current){
		synchronized (clientList) {
			for (ClientConnection client : clientList) {
				if(client != current){
					return client;
				}
			}
		}
		//shouldn't return this
		return current;
	}



	public ArrayList getListeners(){
		return this.clientList;
	}
	public Game getGame(){
		if(game == null){
			game = new Game();
		}
		return this.game;
	}
}

