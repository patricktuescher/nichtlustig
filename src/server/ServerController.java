package server;
import java.util.Locale;

import client.ServiceLocator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import tools.Translator;

public class ServerController {
    
    final private ServerModel model;
    final private ServerView view;
    protected ServiceLocator sl; 
    
    protected ServerController(ServerModel model, ServerView view) {
        this.model = model;
        this.view = view;
        sl = ServiceLocator.getServiceLocator();
        
        // register ourselves to listen for button clicks
        view.ConnectServer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                	model.startServer();
                	view.conOfflineIMV.setVisible(false);
					view.conOnlineIMV.setVisible(true);
					sl.getLogger().info("Start Server Connection");
                	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        view.DisconnectServer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
//					model.stopServer();
                	view.conOfflineIMV.setVisible(true);
					view.conOnlineIMV.setVisible(false);
					sl.getLogger().info("Stop Server Connection");
                	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        
     // EventHandler QuitGameButton - LoginScene
     		view.exit.setOnAction(new EventHandler<ActionEvent>(){

     			@Override
     			public void handle(ActionEvent arg0) {
     			view.stop();     // closes the GUI
                Platform.exit(); // ends any JavaFX activities
                System.exit(0);  // end all activities (our server task) - not good code
     			}		
     		});
        
        
        
        // register ourselves to handle window-closing event
        view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                view.stop();     // closes the GUI
                Platform.exit(); // ends any JavaFX activities
                System.exit(0);  // end all activities (our server task) - not good code
            }
        });
        
        view.languageChange.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             if(view.t.getCurrentLocaleString().equals("en")) {
            	   view.t = new Translator("de");
            	   sl.getLogger().info("Language changed to de");
      			}
     			else{
       			view.t = new Translator("en");}
             	sl.getLogger().info("Language changed to en");
               updateView();
            	            }
        });
       
    }
    
    public void updateView(){
		view.languageChange.setText(view.t.getString("Button.languageChange"));
		view.ConnectServer.setText(view.t.getString("Button.ConnectServer"));
		view.DisconnectServer.setText(view.t.getString("Button.DisconnectServer"));
		view.exit.setText(view.t.getString("Button.exit"));
	
	}
}
