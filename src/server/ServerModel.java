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
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import javafx.concurrent.Task;

public class ServerModel {
	
	Server verbindung;
		  
	
	public void startServer(int port){
		this.verbindung = new Server(port);
		this.verbindung.start();
	}
	public void stopServer(){
		this.verbindung.shutDown();
	}
}

