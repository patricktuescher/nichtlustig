package server;
import client.ServiceLocator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

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
    }
}
