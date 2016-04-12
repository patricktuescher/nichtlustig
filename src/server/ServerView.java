package server;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane; 
import javafx.stage.Stage;

public class ServerView {
	
	Stage primaryStage;
	ServerModel model;
	
	public ServerView(Stage primaryStage, ServerModel model){
		this.primaryStage = primaryStage;
		this.model = model;
		
		
		BorderPane topPane = new BorderPane();
		topPane.setPrefHeight(800);
		topPane.setPrefWidth(1200);		
		topPane.setId("topPane");
		
		Scene scene = new Scene(topPane);
		//topPane.setStyle("-fx-background-image: url(/images/Server_Background.jpg)");
		scene.getStylesheets().add("server/style.css");
		primaryStage.setScene(scene);
	}
	
	public void start(){
		primaryStage.show();
	}

}
