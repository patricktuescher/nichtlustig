package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import client.Account;
import message.*;



public class ClientConnection extends Thread {

	private ServerModel model;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Object obj;
	private String msg;
	private String ClientConnectionName;


	public ClientConnection(ServerModel model, Socket socket) {
		super();
		this.model = model;
		this.socket = socket;
		this.in = null;
		try {
			this.out = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void run() {
		try {
			if (in == null) {
				in = new ObjectInputStream(socket.getInputStream());
			}
			while (true) {
				obj = in.readObject();
				if(obj instanceof ClientLogin){
					ClientLogin acc = (ClientLogin) obj;
					this.ClientConnectionName = acc.getAccount().getAccName();
					this.sendObject(new ClientLoginSuccess(new ClientLoginChecker().check(acc)));
				}
				if(obj instanceof ClientLogout){
					ClientLogout logout = (ClientLogout) obj;
					this.ClientConnectionName = null;
					this.sendObject(new ClientLogoutSuccess(true));
				}
				if(obj instanceof ChatMessage){
					ChatMessage msg = (ChatMessage) obj;
					String s = this.getClientName()+ ": ";
					s = s + msg.getMessage();
					model.broadcast(new ChatMessage(s));
				}
				if(obj instanceof WürfelRoll){
					WürfelRoll wf = (WürfelRoll) obj;
					model.sendToOtherClients(wf, this);
				}
				if(obj instanceof ClientTurn){
					ClientTurn yturn = (ClientTurn) obj;
					model.sendToOtherClients(yturn, this);
				}
				if(obj instanceof CardClick){
					CardClick click = (CardClick) obj;
					model.sendToOtherClients(click, this);
				}

				
		}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (EOFException e) {
			// if stream is closed
			closeSocket();

		}
		catch (IOException e) {
			// Do nothing, because here it will always catch an exception when a client logs out.
			// This is expected behaviour because of the open stream, so do not catch
		}
	}

	/**
	 * Writes any object to the ObjectOutputStream
	 * 
	 * @param obj
	 */
	public void sendObject(Object obj) {
		try {
			if (!socket.isClosed()) {
				out.writeObject(obj);
				out.reset();
				out.flush();
			}
		}
		catch (SocketException e) {
			// if stream is closed
			closeSocket();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void closeSocket() {
		try {
			if (!socket.isClosed()) {
				socket.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getClientName(){
		return this.ClientConnectionName;
	}



}
