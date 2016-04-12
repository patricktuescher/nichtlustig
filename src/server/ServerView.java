package server;

import javafx.stage.Stage;

public class ServerView {
	
	Stage primaryStage;
	ServerModel model;
	
	public ServerView(Stage primaryStage, ServerModel model){
		this.primaryStage = primaryStage;
		this.model = model;
	}
	
	public void start(){
		primaryStage.show();
	}

}
