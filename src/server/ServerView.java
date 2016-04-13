package server;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ServerView {
	
	Stage primaryStage;
	ServerModel model;
	Button button1;
	
	public ServerView(Stage primaryStage, ServerModel model){
		this.primaryStage = primaryStage;
		this.model = model;
		
		
		BorderPane topPane = new BorderPane();
		topPane.setId("topPane");
		topPane.setPrefHeight(800);
		topPane.setPrefWidth(1200);
		HBox h1 = new HBox();
		h1.setId("h1");
		topPane.setCenter(h1);
		button1 = new Button("Hello World");
		topPane.setBottom(button1);
		
		Scene scene = new Scene(topPane);
		//topPane.setStyle("-fx-background-image: url(/images/Server_Background.jpg)");
		scene.getStylesheets().add("stylesheet");
		
		primaryStage.setScene(scene);
	}
	
	public void start(){
		primaryStage.show();
	}

}
