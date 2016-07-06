package server;

import javafx.scene.shape.Circle;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
		    private final ByteArrayOutputStream baos;
		    private final byte b[];
		    private volatile int running;

		    private Servant(Socket so) {
		      this.so = so;
		      b = new byte[1024*1024];
		      baos = new ByteArrayOutputStream(b.length);
		    }
		   
		    @Override
		    public void run() {
		      try {
		        for(running = 2; running == 2; ) {
		          try {
		        	System.out.println("hier");
		            final String msg = readMessage();
		            if(msg ==  null) {
		              throw new IOException("socket closed by peer");
		            }
		            for(Servant servant : servants) {
		              if(servant != this) {
		                try {
		                  servant.writeMessage(msg);
		                }
		                catch(Exception x) {
		                  x.printStackTrace();
		                }
		              }
		            }
		          }
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
		        catch(final Exception x) {
		          x.printStackTrace();
		        }
		      }
		    }

		    private synchronized void writeMessage(String msg) throws Exception {
		      final OutputStream os = so.getOutputStream();
		      os.write(msg.getBytes());
		      os.write(delim);
		      os.flush();
		    }

		    private String readMessage() throws Exception {
		      final InputStream is = so.getInputStream();
		      for(int x;;) {
		        final byte[] a = baos.toByteArray(); // give me a copy
		        for(int end = 0; end < a.length; end++) {
		          if(a[end] == delim) { // delimiter found
		            baos.reset();
		            if((x = a.length - end - 1) > 0) {
		              baos.write(a, end + 1, x);
		            }
		            return new String(a, 0, end);
		          }
		        }
		        if((x = is.read(b)) <= 0) {
		          return null;
		        }
		        baos.write(b, 0, x);
		      }
		    }
		  }

		  public static void start() throws Exception {
		    final ServerSocket ss = new ServerSocket(8080);
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

