package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import client.Account;
import message.CardClick;
import message.ChatMessage;
import message.ClientLogin;
import message.ClientLoginSuccess;
import message.ClientLogout;
import message.ClientLogoutSuccess;
import message.ClientTurn;
import message.EvaluateFirstPlayer;
import message.GameAvailableMessage;
import message.GameComplete;
import message.NewGameChatMessage;
import message.WürfelRoll;
import message.initiateNewGame;



public class ClientConnection extends Thread {

	private ServerModel model;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Object obj;
	private String msg;
	private Account Player;


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
					this.Player = acc.getAccount();
					boolean b = new ClientLoginChecker().check(acc);
					this.sendObject(new ClientLoginSuccess(b));
					if(b){
						if(model.getGame().isGameAvailabe())
							this.sendObject(new GameAvailableMessage(model.getGame().isGameAvailabe(), model.getOtherClient(this).getClientName()));
						else
							this.sendObject(new GameAvailableMessage(false));
					}
					
					
				
				}
				if(obj instanceof ClientLogout){
					ClientLogout logout = (ClientLogout) obj;
					this.Player = null;
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
					model.sendToOtherClients(new ClientTurn(true), this);
					this.sendObject(yturn);
				}
				if(obj instanceof CardClick){
					CardClick click = (CardClick) obj;
					model.sendToOtherClients(click, this);
				}
				if(obj instanceof initiateNewGame){
					model.getGame().joinPlayer(Player);
					model.sendToOtherClients(new GameAvailableMessage(true, this.getClientName()), this);
				}
				if(obj instanceof GameComplete){
					model.getGame().joinPlayer(Player);
					model.broadcast((GameComplete) obj);
					model.broadcast(new NewGameChatMessage());
				}
				if(obj instanceof EvaluateFirstPlayer){
					EvaluateFirstPlayer efp = (EvaluateFirstPlayer) obj;
					model.getGame().setWürfel(efp.getWürfel(), this.Player);
					if(model.getGame().würfelComplete()){
						Account firstPlayer;
						try {
							firstPlayer = model.getGame().firstPlayer();
							if(this.Player.getAccName().equals(firstPlayer.getAccName())){
								this.sendObject(new ClientTurn(true));
								model.sendToOtherClients(new ClientTurn(false), this);
							}
							else{
								this.sendObject(new ClientTurn(false));
								model.sendToOtherClients(new ClientTurn(true), this);
							}
						} catch (Exception e) {
							// Vorgang neu starten, falls die Augenzahlen gleich gross sind
							model.broadcast(new GameComplete());
						}
						
							
						
				}

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
				if(out != null){
					out.reset();
				}
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
		return this.Player.getAccName();
	}



}
