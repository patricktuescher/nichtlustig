package server;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ServerView {
	
	Stage primaryStage;
	ServerModel model;
	
	public ServerView(Stage primaryStage, ServerModel model){
		this.primaryStage = primaryStage;
		this.model = model;
		
		BorderPane topPane = new BorderPane();
		topPane.setCenter(new Label("Hello World"));
		
		Scene scene = new Scene(topPane);
		primaryStage.setScene(scene);
	}
	
	public void start(){
		primaryStage.show();
	}

}
