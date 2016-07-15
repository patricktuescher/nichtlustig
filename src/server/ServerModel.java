package server;

import javafx.scene.shape.Circle;
import message.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import client.Account;
import javafx.concurrent.Task;

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
			ipAddress = InetAddress.getLocalHost().getHostAddress();
	
	
		}catch (IOException e) {

		}
		
	}
	
	public void addClient(ClientConnection newClient) {
		synchronized (clientList) {
			if(clientList.size() < 2){
			clientList.add(newClient);
			}
			else
				newClient.closeSocket();
				newClient.sendObject(new ClientLoginSuccess(false));
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
				if(client == current){
					System.out.println(client.getClientName());
					return client;
				}
			}
		}
		//shouldn't return this
		return current;
	}
	/**
	 * @return returns if the account is the first one to log on to the game
	 */
	public boolean setGame(Account acc){
		if(this.game == null){
			this.game = new Game(acc);
			return true;
		}
		else{
			this.game.joinPlayer(acc);
			return false;
		}
	}
	public ArrayList getListeners(){
		return this.clientList;
	}
}

