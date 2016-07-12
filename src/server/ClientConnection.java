package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;



public class ClientConnection extends Thread {

	private ServerModel model;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Object obj;
	private String msg;


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
				System.out.println("Objekt wurde auf dem Server empfangen");
				model.broadcast(obj);
				System.out.println("Objekt wurde zurück an den Client geschickt");

				
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



}