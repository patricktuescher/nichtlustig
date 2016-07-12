package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;



public class ServerListener extends Thread {

	private static final int portNumber = 8080;

	private static String hostAddress; 
	private static Socket socket;

	public static ServerListener serverListener;

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


	public boolean connect() {
		try {

			if (socket == null) {
				socket = new Socket(hostAddress, portNumber);

				if (out == null) {
					out = new ObjectOutputStream(socket.getOutputStream());
				}
				this.start();
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
					System.out.println("Objekt empfangen");

					
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
