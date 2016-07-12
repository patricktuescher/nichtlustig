package server;

import javafx.scene.shape.Circle;

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

import javafx.concurrent.Task;

public class ServerModel {
	
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
			if()
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

