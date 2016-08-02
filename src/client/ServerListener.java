/**
	 * Copyright 2016, University of Applied Sciences and Arts Northwestern Switzerland (FHNW), Manipake Kuntroburtüs.
	 * All rights reserved.
	 * 
	 * @function This class acts as an interface to the server application and monitors incoming data.
	 * @author 
	 */

package client;

import java.io.IOException;
import tools.Translator;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import javafx.application.Platform;
import message.CardClick;
import message.CardGewertet;
import message.ChatMessage;
import message.ClientLogin;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import message.ClientLoginSuccess;
import message.ClientLogoutSuccess;
import message.ClientTurn;
import message.EvaluateFirstPlayer;
import message.GameAvailableMessage;
import message.GameComplete;
import message.GameFinished;
import message.GameQuit;
import message.NewGameChatMessage;
import message.PointUpdate;
import message.Registration;
import message.WürfelRoll;

public class ServerListener extends Thread {
	protected ServiceLocator sl = ServiceLocator.getServiceLocator();
	protected Translator t = sl.getTranslator();
	private static final int portNumber = 55555;

	private static String hostAddress; 
	private static Socket socket;
	private Logger logger = sl.getLogger();

	public static ServerListener serverListener;

	public static ClientView view;
	public static ClientController controller;
	
	private ObjectInputStream in = null;
	private ObjectOutputStream out = null;
	private Object obj = null;
	private boolean stopThread = false;
	
	
	public static ServerListener getServerListener() {
		if (serverListener == null) {
			serverListener = new ServerListener();
		}
		return serverListener;
	}

	public boolean connect(Account acc) {
		
		try {

			if (socket == null) {
				socket = new Socket(hostAddress, portNumber);

				if (out == null) {
					out = new ObjectOutputStream(socket.getOutputStream());
					this.sendObject(new ClientLogin(acc));
				}
				
				this.start();
				
			}else{
				this.sendObject(new ClientLogin(acc));
			}
			
		}
		catch (IOException e) {

			return false;
		}
		return true;
	}
	
public boolean connect() {
		
		try {

			if (socket == null) {
				socket = new Socket(hostAddress, portNumber);

				if (out == null) {
					out = new ObjectOutputStream(socket.getOutputStream());
				}
				
				this.start();
				
			}else{
				
			}
			
		}
		catch (IOException e) {

			return false;
		}
		return true;

	}

	/**
	 * Disconnect and close socket
	 */
	public void disconnect() {
		try {
			socket.close();
			stopThread = true;
			this.interrupt();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This methods handles the ObjectInputStream, reads the incoming serialized objects,
	 * deserializes them and processed them further in the client. If a new message type is
	 * introduced it must be handled here on the client side and also in ClientConnection on the
	 * server side!
	 */
	public void run() {
		
		try {
			if (in == null) {
				in = new ObjectInputStream(socket.getInputStream());
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		while (!stopThread) {
			{
				try {
					obj = in.readObject();
					if(obj instanceof ClientLoginSuccess){
						ClientLoginSuccess cls = (ClientLoginSuccess) obj;
						if(cls.getSuccess()){
							//Without this, there will be a thread conflict
							Platform.runLater(new Runnable() {
				
								@Override
								   public void run() {
									  controller.setLobbyScene();
								   }
								});
							logger.info("Login successful");
						}
						else{
							logger.info("Login unsuccessful");
							Platform.runLater(new Runnable() {
								   @Override
								   public void run() {
									controller.getAlert();
									
							
										
										
								   }
								});
						}
					}
					if(obj instanceof ClientLogoutSuccess){
						ClientLogoutSuccess cls = (ClientLogoutSuccess) obj;
						if(cls.getSuccess())
						//Without this, there will be a thread conflict
						Platform.runLater(new Runnable() {
							   @Override
							   public void run() {
								  controller.setLoginScene();
							   }
							});
						logger.info("Logout successful");
						
					}
					if(obj instanceof NewGameChatMessage){
						controller.addNewMessage("<< " +t.getString("Text.Gamestart") + " >>");
					}
				
					if(obj instanceof ChatMessage){
						ChatMessage msg = (ChatMessage) obj;

						controller.addNewMessage(msg.getMessage());
						
						
					}
		
					
					if(obj instanceof WürfelRoll){
						WürfelRoll ws = (WürfelRoll) obj;
						ArrayList<Würfel> würfel = ws.getWürfel();
						Platform.runLater(new Runnable() {
							   @Override
							   public void run() {
								   
								   controller.setOpponentDi(würfel);
								   
							   }
							});
						
					}
					
					if(obj instanceof ClientTurn){
						ClientTurn yturn = (ClientTurn) obj;
						System.out.println(yturn.getTurn());
						if (yturn.getTurn()){
							new Thread(new Runnable(){

								@Override
								public void run() {
									// TODO Auto-generated method stub
									controller.initiateTurn();
								}
								
							}).start();
							
						}
						else{
							controller.setOpClientTurn();
						
						}	
					}
						
						
	
					
					if(obj instanceof CardClick){
						CardClick click = (CardClick) obj;
						Platform.runLater(new Runnable() {
							   @Override
							   public void run() {
								   controller.opponentSelectCard(click.getCard());
							   }
							});
						
					}
					if(obj instanceof GameAvailableMessage){
						GameAvailableMessage msg = (GameAvailableMessage) obj;
						Platform.runLater(new Runnable() {
							   @Override
							   public void run() {
								   controller.setGameAvImageOnOff(msg.getAvailibility());
								   if(msg.getAvailibility())
									   controller.setAvailableLabel(msg.getOtherConnectionName());
							   }
							});
						
					}
					if(obj instanceof GameComplete){
						controller.setUpGame();
						this.sendObject(new EvaluateFirstPlayer(controller.getWürfel()));
					}
					
					if(obj instanceof PointUpdate){
						PointUpdate points = (PointUpdate) obj;
						Platform.runLater(new Runnable(){
							@Override
							public void run(){
								controller.updatePunkteFromOtherClient(points.getPoints1(), points.getPoints2());
							}
						});
					}
					
					if(obj instanceof GameFinished){
						Platform.runLater(new Runnable(){
							@Override
							public void run(){
								controller.checkWinner();
								controller.setUpGame();
							}
						});
					}
					
					if(obj instanceof GameQuit){
						Platform.runLater(new Runnable(){
							@Override
							public void run(){
								controller.checkWinner();
								controller.setUpGame();
							}
						});
					}
					
					if(obj instanceof CardGewertet){
						CardGewertet gewertet = (CardGewertet) obj;
						Platform.runLater(new Runnable(){
							@Override
							public void run(){
								controller.opponentWertetCard(gewertet.getCard());
							}
						});
					}
					
					if(obj instanceof Registration){
						Registration check = (Registration) obj;
						if(check.getCheck()){
							Platform.runLater(new Runnable(){
								@Override
								public void run(){
									controller.getRegAlert();
								}
							});
						}else{
						
						Platform.runLater(new Runnable(){
							@Override
							public void run(){
								controller.getRegFailedAlert();
							}
						});
						}
					}
					
				}

				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				catch (IOException e) {
					// error when server is not there: close the client because living without a server is useless 
					stopThread = true;
					System.exit(0);
				}
			}
		}
	}
	
	
	

	/**
	 * Writes any object to the ObjectOutputStream
	 * 
	 * @param obj
	 */
	public void sendObject(Object obj) {
		try {
			out.writeObject(obj);
			out.reset();
			out.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param hostAddress
	 */
	public static void setHostAddress(String hostAddress) {
		ServerListener.hostAddress = hostAddress;
	}

}
