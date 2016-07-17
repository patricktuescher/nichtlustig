package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import message.*;



public class ServerListener extends Thread {

	private static final int portNumber = 8080;

	private static String hostAddress; 
	private static Socket socket;
	private Logger logger = ServiceLocator.getServiceLocator().getLogger();

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
			}
			else{
				this.sendObject(new ClientLogin(acc));
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
										controller.setLoginFailedScene();
									   	Alert alert = new Alert(AlertType.INFORMATION);
										alert.setTitle("Login failure");
										alert.setHeaderText("Login not sucessfull");
										
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
						if (!yturn.getTurn()){
							controller.setOpClientTurn();
						}
						else
							controller.initiateTurn();
						
						
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
