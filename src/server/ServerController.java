package server;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ServerController {
	
	ServerView view;
	ServerModel model;
	
	public ServerController(ServerView view, ServerModel model){
		this.view = view;
		this.model = model;
		
		view.ConnectServer.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				view.status.setId("greenCircle");
				view.onlineOffline.setText("Online");
				view.onlineOffline.setId("online");
				
			}
			
			
		});
		
		view.DisconnectServer.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				view.status.setId("redCircle");
				view.onlineOffline.setText("Offline");
				view.onlineOffline.setId("offline");
				
			}
			
			
		});
	}
	

}
