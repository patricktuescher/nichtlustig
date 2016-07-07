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
		  static private int delim = 13; // CR, LF, ETX...
		  static private final Collection<Servant> servants = new ArrayList<Servant>();
		  // TODO servants nicht synced => ConcurrentModificationException

		  static private final class Servant implements Runnable
		  {
		    private final Socket so;
		    private ObjectOutputStream os;
		    private ObjectInputStream is;
		    private volatile int running;
		    private int Zahl;

		    private Servant(Socket so) throws IOException {
		      this.so = so;
		      os = new ObjectOutputStream(this.so.getOutputStream());
		      is = new ObjectInputStream(this.so.getInputStream());
		    }
		   
		    @Override
		    public void run() {
		      try {
		        for(running = 2; running == 2; ) {
		          try {
		            Integer msg = readMessage();
		            if(msg ==  null) {
		              throw new IOException("socket closed by peer");
		            }
		            for(Servant servant : servants) {
		           //   if(servant != this) {
		                try {
		                  servant.writeMessage(msg);
		                }
		                catch(Exception x) {
		                  x.printStackTrace();
		                }
		              }
		            }
		      //    }
		          catch(SocketTimeoutException _) {
		             // gibt die möglichkeit, running zu checken
		          }
		        }
		      }
		      catch(final Exception x) {
		        x.printStackTrace();
		      }
		      finally {
		        servants.remove(this);
		        try {
		          so.close();
		        }
		        catch(final Exception x){
		          x.printStackTrace();
		        }
		      }
		    }

		    private synchronized void writeMessage(Integer msg) throws Exception {
		      os.writeObject(msg);
		      os.flush();
		    }

		    private synchronized Integer readMessage() throws Exception {
		    	Integer i =(Integer)is.readObject();
		    	return i;
		    }
		      }

		  public void start(int port) throws Exception {
		    final ServerSocket ss = new ServerSocket(port);
		    try {
		      for(;;) {
		        final Socket so = ss.accept();
		        so.setKeepAlive(true);
		        so.setSoTimeout(1000);
		        final Servant servant = new Servant(so);
		        servants.add(servant);
		        new Thread(servant).start();
		      }
		    }
		    finally {
		      ss.close();
		    }
		  }
}

