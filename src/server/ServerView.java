package server;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ServerView {
	
	Stage primaryStage;
	ServerModel model;
	Button button1;
	
	public ServerView(Stage primaryStage, ServerModel model){
		this.primaryStage = primaryStage;
		this.model = model;
		
		//Setting up BorderPane
		BorderPane topPane = new BorderPane();
		topPane.setId("topPane"); //ID for CSS
		topPane.setPrefHeight(800);
		topPane.setPrefWidth(1200);
		
		Button ConnectServer = new Button("Connect to Server");
		ConnectServer.setId("ServerButtons");
		ConnectServer.setPrefWidth(500);
		Button DisconnectServer = new Button("Disconnect  to Server");
		DisconnectServer.setId("ServerButtons");
		DisconnectServer.setPrefWidth(500);

		GridPane innerPane = new GridPane();
		innerPane.setAlignment(Pos.TOP_CENTER);
		innerPane.setHgap(10);
		innerPane.setVgap(100);
		innerPane.add(ConnectServer, 0, 5);
		innerPane.add(DisconnectServer, 0, 6);
		topPane.setCenter(innerPane);
		
		Scene scene = new Scene(topPane);
		scene.getStylesheets().add("stylesheet");
		primaryStage.setTitle("Server-Applikation");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
	}
	
	public void start(){
		primaryStage.show();
	}

}
